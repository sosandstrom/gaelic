#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * INSERT COPYRIGHT HERE
 */

package ${package}.domain;

import com.wadpam.gaelic.oauth.domain.DOAuth2User;
import javax.persistence.Basic;
import javax.persistence.Entity;

/**
 *
 * @author sosandstrom
 */
@Entity
public class DProfile extends DOAuth2User {
    
    @Basic
    private String phoneNumber;
    
    @Basic
    private String ab0;

    public String getAb0() {
        return ab0;
    }

    public void setAb0(String ab0) {
        this.ab0 = ab0;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
}
