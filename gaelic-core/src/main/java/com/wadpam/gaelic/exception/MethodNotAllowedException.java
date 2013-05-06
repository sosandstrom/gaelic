/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.exception;

import java.util.Collection;

/**
 *
 * @author sosandstrom
 */
public class MethodNotAllowedException extends RestException {
    
    public static final String MESSAGE = "Method Not Allowed";
    
    private final Collection<String> allow;

    public MethodNotAllowedException(int code, String developerMessage, String moreInfo, Collection<String> allow) {
        super(STATUS_METHOD_NOT_ALLOWED, MESSAGE, code, developerMessage, moreInfo);
        this.allow = allow;
    }

    public MethodNotAllowedException(Collection<String> allow) {
        super(STATUS_METHOD_NOT_ALLOWED, MESSAGE);
        this.allow = allow;
    }

    public Collection<String> getAllow() {
        return allow;
    }

}
