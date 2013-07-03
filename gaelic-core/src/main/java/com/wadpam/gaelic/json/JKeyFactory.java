/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wadpam.gaelic.json;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sosandstrom
 */
public class JKeyFactory {

    public static final String DELIMITER = "/";
    
    public static JKey createKey(String keyString) {
        final LinkedList<String> pathList = parsePath(keyString, "");
        return createKey(pathList, 0, null);
    }
    
    public static JKey createKey(List<String> pathList, int pathIndex, String kind) {
        int i = pathIndex;
        
        // Entity key first:
        final JKey key = new JKey();
        key.setKind(null != kind ? kind : pathList.get(i));
        
        // resource/{id} or just resource/ ?
        if (0 == (pathList.size() - pathIndex) %2) {
            key.setId(pathList.get(i+1));
            i+=2;
        }
        else {
            i++;
        }
        
        // then parent keys
        JKey childKey = key, parentKey;
        for ( ; i < pathList.size(); i += 2) {
            parentKey = new JKey();
            parentKey.setKind(pathList.get(i));
            parentKey.setId(pathList.get(i+1));
            
            childKey.setParentKey(parentKey);
            childKey = parentKey;
        }
        
        return key;
    }
    
    public static String createString(final JKey key) {
        final StringBuffer sb = new StringBuffer();
        JKey k = key;
        
        while (null != k) {
            sb.append(DELIMITER);
            sb.append(k.getKind());
            if (null != k.getId() || k != key) {
                sb.append(DELIMITER);
                sb.append(k.getId());
            }
            
            k = k.getParentKey();
        }
        
        return sb.toString();
    }
    
    public static LinkedList<String> parsePath(final String uri) {
        return parsePath(uri, "");
    }
    
    public static LinkedList<String> parsePath(final String uri, final String ctxt) {
        final LinkedList<String> pathStack = new LinkedList<String>();

        // if app is deployed with context != / then skip the first path
        for (String p : uri.substring(ctxt.length()).split(DELIMITER)) {
            if (!"".equals(p)) {
                pathStack.add(p);
            }
        }
        return pathStack;
    }

}
