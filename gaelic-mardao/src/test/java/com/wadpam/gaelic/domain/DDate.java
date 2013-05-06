/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.domain;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import net.sf.mardao.core.domain.AbstractLongEntity;

/**
 *
 * @author sosandstrom
 */
@Entity
public class DDate extends AbstractLongEntity {

    @Basic
    private Date startDate;

    public DDate() {
    }

    public DDate(Long id, Date startDate) {
        setId(id);
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    
}
