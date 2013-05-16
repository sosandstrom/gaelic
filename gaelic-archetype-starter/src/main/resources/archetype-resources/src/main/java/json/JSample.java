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
public class JSample extends JOAuth2User {

    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
}
