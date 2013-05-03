/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.json;

import com.wadpam.gaelic.exception.RestException;
import java.io.Serializable;

/**
 *
 * @author sosandstrom
 */
public class JException implements Serializable {
    private int code;
    private int status;
    private String message;
    private String developerMessage;
    private String moreInfo;
    private String stackTrace;

    public JException() {
    }

    public JException(Exception exception) {
        this.code = RestException.STATUS_INTERNAL_SERVER_ERROR;
        this.message = exception.getMessage();
        buildStackTrace(exception);
    }

    public JException(RestException exception) {
        this.code = exception.getCode();
        this.status = exception.getStatus();
        this.message = exception.getMessage();
        this.developerMessage = exception.getDeveloperMessage();
        this.moreInfo = exception.getMoreInfo();
        buildStackTrace(exception);
    }
    
    protected final String buildStackTrace(Exception ex) {
        final StringBuffer sb = new StringBuffer();
        int depth = 0;
        for (StackTraceElement ste : ex.getStackTrace()) {
            sb.append(ste.getClassName());
            sb.append('.');
            sb.append(ste.getMethodName());
            sb.append(':');
            sb.append(ste.getLineNumber());
            
            depth++;
            if (2 == depth) {
                break;
            }
            sb.append(" | ");
        }
        this.stackTrace = sb.toString();
        return this.stackTrace;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
