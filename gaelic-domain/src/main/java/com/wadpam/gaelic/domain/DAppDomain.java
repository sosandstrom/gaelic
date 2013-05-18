package com.wadpam.gaelic.domain;


import com.wadpam.gaelic.security.SecurityDetails;
import com.wadpam.gaelic.security.SecurityDetailsService;
import java.util.Arrays;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import net.sf.mardao.core.domain.AbstractStringEntity;

/**
 * Domain object containing domain/app properties.
 * @author mattiaslevin
 */
@Entity
@Table(uniqueConstraints={
    @UniqueConstraint(columnNames = {"username"})})
public class DAppDomain extends AbstractStringEntity 
        implements SecurityDetails {

    /** Short description of the domain */
    @Basic
    private String description;

    /** The user name used in basic authentication */
    @Basic
    private String username;

    /** The password used in basic authentication */
    @Basic
    private String password;

    /** Analytics tracking code, e.g. Google Analytics */
    @Basic
    private String analyticsTrackingCode;

    /** Contact email that can be used for email send from GAE */
    @Basic
    private String email;

    /** Context specific app property */
    @Basic
    private String appArg1;

    /** Context specific app property */
    @Basic
    private String appArg2;

    public static final Collection<String> ROLES_APPLICATION = Arrays.asList(SecurityDetailsService.ROLE_APPLICATION);

    @Override
    public String toString() {
        return String.format("Domain:%s, description:%s, tracking code:%s",
                getId(), description, analyticsTrackingCode);
    }


    // Setters and getters
    public String getAnalyticsTrackingCode() {
        return analyticsTrackingCode;
    }

    public void setAnalyticsTrackingCode(String analyticsTrackingCode) {
        this.analyticsTrackingCode = analyticsTrackingCode;
    }

    public String getAppArg1() {
        return appArg1;
    }

    public void setAppArg1(String appArg1) {
        this.appArg1 = appArg1;
    }

    public String getAppArg2() {
        return appArg2;
    }

    public void setAppArg2(String appArg2) {
        this.appArg2 = appArg2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Collection<String> getRoles() {
        return ROLES_APPLICATION;
    }

    @Override
    public Object getUserKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
