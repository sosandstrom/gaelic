/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.exception;

/**
 *
 * @author sosandstrom
 */
public class ForbiddenException extends RestException {
    
    public static final String MESSAGE = "Forbidden";

    public ForbiddenException(int code, String developerMessage, String moreInfo) {
        super(STATUS_FORBIDDEN, MESSAGE, code, developerMessage, moreInfo);
    }

    public ForbiddenException() {
        super(STATUS_FORBIDDEN, MESSAGE);
    }

}
