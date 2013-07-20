/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.config;

import com.wadpam.gaelic.Node;
import static com.wadpam.gaelic.config.ConfigBuilder.LOG;
import com.wadpam.gaelic.tree.NodeDelegate;

/**
 *
 * @author sosandstrom
 */
public class DelegateBuilder extends ConfigBuilder {

    public DelegateBuilder(Node node) {
        super(node);
    }

    @Override
    public ConfigBuilder add(String ignored, Node child) {
        LOG.trace("adding child {} to this {} for ignored path {}", new Object[] {
            child, node, ignored});
        ((NodeDelegate) node).setDelegate(child);
        ConfigBuilder builder = to(child);
        mapBuilder(ignored, builder);
        return builder;
    }

}
