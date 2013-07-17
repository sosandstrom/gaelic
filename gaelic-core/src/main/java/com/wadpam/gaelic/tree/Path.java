/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.tree;

import com.wadpam.gaelic.Node;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public class Path extends Node {
    
    private final TreeMap<String,Node> paths = new TreeMap<String,Node>();
    
    private final ThreadLocal<Node> servingChild = new ThreadLocal<Node>();
    
    public void addChild(final String path, final Node child) {
        paths.put(path, child);
    }
    
    @Override
    public void initNode(ServletConfig config, Node parent) throws ServletException, IOException {
        super.initNode(config, parent);
        
        for (Node child : paths.values()) {
            child.initNode(config, this);
        }
    }

    @Override
    public Node getServingNode(HttpServletRequest request, LinkedList<String> pathList, int pathIndex) {
        currentRequest.set(request);
        servingChild.remove();
        
        if (pathList.isEmpty()) {
            return null;
        }
        
        final String path = pathList.get(pathIndex);
        LOG.trace("mapping {} for {}({})", new Object[] {
            path, pathIndex, pathList.size()});
        String p;
        
        Node child;
        Node candidate = null;
        for (Entry<String, Node> entry : paths.entrySet()) {
            p = entry.getKey();
            if ((p.startsWith("{") && p.endsWith("}")) || p.equals(path)) {
                
                child = entry.getValue();
                candidate = child.getServingNode(request, pathList, pathIndex+1);
                if (null != candidate) {
                    servingChild.set(child);
                    
                    // store path variable?
                    if (p.startsWith("{") && p.endsWith("}")) {
                        setPathVariable(p, path);
                    }
                }
            }
        }
        return candidate;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        servingChild.get().service(request, response);
    }
    
}
