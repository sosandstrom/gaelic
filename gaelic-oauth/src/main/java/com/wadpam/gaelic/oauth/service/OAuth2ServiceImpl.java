/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.service;

import com.wadpam.gaelic.crud.CrudListener;
import com.wadpam.gaelic.crud.CrudObservable;
import com.wadpam.gaelic.crud.MardaoCrudService;
import com.wadpam.gaelic.exception.AuthenticationFailedException;
import com.wadpam.gaelic.json.RestResponse;
import com.wadpam.gaelic.oauth.dao.DConnectionDao;
import com.wadpam.gaelic.oauth.domain.DConnection;
import com.wadpam.gaelic.oauth.domain.DOAuth2User;
import com.wadpam.gaelic.social.SocialProfile;
import com.wadpam.gaelic.social.SocialTemplate;
import com.wadpam.gaelic.tree.CrudLeaf;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sosandstrom
 */
public class OAuth2ServiceImpl implements OAuth2Service, CrudObservable {
    
    static final Logger LOG = LoggerFactory.getLogger(OAuth2ServiceImpl.class);
    
    private boolean autoCreateUser = true;
    
//    private CrudService<DFactory, String> factoryService;
    
    private DConnectionDao connectionDao;
    
    private OAuth2UserService<DOAuth2User> oauth2UserService;
    
    protected final ArrayList<CrudListener> listeners = new ArrayList<CrudListener>();
    
    private ProviderFactory customProvider;
    
    protected Object beginTransaction() {
        return connectionDao.beginTransaction();
    }
    
    protected void commitTransaction(Object transaction) {
        connectionDao.commitTransaction(transaction);
    }
    
    protected void rollbackActiveTransaction(Object transaction) {
        connectionDao.rollbackActiveTransaction(transaction);
    }
    
    @Override
    public Long getUserId(Object userKey) {
        return connectionDao.getSimpleKeyByPrimaryKey(userKey);
    }
    
    /**
     * 
     * @param access_token
     * @param providerId
     * @param providerUserId
     * @param secret
     * @param expires_in
     * @return the userId associated with the Connection, null if new Connection
     */
    @Override
    public RestResponse<DConnection> registerFederated(
            String access_token, 
            String providerId,
            String providerUserId,
            String secret,
            Integer expiresInSeconds,
            String appArg0,
            String domain) {
        
        // use the connectionFactory
        final SocialTemplate socialTemplate = SocialTemplate.create(
                providerId, access_token, appArg0, domain);

        SocialProfile profile = null;
        try {
            profile = socialTemplate.getProfile();

            if (null == profile) {
                throw new IllegalArgumentException("Invalid connection");
            }
        } catch (IOException unauthorized) {
            throw new AuthenticationFailedException(503401, "Unauthorized federated side", null);
        }
        
        // providerUserId is optional, fetch it if necessary:
        final String realProviderUserId = profile.getId();
        if (null == providerUserId) {
            providerUserId = realProviderUserId;
        }
        else if (!providerUserId.equals(realProviderUserId)) {
            throw new AuthenticationFailedException(503403, "Unauthorized federated side mismatch", null);
        }
        
        // do outside transactions, as Only ancestor queries are allowed inside transactions:
        
        // load connection from db async style (likely case is new token for existing user)
        final Iterable<DConnection> conns = connectionDao.queryByProviderUserId(providerUserId);
        
        // load existing conn for token
        DConnection conn = connectionDao.findByAccessToken(access_token);
        final boolean isNewConnection = (null == conn);
        boolean isNewUser = false;
        DOAuth2User user = null;
        Object userKey = null;

        final Object transaction = beginTransaction();

        try {
            final ArrayList<Long> expiredTokens = new ArrayList<Long>();

            // create connection?
            if (isNewConnection) {

                // find other connections for this user, discard expired
                final Date now = new Date();
                for (DConnection dc : conns) {
                    if (providerId.equals(dc.getProviderId())) {
                        userKey = dc.getUserKey();

                        // expired?
                        if (null != dc.getExpireTime() && now.after(dc.getExpireTime())) {
                            expiredTokens.add(dc.getId());
                        }
                    }
                }

                // create user?
                isNewUser = (null == userKey);
                if (isNewUser && autoCreateUser && null != oauth2UserService) {
                    user = oauth2UserService.createUser(profile.getEmail(), 
                            profile.getFirstName(), profile.getLastName(),
                            profile.getDisplayName(), providerId, providerUserId, 
                            profile.getUsername(), profile.getProfileUrl());
                    userKey = oauth2UserService.getUserKey(user);
                }

                conn = new DConnection();
                conn.setAccessToken(access_token);
                conn.setDisplayName(profile.getDisplayName());
                conn.setProviderId(providerId);
                conn.setProviderUserId(providerUserId);
                conn.setSecret(secret);
                conn.setUserKey(userKey);
                if (null != expiresInSeconds) {
                    conn.setExpireTime(new Date(System.currentTimeMillis() + expiresInSeconds*1000L));
                }
                connectionDao.persist(conn);
            }
            else {
                userKey = conn.getUserKey();
            }

            // update connection values
            conn.setAppArg0(appArg0);
            if (null != oauth2UserService) {
                
                // existing user
                if (null == user) {
                    Long userId = connectionDao.getSimpleKeyByPrimaryKey(userKey);
                    user = oauth2UserService.get(null, MardaoCrudService.getStringId(userId, Long.class));
                }
                
                // copy roles to Connection
                if (null != user) {
                    Collection<String> userRoles = user.getRoles();
                    conn.setUserRoles(ConnectionServiceImpl.convertRoles(userRoles));
                }
                LOG.debug("Roles set to {} from user {}", conn.getUserRoles(), user);
            }
            connectionDao.update(conn);

            // notify listeners
            postService(null, domain, OPERATION_REGISTER_FEDERATED, conn, null, profile);

            connectionDao.delete(userKey, expiredTokens);
            
            commitTransaction(transaction);
            
            return new RestResponse<DConnection>( 
                    isNewUser ? CrudLeaf.STATUS_CREATED : CrudLeaf.STATUS_OK,
                    conn);
        }
        finally {
            rollbackActiveTransaction(transaction);
        }
    }
    
    @Override
    public void addListener(CrudListener listener) {
        listeners.add(listener);
    }
    
    @Override
    public void removeListener(CrudListener listener) {
        listeners.remove(listener);
    }
    
    protected void preService(HttpServletRequest request, String namespace,
            int operation, Object json, Object domain, Serializable id) {
        for (CrudListener l : listeners) {
            l.preService(null, null, request, namespace, 
                    operation, json, domain, id);
        }
    }
    
    protected void postService(HttpServletRequest request, String namespace,
            int operation, Object json, Serializable id, Object serviceResponse) {
        for (CrudListener l : listeners) {
            l.postService(null, null, request, namespace, 
                    operation, json, id, serviceResponse);
        }
    }

//    public void setFactoryService(CrudService factoryService) {
//        this.factoryService = factoryService;
//    }

    public void setConnectionDao(DConnectionDao dConnectionDao) {
        this.connectionDao = dConnectionDao;
    }

    public void setAutoCreateUser(boolean autoCreateUser) {
        this.autoCreateUser = autoCreateUser;
    }

    public void setOauth2UserService(OAuth2UserService oauth2UserService) {
        this.oauth2UserService = oauth2UserService;
    }

    public void setCustomProvider(ProviderFactory customProvider) {
        this.customProvider = customProvider;
    }

}
