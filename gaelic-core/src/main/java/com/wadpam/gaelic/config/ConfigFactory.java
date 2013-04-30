/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.config;

import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.tree.AbstractPath;

/**
 *
 * @author sosandstrom
 */
public class ConfigFactory {
    
    private final Node node;
    
    public ConfigFactory() {
        this.node = null;
    }
    
    protected ConfigFactory(Node node) {
        this.node = node;
    }
    
    public Node build() {
        return node;
    }
    
    public static ConfigFactory to(Node node) {
        return new ConfigFactory(node);
    }

    public ConfigFactory path(String path) {
        AbstractPath returnValue = new AbstractPath();
        ((AbstractPath) node).addChild(path, returnValue);
        return to(returnValue);
    }
    
    public static ConfigFactory root() {
        final AbstractPath root = new AbstractPath();
        root.setName("root");
        return to(root);
    }
    
    public ConfigFactory named(String name) {
        node.setName(name);
        return this;
    }
}
