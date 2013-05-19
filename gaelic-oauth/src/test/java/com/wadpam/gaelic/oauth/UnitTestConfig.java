/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth;

import com.wadpam.gaelic.GaelicConfig;
import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.oauth.web.OAuth2Interceptor;
import com.wadpam.gaelic.security.DomainSecurityInterceptor;
import com.wadpam.gaelic.security.SecurityConfig;
import com.wadpam.gaelic.security.SecurityDetailsService;
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
        
        DomainSecurityInterceptor basicInterceptor = new DomainSecurityInterceptor();
        OAuth2Interceptor oauth2Interceptor = new OAuth2Interceptor();
        
        SecurityDetailsService detailsService = new UnitTestDetailsService();
        basicInterceptor.setSecurityDetailsService(detailsService);
        oauth2Interceptor.setSecurityDetailsService(detailsService);
        
        Collection<Map.Entry<String, Collection<String>>> basicWhitelist = WHITELIST_BUILDER
                .with("\\A/api/[^/]+/public\\z", GET)
                .build();
        basicInterceptor.setWhitelistedMethods(basicWhitelist);
        
        // add /api/{domain}
        BUILDER.root()
            .path("api")
                .interceptedPath("{domain}", basicInterceptor)
                    // add /endpoints
                    .add("endpoints", MethodUriLeaf.class).named("getEndpoints()");
        
        BUILDER.from("{domain}")
                    // add public whitelisted
                    .add("public", MethodUriLeaf.class).named("public");
        
        BUILDER.from("{domain}")
                    // add double protected
                    .interceptor("double", oauth2Interceptor)
                    .add("double", MethodUriLeaf.class).named("double");
        
        BUILDER.from("{domain}")
                    // add _admin
                    .path("_admin")
                        .add("task", MethodUriLeaf.class).named("AdminTask");
                    
        return BUILDER.build();
    }
    
}
