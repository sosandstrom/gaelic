/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.config;

import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.tree.Path;
import java.util.TreeMap;

/**
 *
 * @author sosandstrom
 */
public class ConfigBuilder {
    
    protected static final TreeMap<String,ConfigBuilder> BUILDER_MAP = new TreeMap<String, ConfigBuilder>();
    protected static final TreeMap<String,Node> NODE_MAP = new TreeMap<String, Node>();
    
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
            final Node child = (Node) nodeClass.newInstance();
            child.setName(path);
            
            ((Path) node).addChild(path, child);
            final ConfigBuilder builder = new ConfigBuilder(child);
            mapBuilder(builder);
            
            return builder;
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
    
    public ConfigBuilder add(String path, String nodeName) {
        Node child = get(nodeName);
        return add(path, child);
    }
    
    public static ConfigBuilder root() {
        NODE_MAP.clear();
        BUILDER_MAP.clear();
        final Path root = new Path();
        root.setName("root");
        ConfigBuilder builder = to(root);
        mapBuilder(builder);
        return builder;
    }
    
    protected static void mapBuilder(ConfigBuilder builder) {
        if (null != builder && null != builder.node) {
            final String name = builder.node.getName();
            if (null != name) {
                NODE_MAP.put(name, builder.node);
                BUILDER_MAP.put(name, builder);
            }
        }
    }
    
    public ConfigBuilder named(String name) {
        node.setName(name);
        
        // add by new name. remove old?
        mapBuilder(this);
        
        return this;
    }
    
    public static ConfigBuilder from(String name) {
        return BUILDER_MAP.get(name);
    }
    
    public static Node get(String name) {
        return NODE_MAP.get(name);
    }
}
