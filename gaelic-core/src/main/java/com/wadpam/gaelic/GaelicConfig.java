/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic;

import com.wadpam.gaelic.config.ConfigFactory;
import javax.servlet.ServletConfig;

/**
 *
 * @author sosandstrom
 */
public interface GaelicConfig {
    Node init(GaelicServlet gaelicServlet, ServletConfig servletConfig);
    
    static final ConfigFactory FACTORY = new ConfigFactory();
}
