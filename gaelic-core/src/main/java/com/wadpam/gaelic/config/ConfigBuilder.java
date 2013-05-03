/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.config;

import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.tree.CrudLeaf;
import com.wadpam.gaelic.tree.InterceptedPath;
import com.wadpam.gaelic.tree.Interceptor;
import com.wadpam.gaelic.tree.Path;
import java.util.TreeMap;

/**
 *
 * @author sosandstrom
 */
public class ConfigBuilder {
    protected static final String NAME_ROOT = "root";
    protected static final TreeMap<String,ConfigBuilder> BUILDER_MAP = new TreeMap<String, ConfigBuilder>();
    protected static final TreeMap<String,Node> NODE_MAP = new TreeMap<String, Node>();
    
    private final Node node;
    
    public ConfigBuilder() {
        this.node = null;
    }
    
    protected ConfigBuilder(Node node) {
        this.node = node;
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
        ConfigBuilder builder = to(child);
        mapBuilder(path, builder);
        return builder;
    }
    
    public ConfigBuilder add(String path, String nodeName) {
        Node child = get(nodeName);
        return add(path, child);
    }
    
    public Node build() {
        return node != null ? node : NODE_MAP.get(NAME_ROOT);
    }
    
    public ConfigBuilder crud(String version, Class idClass) {
        CrudLeaf crud = new CrudLeaf(idClass, idClass, idClass);
        return add(version, crud);
    }
    
    public static ConfigBuilder from(String name) {
        return BUILDER_MAP.get(name);
    }
    
    public static Node get(String name) {
        return NODE_MAP.get(name);
    }

    public ConfigBuilder interceptedPath(String path, Interceptor interceptor) {
        InterceptedPath p = new InterceptedPath();
        p.setInterceptor(interceptor);
        return add(path, p);
    }
    
    protected static void mapBuilder(ConfigBuilder builder) {
        if (null != builder && null != builder.node) {
            mapBuilder(builder.node.getName(), builder);
        }
    }
    
    protected static void mapBuilder(String path, ConfigBuilder builder) {
        if (null != builder && null != builder.node) {
            if (null != path) {
                NODE_MAP.put(path, builder.node);
                BUILDER_MAP.put(path, builder);
            }
        }
    }
    
    public ConfigBuilder named(String name) {
        node.setName(name);
        
        // add by new name. remove old?
        mapBuilder(this);
        
        return this;
    }
    
    public ConfigBuilder path(String path) {
        return add(path, Path.class);
    }
    
    public static ConfigBuilder root() {
        NODE_MAP.clear();
        BUILDER_MAP.clear();
        final Path root = new Path();
        root.setName(NAME_ROOT);
        ConfigBuilder builder = to(root);
        mapBuilder(builder);
        return builder;
    }
    
    public static ConfigBuilder to(Node node) {
        return new ConfigBuilder(node);
    }
}
