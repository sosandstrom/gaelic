/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.security;

import com.wadpam.gaelic.GaelicConfig;
import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.Node;
import javax.servlet.ServletConfig;

/**
 *
 * @author sosandstrom
 */
public class UnitTestConfig implements GaelicConfig {

    @Override
    public Node init(GaelicServlet gaelicServlet, ServletConfig servletConfig) {
        DomainSecurityInterceptor securityInterceptor = new DomainSecurityInterceptor();
        SecurityDetailsService detailsService = new UnitTestDetailsService();
        securityInterceptor.setSecurityDetailsService(detailsService);
        
        // add /api/{domain}
        BUILDER.root()
            .path("api")
                .interceptedPath("{domain}", securityInterceptor)
                    // add /endpoints
                    .add("endpoints", MethodUriLeaf.class).named("getEndpoints()");
        
        BUILDER.from("{domain}")
                    // add _admin
                    .path("_admin")
                        .add("task", MethodUriLeaf.class).named("AdminTask");
                    
        return BUILDER.build();
    }
    
}
