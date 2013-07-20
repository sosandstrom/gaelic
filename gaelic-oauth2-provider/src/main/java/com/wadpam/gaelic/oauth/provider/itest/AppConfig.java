/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.provider.itest;

import com.wadpam.gaelic.GaelicConfig;
import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.oauth.provider.tree.AuthorizeEndpointLeaf;
import javax.servlet.ServletConfig;

/**
 *
 * @author sosandstrom
 */
public class AppConfig implements GaelicConfig {

    @Override
    public Node init(GaelicServlet gaelicServlet, ServletConfig servletConfig) {
        
        BUILDER.root()
                .path("oauth")
                    .add("authorize", AuthorizeEndpointLeaf.class).named("AuthorizeEndpoint");
        
        return BUILDER.build();
    }

}
