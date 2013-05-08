/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.security;

import com.wadpam.gaelic.GaelicConfig;
import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.Node;
import java.util.Collection;
import java.util.Map;
import javax.servlet.ServletConfig;

/**
 *
 * @author sosandstrom
 */
public class UnitTestConfig implements GaelicConfig, SecurityConfig {

    @Override
    public Node init(GaelicServlet gaelicServlet, ServletConfig servletConfig) {
        
        DomainSecurityInterceptor securityInterceptor = new DomainSecurityInterceptor();
        
        SecurityDetailsService detailsService = new UnitTestDetailsService();
        securityInterceptor.setSecurityDetailsService(detailsService);
        
        Collection<Map.Entry<String, Collection<String>>> basicWhitelist = WHITELIST_BUILDER
                .with("\\A/api/[^/]+/public\\z", GET)
                .build();
        securityInterceptor.setWhitelistedMethods(basicWhitelist);
        
        // add /api/{domain}
        BUILDER.root()
            .path("api")
                .interceptedPath("{domain}", securityInterceptor)
                    // add /endpoints
                    .add("endpoints", MethodUriLeaf.class).named("getEndpoints()");
        
        BUILDER.from("{domain}")
                    // add public whitelisted
                    .add("public", MethodUriLeaf.class).named("public");
        
        BUILDER.from("{domain}")
                    // add _admin
                    .path("_admin")
                        .add("task", MethodUriLeaf.class).named("AdminTask");
                    
        return BUILDER.build();
    }
    
}
