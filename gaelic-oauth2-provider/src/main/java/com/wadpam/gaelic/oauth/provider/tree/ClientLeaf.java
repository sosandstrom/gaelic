/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.provider.tree;

import com.wadpam.gaelic.converter.MardaoConverter;
import com.wadpam.gaelic.oauth.provider.domain.Do2pClient;
import com.wadpam.gaelic.oauth.provider.json.Jo2pClient;
import com.wadpam.gaelic.oauth.provider.service.ClientService;
import com.wadpam.gaelic.tree.CrudLeaf;

/**
 *
 * @author sosandstrom
 */
public class ClientLeaf extends CrudLeaf<Jo2pClient, Do2pClient, ClientService> {

    public static final ClientConverter CONVERTER = new ClientConverter();
    
    public ClientLeaf() {
        super(Do2pClient.class, Long.class, Jo2pClient.class);
        setConverter(CONVERTER);
    }

    public static class ClientConverter extends MardaoConverter<Jo2pClient, Do2pClient> {

        public ClientConverter() {
            super(Jo2pClient.class, Do2pClient.class);
        }

        @Override
        public void convertDomain(Do2pClient from, Jo2pClient to) {
            convertLongEntity(from, to);
            to.setDescription(from.getDescription());
            to.setName(from.getName());
            to.setRedirectUri(from.getRedirectUri());
            to.setSecret(from.getSecret());
        }

        @Override
        public void convertJson(Jo2pClient from, Do2pClient to) {
            convertJLong(from, to);
            to.setDescription(from.getDescription());
            to.setName(from.getName());
            to.setRedirectUri(from.getRedirectUri());
            to.setSecret(from.getSecret());
        }
        
    }
}
