/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.exception;

/**
 *
 * @author sosandstrom
 */
public class AuthenticationFailedException extends RestException {
    
    public static final String MESSAGE = "Unauthorized";

    public AuthenticationFailedException(int code, String developerMessage, String moreInfo) {
        super(STATUS_UNAUTHORIZED, MESSAGE, code, developerMessage, moreInfo);
    }

    public AuthenticationFailedException() {
        super(STATUS_UNAUTHORIZED, MESSAGE);
    }

}
