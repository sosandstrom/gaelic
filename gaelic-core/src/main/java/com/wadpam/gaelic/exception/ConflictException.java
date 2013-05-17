/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.exception;

/**
 *
 * @author sosandstrom
 */
public class ConflictException extends RestException {
    
    public static final String MESSAGE = "Conflict";

    public ConflictException(int code, String developerMessage, String moreInfo) {
        super(STATUS_CONFLICT, MESSAGE, code, developerMessage, moreInfo);
    }

    public ConflictException() {
        super(STATUS_CONFLICT, MESSAGE);
    }

}
