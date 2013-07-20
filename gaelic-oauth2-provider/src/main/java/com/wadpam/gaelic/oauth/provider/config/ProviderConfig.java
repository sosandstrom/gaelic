/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.provider.config;

import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.oauth.provider.dao.DaoConfig;
import com.wadpam.gaelic.oauth.provider.dao.Do2pClientDao;
import com.wadpam.gaelic.oauth.provider.dao.Do2pClientDaoBean;
import com.wadpam.gaelic.oauth.provider.dao.Do2pProfileDao;
import com.wadpam.gaelic.oauth.provider.dao.Do2pProfileDaoBean;
import com.wadpam.gaelic.oauth.provider.dao.Do2pTokenDao;
import com.wadpam.gaelic.oauth.provider.dao.Do2pTokenDaoBean;
import com.wadpam.gaelic.oauth.provider.domain.Do2pClient;
import com.wadpam.gaelic.oauth.provider.domain.Do2pProfile;
import com.wadpam.gaelic.oauth.provider.domain.Do2pToken;
import com.wadpam.gaelic.oauth.provider.service.ClientService;
import com.wadpam.gaelic.oauth.provider.service.ProfileService;
import com.wadpam.gaelic.oauth.provider.service.ProviderService;
import com.wadpam.gaelic.oauth.provider.tree.AuthorizeEndpointLeaf;
import com.wadpam.gaelic.oauth.provider.tree.ClientLeaf;
import com.wadpam.gaelic.oauth.provider.tree.ProfileLeaf;
import com.wadpam.gaelic.oauth.provider.tree.TokenEndpointLeaf;
import com.wadpam.gaelic.oauth.web.OAuth2Interceptor;
import com.wadpam.gaelic.security.SecurityInterceptor;
import com.wadpam.gaelic.tree.ForwardLeaf;
import com.wadpam.gaelic.tree.InterceptedPath;
import java.util.HashMap;
import java.util.Map;
import net.sf.mardao.core.dao.DaoImpl;

/**
 *
 * @author sosandstrom
 */
public class ProviderConfig {

    public static Map<Class, Object> createLeaves() {
        final Map<Class, Object> LEAF_MAP= new HashMap<Class, Object>();
        final Map<Class, DaoImpl> DAO_MAP = DaoConfig.createDaos();
        
        Do2pClientDao clientDao = (Do2pClientDaoBean) DAO_MAP.get(Do2pClient.class);
        ClientService clientService = new ClientService();
        clientService.setDao(clientDao);
        
        Do2pProfileDao profileDao = (Do2pProfileDaoBean) DAO_MAP.get(Do2pProfile.class);
        ProfileService profileService = new ProfileService();
        profileService.setDao(profileDao);
        
        Do2pTokenDao tokenDao = (Do2pTokenDaoBean) DAO_MAP.get(Do2pToken.class);
        
        ProviderService providerService = new ProviderService();
        providerService.setClientDao(clientDao);
        providerService.setProfileDao(profileDao);
        providerService.setTokenDao(tokenDao);
        LEAF_MAP.put(ProviderService.class, providerService);
        
        ProfileLeaf profileLeaf = new ProfileLeaf();
        profileLeaf.setService(profileService);
        LEAF_MAP.put(ProfileLeaf.class, profileLeaf);
        
        ClientLeaf clientLeaf = new ClientLeaf();
        clientLeaf.setService(clientService);
        LEAF_MAP.put(ClientLeaf.class, clientLeaf);
        
        AuthorizeEndpointLeaf authorizeEndpointLeaf = new AuthorizeEndpointLeaf();
        authorizeEndpointLeaf.setProviderService(providerService);
        LEAF_MAP.put(AuthorizeEndpointLeaf.class, authorizeEndpointLeaf);
        
        TokenEndpointLeaf tokenEndpointLeaf = new TokenEndpointLeaf();
        tokenEndpointLeaf.setProviderService(providerService);
        LEAF_MAP.put(TokenEndpointLeaf.class, tokenEndpointLeaf);
        
        // Basic Authentication interceptor protecting /oauth/token
        SecurityInterceptor basicInterceptor = new SecurityInterceptor();
        basicInterceptor.setSecurityDetailsService(clientService);
        LEAF_MAP.put(SecurityInterceptor.class, basicInterceptor);
        
        // OAuth2 interceptor protecting /oauth/profile
        OAuth2Interceptor oauth2Interceptor = new OAuth2Interceptor();
        oauth2Interceptor.setSecurityDetailsService(providerService);
        LEAF_MAP.put(OAuth2Interceptor.class, oauth2Interceptor);
        
        // forwards to login.html
        ForwardLeaf forwardLeaf = new ForwardLeaf("/internal/login.html");
        LEAF_MAP.put(ForwardLeaf.class, forwardLeaf);
        
        return LEAF_MAP;
    }
    
}
