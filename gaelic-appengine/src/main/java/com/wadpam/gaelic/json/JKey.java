/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.json;

/**
 *
 * @author sosandstrom
 */
public class JKey {
    private JKey parentKey;
    private String kind;
    private Long id;
    private String name;

    @Override
    public String toString() {
        return String.format("%s{%s(%s)}", getClass().getSimpleName(), kind,
                null != id ? id.toString() : name);
    }
    
    public JKey getParentKey() {
        return parentKey;
    }

    public void setParentKey(JKey parentKey) {
        this.parentKey = parentKey;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
