#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * INSERT COPYRIGHT HERE
 */

package ${package}.json;

import com.wadpam.gaelic.json.JBaseObject;

/**
 *
 * @author sosandstrom
 */
public class JDonation extends JBaseObject {

    private Long userId;

    private String comment;
    
    private Long donationDate;

    private Short hb;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Long donationDate) {
        this.donationDate = donationDate;
    }

    public Short getHb() {
        return hb;
    }

    public void setHb(Short hb) {
        this.hb = hb;
    }

    
}
