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
import com.wadpam.gaelic.tree.ForwardLeaf;
import com.wadpam.gaelic.tree.InterceptorAdapter;
import com.wadpam.gaelic.tree.MethodUriLeaf;
import java.util.Map;
import javax.servlet.ServletConfig;

/**
 *
 * @author sosandstrom
 */
public class AppConfig implements GaelicConfig {

    @Override
    public Node init(GaelicServlet gaelicServlet, ServletConfig servletConfig) {
        
        // Create Leaves and services for OAuth2 Provider:
        final Map<Class, Node> LEAF_MAP = ProviderConfig.createLeaves();
        
        BUILDER.root()
                .path("api")
                    .interceptedPath("{domain}", new InterceptorAdapter())
                        .add("leaf", MethodUriLeaf.class).named("getPage()")
            .from("root")
                .path("oauth")
                    .add("authorize", LEAF_MAP.get(AuthorizeLeaf.class)).named("AuthorizeEndpoint")
                .from("oauth")
                    .path("profile")
                        .add("v10", LEAF_MAP.get(ProfileLeaf.class)).named("Resource Owner")
                .from("oauth")
                    .path("client")
                        .add("v10", LEAF_MAP.get(ClientLeaf.class)).named("Consumer Client")
                .from("oauth")
                    .add("login.html", LEAF_MAP.get(ForwardLeaf.class)).named("Sign in");
        
        return BUILDER.build();
    }

}
