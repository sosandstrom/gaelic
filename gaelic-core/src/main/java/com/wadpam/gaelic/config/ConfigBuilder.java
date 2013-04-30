/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.config;

import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.tree.Path;

/**
 *
 * @author sosandstrom
 */
public class ConfigBuilder {
    
    private final Node node;
    
    public ConfigBuilder() {
        this.node = null;
    }
    
    protected ConfigBuilder(Node node) {
        this.node = node;
    }
    
    public Node build() {
        return node;
    }
    
    public static ConfigBuilder to(Node node) {
        return new ConfigBuilder(node);
    }

    public ConfigBuilder path(String path) {
        return add(path, Path.class);
    }
    
    public ConfigBuilder add(String path, Class nodeClass) {
        try {
            final Node returnValue = (Node) nodeClass.newInstance();
            returnValue.setName(path);
            ((Path) node).addChild(path, returnValue);
            return to(returnValue);
        } catch (InstantiationException ex) {
            throw new RuntimeException("Building Config", ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException("Building Config", ex);
        }
    }
    
    public ConfigBuilder add(String path, Node child) {
        ((Path) node).addChild(path, child);
        return to(child);
    }
    
    public static ConfigBuilder root() {
        final Path root = new Path();
        root.setName("root");
        return to(root);
    }
    
    public ConfigBuilder named(String name) {
        node.setName(name);
        return this;
    }
}
