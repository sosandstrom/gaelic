/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.config;

import com.wadpam.gaelic.GaelicConfig;
import com.wadpam.gaelic.GaelicServlet;
import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.tree.AbstractPath;
import com.wadpam.gaelic.tree.InterceptorAdapter;
import javax.servlet.ServletConfig;

/**
 *
 * @author sosandstrom
 */
public class AppConfig implements GaelicConfig {

    @Override
    public Node init(GaelicServlet gaelicServlet, ServletConfig servletConfig) {
        final AbstractPath root = new AbstractPath();
        root.setName("root");
        
        final AbstractPath api = new AbstractPath();
        api.setName("api");
        root.addChild("api", api);
        
//        final AbstractPath domain = new AbstractPath();
        final InterceptorAdapter domain = new InterceptorAdapter();
        domain.setName("{domain}");
        api.addChild("{domain}", domain);
        
        final Node leaf = new Node();
        leaf.setName("getPage()");
        domain.addChild("leaf", leaf);
        
        return root;
    }

}
