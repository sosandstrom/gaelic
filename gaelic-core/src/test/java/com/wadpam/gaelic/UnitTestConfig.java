/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic;

import com.wadpam.gaelic.config.ConfigBuilder;
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
        final ConfigBuilder root = BUILDER.root();
        
        // add /api/{domain}
        ConfigBuilder domainFactory = root.path("api").add("{domain}", InterceptorAdapter.class);
        
        // add /endpoints
        domainFactory.add("endpoints", MethodUriLeaf.class).named("getEndpoints()");

        // add /interceptor/{boolean}
        ConfigBuilder interceptorFactory = domainFactory.add("interceptor", UnitTestInterceptor.class).named("appendURI");
        Node bool = interceptorFactory.add("true", MethodUriLeaf.class).named("bool").build();
        interceptorFactory.add("false", bool);
        
        return root.build();
    }
    
}
