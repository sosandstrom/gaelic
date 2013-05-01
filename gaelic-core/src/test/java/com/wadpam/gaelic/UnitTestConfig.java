/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic;

import com.wadpam.gaelic.tree.InterceptorAdapter;
import com.wadpam.gaelic.tree.MethodUriLeaf;
import com.wadpam.gaelic.tree.UnitTestInterceptor;
import javax.servlet.ServletConfig;

/**
 *
 * @author sosandstrom
 */
public class UnitTestConfig implements GaelicConfig {

    @Override
    public Node init(GaelicServlet gaelicServlet, ServletConfig servletConfig) {
        // add /api/{domain}
        BUILDER.root()
            .path("api")
                .interceptedPath("{domain}", new InterceptorAdapter())
                    // add /endpoints
                    .add("endpoints", MethodUriLeaf.class).named("getEndpoints()");

                // add /interceptor/{boolean}
                BUILDER.from("{domain}")
                    .interceptedPath("interceptor", new UnitTestInterceptor()).named("appendURI")
                        .add("true", MethodUriLeaf.class).named("bool");
                    BUILDER.from("interceptor")
                        .add("false", "bool");
                    
        return BUILDER.build();
    }
    
}
