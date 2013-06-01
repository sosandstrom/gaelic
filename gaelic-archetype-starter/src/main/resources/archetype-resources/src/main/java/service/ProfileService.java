#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * INSERT COPYRIGHT HERE
 */

package ${package}.service;

import ${package}.dao.DProfileDao;
import ${package}.dao.DProfileDaoBean;
import ${package}.domain.DProfile;
import com.wadpam.gaelic.oauth.service.OAuth2UserServiceImpl;

/**
 *
 * @author sosandstrom
 */
public class ProfileService extends OAuth2UserServiceImpl<DProfile, DProfileDao> {

    public ProfileService() {
        super(DProfile.class, DProfileDaoBean.class);
    }

}
