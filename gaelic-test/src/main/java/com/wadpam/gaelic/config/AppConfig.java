/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.config;

import com.wadpam.gaelic.GaelicConfig;
import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.tree.Path;
import com.wadpam.gaelic.tree.InterceptorAdapter;
import com.wadpam.gaelic.tree.MethodUriLeaf;
import javax.servlet.ServletConfig;

/**
 *
 * @author sosandstrom
 */
public class AppConfig implements GaelicConfig {

    @Override
    public Node init(GaelicServlet gaelicServlet, ServletConfig servletConfig) {
        final Path root = new Path();
        root.setName("root");
        
        final Path api = new Path();
        api.setName("api");
        root.addChild("api", api);
        
//        final Path domain = new Path();
        final InterceptorAdapter domain = new InterceptorAdapter();
        domain.setName("{domain}");
        api.addChild("{domain}", domain);
        
        final MethodUriLeaf leaf = new MethodUriLeaf("getPage()");
        domain.addChild("leaf", leaf);
        
        return root;
    }

}
