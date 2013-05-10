/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.json;

/**
 *
 * @author sosandstrom
 */
public class RestResponse<J> {
    
    private int status;
    private J body;

    public RestResponse() {
    }

    public RestResponse(int status, J body) {
        this.status = status;
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public J getBody() {
        return body;
    }

    public void setBody(J body) {
        this.body = body;
    }
    
}
