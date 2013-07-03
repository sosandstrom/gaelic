/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic;

import static com.wadpam.gaelic.GaelicConfig.BUILDER;
import com.wadpam.gaelic.crud.DateService;
import com.wadpam.gaelic.json.JDate;
import com.wadpam.gaelic.tree.DateLeaf;
import com.wadpam.gaelic.tree.InterceptorAdapter;
import com.wadpam.gaelic.tree.LeafAdapter;
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
        LeafAdapter dDateLeaf = new LeafAdapter(JDate.class);
        LeafAdapter datumLeaf = new LeafAdapter(JDate.class, "DDate");
        
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
                    
                // add /crud/v10
                BUILDER.from("{domain}")
                    .path("crud")
                        .crud("v10", DateLeaf.class, DateService.class);
                    
                // add /adapter/DDate
                BUILDER.from("{domain}")
                    .path("adapter")
                        .add("DDate", dDateLeaf);
                    
                    // add /adapter/datum to test DDate kind override
                    BUILDER.from("adapter")
                        .add("datum", datumLeaf);
                    
        return BUILDER.build();
    }
    
}
