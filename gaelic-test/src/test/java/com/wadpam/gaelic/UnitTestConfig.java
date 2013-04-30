/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic;

import com.wadpam.gaelic.tree.Path;
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
        final Path root = new Path();
        root.setName("root");
        
        final Path api = new Path();
        api.setName("api");
        root.addChild("api", api);
        
        final InterceptorAdapter domain = new InterceptorAdapter();
        domain.setName("{domain}");
        api.addChild("{domain}", domain);
        
        final MethodUriLeaf endpoints = new MethodUriLeaf();
        endpoints.setName("getEndpoints()");
        domain.addChild("endpoints", endpoints);
        
        final UnitTestInterceptor interceptor = new UnitTestInterceptor();
        interceptor.setName("appendURI");
        domain.addChild("interceptor", interceptor);
        final MethodUriLeaf bool = new MethodUriLeaf("bool");
        interceptor.addChild("true", bool);
        interceptor.addChild("false", bool);
        
        return root;
    }
    
}
