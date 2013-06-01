#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * INSERT COPYRIGHT HERE
 */

package ${package}.json;

/**
 * Inherited Audit fields are related to OAuth2User.
 * @author sosandstrom
 */
public class JUserDetails extends JProfile {

    private Integer donationCount;
    private JDonation lastDonation;

    public Integer getDonationCount() {
        return donationCount;
    }

    public void setDonationCount(Integer donationCount) {
        this.donationCount = donationCount;
    }

    public JDonation getLastDonation() {
        return lastDonation;
    }

    public void setLastDonation(JDonation lastDonation) {
        this.lastDonation = lastDonation;
    }
    
}
