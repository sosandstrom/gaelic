/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.json;

import java.io.Serializable;

/**
 *
 * @author sosandstrom
 */
public class JDate extends JBaseObject implements Serializable {

    private Long startDate;

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }
    
    
}
