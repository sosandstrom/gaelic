/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.exception;

/**
 *
 * @author sosandstrom
 */
public class RestException extends RuntimeException {
    
    public static final int STATUS_BAD_REQUEST = 400;
    public static final int STATUS_UNAUTHORIZED = 401;
    public static final int STATUS_FORBIDDEN = 403;
    public static final int STATUS_NOT_FOUND = 404;
    public static final int STATUS_METHOD_NOT_ALLOWED = 405;
    public static final int STATUS_CONFLICT = 409;
    public static final int STATUS_INTERNAL_SERVER_ERROR = 500;
    
    public static final BadRequestException BAD_REQUEST = new BadRequestException();
    public static final NotFoundException NOT_FOUND = new NotFoundException();
    
    private int status;
    private int code;
    private String developerMessage;
    private String moreInfo;

    public RestException(int status, String message, int code, String developerMessage, String moreInfo) {
        super(message);
        this.status = status;
        this.code = code;
        this.developerMessage = developerMessage;
        this.moreInfo = moreInfo;
    }

    public RestException(int status, String message) {
        super(message);
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%s{status:%d, message:%s, code:%d, devMsg:%s}", 
                getClass().getSimpleName(), status, getMessage(), code, developerMessage);
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
    
}
