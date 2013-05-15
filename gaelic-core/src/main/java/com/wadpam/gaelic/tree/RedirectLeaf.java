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
public class RedirectLeaf extends Node {
    
    private String redirectPath;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
        redirect(req, resp, redirectPath);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
        redirect(req, resp, redirectPath);
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
    
    public void setRedirectPath(String redirectPath) {
        this.redirectPath = redirectPath;
    }
    
}
