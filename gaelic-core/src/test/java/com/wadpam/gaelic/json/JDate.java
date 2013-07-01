/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.json;

import java.io.Serializable;

/**
 *
 * @author sosandstrom
 */
public class JDate implements Serializable {
    private String id;
    private Long startDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return String.format("JDate{id:%s, startDate:%d}", id, startDate);
    }
    
    
}
