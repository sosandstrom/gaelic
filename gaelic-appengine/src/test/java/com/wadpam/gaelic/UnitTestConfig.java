/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic;

import static com.wadpam.gaelic.GaelicConfig.BUILDER;
import com.wadpam.gaelic.tree.DomainLeaf;
import com.wadpam.gaelic.tree.DomainNamespaceInterceptor;
import com.wadpam.gaelic.tree.EntityLeaf;
import javax.servlet.ServletConfig;

/**
 *
 * @author sosandstrom
 */
public class UnitTestConfig implements GaelicConfig {

    @Override
    public Node init(GaelicServlet gaelicServlet, ServletConfig servletConfig) {
        
        DomainNamespaceInterceptor dni = new DomainNamespaceInterceptor();
        
        // add /api/{domain}
        BUILDER.root()
            .path("api")
                .interceptedPath("{domain}", dni)
                    // add /endpoints
                    .add("namespace", DomainLeaf.class).named("getNamespace()");
        
        BUILDER.from(Node.PATH_DOMAIN)
                    .path("entity")
                        // add {kind}
                        .add(EntityLeaf.PATH_KIND, EntityLeaf.class);
                    
        return BUILDER.build();
    }
    
}
