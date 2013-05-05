/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.domain;

import java.util.Date;

/**
 *
 * @author sosandstrom
 */
public class DDate {

    private Long id;
    private Date startDate;

    public DDate() {
    }

    public DDate(Long id, Date startDate) {
        this.id = id;
        this.startDate = startDate;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    
}
