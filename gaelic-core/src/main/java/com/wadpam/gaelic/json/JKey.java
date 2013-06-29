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
    private String id;

    @Override
    public String toString() {
        return String.format("%s{%s(%s)}", getClass().getSimpleName(), kind, id);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
