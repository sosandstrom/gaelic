/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.json;

import java.util.Map;

/**
 *
 * @author sosandstrom
 */
public class JEntity extends JKey {
    private Map<String, Object> properties;
    private Map<String, String> propertyTypes;

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
