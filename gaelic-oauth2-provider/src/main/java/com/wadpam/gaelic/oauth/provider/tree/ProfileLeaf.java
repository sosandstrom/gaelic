/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.provider.tree;

import com.wadpam.gaelic.converter.MardaoConverter;
import com.wadpam.gaelic.oauth.provider.domain.Do2pProfile;
import com.wadpam.gaelic.oauth.provider.json.Jo2pProfile;
import com.wadpam.gaelic.oauth.provider.service.ProfileService;
import com.wadpam.gaelic.tree.CrudLeaf;

/**
 *
 * @author sosandstrom
 */
public class ProfileLeaf extends CrudLeaf<Jo2pProfile, Do2pProfile, ProfileService> {
    
    public static final ProfileConverter CONVERTER = new ProfileConverter();

    public ProfileLeaf() {
        super(Do2pProfile.class, Long.class, Jo2pProfile.class);
        setConverter(CONVERTER);
    }

    public static class ProfileConverter extends MardaoConverter<Jo2pProfile, Do2pProfile> {

        public ProfileConverter() {
            super(Jo2pProfile.class, Do2pProfile.class);
        }

        @Override
        public void convertDomain(Do2pProfile from, Jo2pProfile to) {
            convertLongEntity(from, to);
            to.setUsername(from.getUsername());
            to.setEmail(from.getEmail());
            to.setState(from.getState());
        }

        @Override
        public void convertJson(Jo2pProfile from, Do2pProfile to) {
            convertJLong(from, to);
            to.setUsername(from.getUsername());
            to.setEmail(from.getEmail());
            to.setState(from.getState());
            to.setSecret(from.getPassword());
        }
        
    }
}
