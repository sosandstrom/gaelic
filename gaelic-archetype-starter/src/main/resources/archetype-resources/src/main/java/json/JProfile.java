#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * INSERT COPYRIGHT HERE
 */

package ${package}.json;

import com.wadpam.gaelic.oauth.json.JOAuth2User;

/**
 *
 * @author sosandstrom
 */
public class JProfile extends JOAuth2User {

    private String phoneNumber;
    
    private String ab0;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAb0() {
        return ab0;
    }

    public void setAb0(String ab0) {
        this.ab0 = ab0;
    }
    
}
