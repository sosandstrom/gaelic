/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.tree;

import com.google.appengine.api.NamespaceManager;
import com.wadpam.gaelic.Node;
import com.wadpam.gaelic.exception.ConflictException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sosandstrom
 */
public class DomainLeaf extends Node {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setResponseBody(req, 200, NamespaceManager.get());
        if ("exception".equals(DomainNamespaceInterceptor.getDomain(req))) {
            throw new ConflictException();
        }
    }

}
