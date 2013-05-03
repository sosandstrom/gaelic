package com.wadpam.gaelic.json;

import java.io.Serializable;

/**
 *
 * @author sosandstrom
 */
public class JBaseObject implements Serializable {
    
    /** Unique id for this Entity in the database */
    private String id;
    
    /** Set by mardao to whom this entity was created by */
    private String createdBy;
    
    /** Milliseconds since 1970 when this Entity was created in the database */
    private Long createdDate;
    
    /** Set by mardao to whom this entity was updated by last time */
    private String updatedBy;
    
    /** Milliseconds since 1970 when this Entity was last updated in the database */
    private Long updatedDate;

    public JBaseObject() {
    }

    public JBaseObject(String id, Long createdDate, Long updatedDate) {
        this.id = id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public JBaseObject(String id, String createdBy, Long createdDate, String updatedBy, Long updatedDate) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
    }
    
    @Override
    public String toString() {
        return String.format("%s{id:%s, updatedDate:%s, %s}", getClass().getSimpleName(), id, updatedDate, subString());
    }
    
    protected String subString() { 
        return ""; 
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Long updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

}
