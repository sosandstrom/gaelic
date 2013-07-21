/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.config;

import com.google.appengine.api.utils.SystemProperty;
import com.wadpam.gaelic.GaelicConfig;
import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.oauth.provider.config.ProviderConfig;
import com.wadpam.gaelic.oauth.provider.dao.Do2pClientDao;
import com.wadpam.gaelic.oauth.provider.dao.Do2pProfileDao;
import com.wadpam.gaelic.oauth.provider.service.ClientService;
import com.wadpam.gaelic.oauth.provider.service.ProfileService;
import com.wadpam.gaelic.oauth.provider.service.ProviderService;
import com.wadpam.gaelic.oauth.provider.tree.AuthorizeEndpointLeaf;
import com.wadpam.gaelic.oauth.provider.tree.ClientLeaf;
import com.wadpam.gaelic.oauth.provider.tree.ProfileLeaf;
import com.wadpam.gaelic.oauth.provider.tree.TokenEndpointLeaf;
import com.wadpam.gaelic.oauth.web.OAuth2Interceptor;
import com.wadpam.gaelic.security.SecurityConfig;
import com.wadpam.gaelic.security.SecurityInterceptor;
import com.wadpam.gaelic.tree.ForwardLeaf;
import com.wadpam.gaelic.tree.InterceptorAdapter;
import com.wadpam.gaelic.tree.MethodUriLeaf;
import com.wadpam.gaelic.web.MardaoPrincipalInterceptor;
import java.util.Collection;
import java.util.Map;
import javax.servlet.ServletConfig;

/**
 *
 * @author sosandstrom
 */
public class AppConfig implements GaelicConfig, SecurityConfig {

    @Override
    public Node init(GaelicServlet gaelicServlet, ServletConfig servletConfig) {
        
        // Create Leaves and services for OAuth2 Provider:
        final Map<Class, Object> LEAF_MAP = ProviderConfig.createLeaves();

        SecurityInterceptor basicInterceptor = (SecurityInterceptor) LEAF_MAP.get(SecurityInterceptor.class);
        basicInterceptor.setWhitelistedMethods(createBasicWhitelist());
        
        OAuth2Interceptor oauth2Interceptor = (OAuth2Interceptor) LEAF_MAP.get(OAuth2Interceptor.class);
        oauth2Interceptor.setWhitelistedMethods(createOAuth2Whitelist());
        
        BUILDER.root()
                .path("api")
                    .interceptedPath("{domain}", new InterceptorAdapter())
                        .add("leaf", MethodUriLeaf.class).named("getPage()")
            .from("root")
                .interceptor("oauth", basicInterceptor)
                .interceptor("oauth", oauth2Interceptor)
                .interceptedPath("oauth", new MardaoPrincipalInterceptor())
                    .add("authorize", (Node) LEAF_MAP.get(AuthorizeEndpointLeaf.class)).named("AuthorizeEndpoint");
                BUILDER.from("oauth")
                    .add("token", (Node) LEAF_MAP.get(TokenEndpointLeaf.class)).named("TokenEndpoint");
                BUILDER.from("oauth")
                    .path("profile")
                        .add("v10", (Node) LEAF_MAP.get(ProfileLeaf.class)).named("Resource Owner")
                .from("oauth")
                    .path("client")
                        .add("v10", (Node) LEAF_MAP.get(ClientLeaf.class)).named("Consumer Client")
                .from("oauth")
                    .add("login.html", (Node) LEAF_MAP.get(ForwardLeaf.class)).named("Sign in");
        
        initDevServer(LEAF_MAP);
                
        return BUILDER.build();
    }

    protected Collection<Map.Entry<String, Collection<String>>> createBasicWhitelist() {
        return WHITELIST_BUILDER.with("\\A/oauth/profile/", GET, POST, DELETE)
                .add("\\A/oauth/client/v", DELETE, GET, POST)
                .add("\\A/oauth/login.html", DELETE, GET, POST)
                .add("\\A/oauth/authorize", DELETE, GET, POST)
                .build();
    }

    protected Collection<Map.Entry<String, Collection<String>>> createOAuth2Whitelist() {
        return WHITELIST_BUILDER.with("\\A/oauth/profile/v10\\z", POST)
                .add("\\A/oauth/client/v", DELETE, GET, POST)
                .add("\\A/oauth/login.html", DELETE, GET, POST)
                .add("\\A/oauth/authorize", DELETE, GET, POST)
                .add("\\A/oauth/token\\z", POST)
                .build();
    }

    private void initDevServer(Map<Class, Object> LEAF_MAP) {
        if (SystemProperty.Environment.Value.Development == SystemProperty.environment.value()) {
            
            // create a client id 4004
            ClientLeaf clientLeaf = (ClientLeaf) LEAF_MAP.get(ClientLeaf.class);
            ClientService clientService = clientLeaf.getService();
            Do2pClientDao clientDao = clientService.getDao();
            clientDao.persist(4004L, "Localhost client", "localDev", 
                    "http://localhost:8485/RestTest/oauth/gaelic.html", "topsecret");
            
            // create a profile to allow test sign in
            ProfileLeaf profileLeaf = (ProfileLeaf) LEAF_MAP.get(ProfileLeaf.class);
            ProfileService profileService = profileLeaf.getService();
            Do2pProfileDao profileDao = profileService.getDao();
            final String secret = ProviderService.encryptPassword("john.doe@acme.com", 7007L);
            profileDao.persist(7007L, "john.doe@acme.com", secret, 1L, "john.doe@acme.com");
        }
    }

}
