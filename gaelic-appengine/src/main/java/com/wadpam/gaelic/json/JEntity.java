/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.json;

import java.util.Map;

/**
 *
 * @author sosandstrom
 */
public class JEntity {
    private String kind;
    private Long id;
    private Map<String, Object> properties;
    private Map<String, String> propertyTypes;

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

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public Map<String, String> getPropertyTypes() {
        return propertyTypes;
    }

    public void setPropertyTypes(Map<String, String> propertyTypes) {
        this.propertyTypes = propertyTypes;
    }
    
}
