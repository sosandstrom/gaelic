/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.security;

import java.util.Collection;

/**
 *
 * @author sosandstrom
 */
public interface SecurityDetails {
    String getUsername();
    String getPassword();
    Object getUserKey();
    Collection<String> getRoles();
}
