/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic;

import javax.servlet.ServletConfig;

/**
 *
 * @author sosandstrom
 */
public interface GaelicConfig {
    Node init(GaelicServlet gaelicServlet, ServletConfig servletConfig);
}
