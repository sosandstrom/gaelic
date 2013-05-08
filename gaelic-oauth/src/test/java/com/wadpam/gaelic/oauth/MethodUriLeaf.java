/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth;

import com.wadpam.gaelic.Node;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public class MethodUriLeaf extends Node {

    public MethodUriLeaf() {
    }

    public MethodUriLeaf(String name) {
        setName(name);
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        setResponseBody(request, 200, new UnitTestBody(request.getMethod(), request.getRequestURI()));
    }

}
