/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.crud;

import com.wadpam.gaelic.GaelicConfig;
import static com.wadpam.gaelic.GaelicConfig.BUILDER;
import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.tree.InterceptorAdapter;
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
                .path("{domain}")
                    .path("crud")
                        .crud("v10", DateLeaf.class, DateService.class);
                    
        return BUILDER.build();
    }
    
}
