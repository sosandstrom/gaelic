/*
 * INSERT COPYRIGHT HERE
 */

package com.wadpam.gaelic.oauth.provider.service;

import com.wadpam.gaelic.crud.MardaoCrudService;
import com.wadpam.gaelic.exception.ConflictException;
import com.wadpam.gaelic.oauth.provider.dao.Do2pProfileDao;
import com.wadpam.gaelic.oauth.provider.dao.Do2pProfileDaoBean;
import com.wadpam.gaelic.oauth.provider.domain.Do2pProfile;

/**
 * Encrypts the password when Creating or Updating
 * @author sosandstrom
 */
public class ProfileService extends MardaoCrudService<Do2pProfile, Long, Do2pProfileDao> {

    public ProfileService() {
        super(Do2pProfile.class, Long.class, Do2pProfileDaoBean.class);
    }
    
    protected void checkUsername(String username, Long currentUserId) {
        if (null == username) {
            throw new ConflictException(ProviderService.ERR_USERNAME_REQUIRED, "Username mandatory", null);
        }
        Long key = dao.findKeyByUsername(username);
        
        // existing user, not same as this being changed
        if (null != key && !key.equals(currentUserId)) {
            throw new ConflictException(ProviderService.ERR_USERNAME_CONFLICT, username, null);
        }
    }

    @Override
    public String create(Do2pProfile domain) {
        
        checkUsername(domain.getUsername(), domain.getId());
        
        // make sure ID is generated:
        super.create(domain);
        
        // now, encrypt the password:
        return update(domain);
    }
    
    @Override
    public String update(Do2pProfile domain) {
        
        checkUsername(domain.getUsername(), domain.getId());
        
        if (null != domain.getSecret() && 32 != domain.getSecret().length()) {
            final String hash = ProviderService.encryptPassword(domain.getSecret(), domain.getId());
            domain.setSecret(hash);
        }
        return super.update(domain);
    }

    
}
