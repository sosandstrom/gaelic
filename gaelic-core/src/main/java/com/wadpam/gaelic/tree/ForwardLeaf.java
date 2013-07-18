/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.tree;

import com.wadpam.gaelic.Node;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public class ForwardLeaf extends Node {
    
    private String forwardPath;

    public ForwardLeaf() {
    }

    public ForwardLeaf(String forwardPath) {
        this.forwardPath = forwardPath;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
        forward(req, resp, forwardPath);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
        forward(req, resp, forwardPath);
    }
    
    /**
     * Override to add functionality
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
    
    public void setForwardPath(String forwardPath) {
        this.forwardPath = forwardPath;
    }

}
