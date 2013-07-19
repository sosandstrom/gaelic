/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.config;

import com.wadpam.gaelic.GaelicConfig;
import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.oauth.provider.config.ProviderConfig;
import com.wadpam.gaelic.oauth.provider.tree.AuthorizeLeaf;
import com.wadpam.gaelic.oauth.provider.tree.ClientLeaf;
import com.wadpam.gaelic.oauth.provider.tree.ProfileLeaf;
import com.wadpam.gaelic.oauth.web.OAuth2Interceptor;
import com.wadpam.gaelic.security.SecurityConfig;
import com.wadpam.gaelic.tree.ForwardLeaf;
import com.wadpam.gaelic.tree.InterceptorAdapter;
import com.wadpam.gaelic.tree.MethodUriLeaf;
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
        
        OAuth2Interceptor oauth2Interceptor = (OAuth2Interceptor) LEAF_MAP.get(OAuth2Interceptor.class);
        oauth2Interceptor.setWhitelistedMethods(createWhitelistedMethods());
        
        BUILDER.root()
                .path("api")
                    .interceptedPath("{domain}", new InterceptorAdapter())
                        .add("leaf", MethodUriLeaf.class).named("getPage()")
            .from("root")
                .path("oauth")
                    .add("authorize", (Node) LEAF_MAP.get(AuthorizeLeaf.class)).named("AuthorizeEndpoint")
                .from("oauth")
                    .interceptedPath("profile", oauth2Interceptor)
                        .add("v10", (Node) LEAF_MAP.get(ProfileLeaf.class)).named("Resource Owner")
                .from("oauth")
                    .path("client")
                        .add("v10", (Node) LEAF_MAP.get(ClientLeaf.class)).named("Consumer Client")
                .from("oauth")
                    .add("login.html", (Node) LEAF_MAP.get(ForwardLeaf.class)).named("Sign in");
        
        return BUILDER.build();
    }

    protected Collection<Map.Entry<String, Collection<String>>> createWhitelistedMethods() {
        return WHITELIST_BUILDER.with("\\A/oauth/profile/v10\\z", POST)
                .build();
    }

}
