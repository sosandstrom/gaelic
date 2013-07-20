/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.tree;

import com.wadpam.gaelic.Node;
import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public class NodeDelegate extends Node {
    
    private Node delegate;

    @Override
    public Node getServingNode(HttpServletRequest request, LinkedList<String> pathList, int pathIndex) {
        LOG.trace("mapping {} for {}({}) delegate is {}", new Object[] {
            request.getRequestURI(), pathIndex, pathList.size(), delegate});
        return delegate.getServingNode(request, pathList, pathIndex);
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        delegate.service(req, resp);
    }

    public void setDelegate(Node delegate) {
        this.delegate = delegate;
    }

}
