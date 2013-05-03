/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.exception;

/**
 *
 * @author sosandstrom
 */
public class BadRequestException extends RestException {
    
    public static final String MESSAGE = "Bad Request";

    public BadRequestException(int code, String developerMessage, String moreInfo) {
        super(STATUS_BAD_REQUEST, MESSAGE, code, developerMessage, moreInfo);
    }

    public BadRequestException() {
        super(STATUS_BAD_REQUEST, MESSAGE);
    }

}
