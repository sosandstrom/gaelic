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
import com.wadpam.gaelic.oauth.provider.tree.AuthorizeLeaf;
import com.wadpam.gaelic.oauth.provider.tree.ClientLeaf;
import com.wadpam.gaelic.oauth.provider.tree.ProfileLeaf;
import com.wadpam.gaelic.tree.ForwardLeaf;
import java.util.HashMap;
import java.util.Map;
import net.sf.mardao.core.dao.DaoImpl;

/**
 *
 * @author sosandstrom
 */
public class ProviderConfig {

    public static Map<Class, Node> createLeaves() {
        final Map<Class, Node> LEAF_MAP= new HashMap<Class, Node>();
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
        
        ProfileLeaf profileLeaf = new ProfileLeaf();
        profileLeaf.setService(profileService);
        LEAF_MAP.put(ProfileLeaf.class, profileLeaf);
        
        ClientLeaf clientLeaf = new ClientLeaf();
        clientLeaf.setService(clientService);
        LEAF_MAP.put(ClientLeaf.class, clientLeaf);
        
        AuthorizeLeaf authorizeLeaf = new AuthorizeLeaf();
        authorizeLeaf.setProviderService(providerService);
        LEAF_MAP.put(AuthorizeLeaf.class, authorizeLeaf);
        
        // forwards to login.html
        ForwardLeaf forwardLeaf = new ForwardLeaf("/internal/login.html");
        LEAF_MAP.put(ForwardLeaf.class, forwardLeaf);
        
        return LEAF_MAP;
    }
    
}
