/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.exception;

/**
 *
 * @author sosandstrom
 */
public class NotFoundException extends RestException {
    
    public static final String MESSAGE = "Not Found";

    public NotFoundException(int code, String developerMessage, String moreInfo) {
        super(STATUS_NOT_FOUND, MESSAGE, code, developerMessage, moreInfo);
    }

    public NotFoundException() {
        super(STATUS_NOT_FOUND, MESSAGE);
    }

}
