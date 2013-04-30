/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic;

import com.wadpam.gaelic.config.ConfigBuilder;
import javax.servlet.ServletConfig;

/**
 *
 * @author sosandstrom
 */
public interface GaelicConfig {
    Node init(GaelicServlet gaelicServlet, ServletConfig servletConfig);
    
    static final ConfigBuilder BUILDER = new ConfigBuilder();
}
