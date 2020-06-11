/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amon.db;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amon.Sabul
 */
@Entity
@Table(name = "tb_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbUser.findAll", query = "SELECT t FROM TbUser t")
    , @NamedQuery(name = "TbUser.findById", query = "SELECT t FROM TbUser t WHERE t.id = :id")
    , @NamedQuery(name = "TbUser.findByLastPasswordChangeOn", query = "SELECT t FROM TbUser t WHERE t.lastPasswordChangeOn = :lastPasswordChangeOn")
    , @NamedQuery(name = "TbUser.findByTimeLoggedIn", query = "SELECT t FROM TbUser t WHERE t.timeLoggedIn = :timeLoggedIn")
    , @NamedQuery(name = "TbUser.findByTimeLoggedOut", query = "SELECT t FROM TbUser t WHERE t.timeLoggedOut = :timeLoggedOut")
    , @NamedQuery(name = "TbUser.findByIsLoginDisabled", query = "SELECT t FROM TbUser t WHERE t.isLoginDisabled = :isLoginDisabled")
    , @NamedQuery(name = "TbUser.findByCreatedOn", query = "SELECT t FROM TbUser t WHERE t.createdOn = :createdOn")
    , @NamedQuery(name = "TbUser.findByModifiedOn", query = "SELECT t FROM TbUser t WHERE t.modifiedOn = :modifiedOn")
    , @NamedQuery(name = "TbUser.findBySupervisedOn", query = "SELECT t FROM TbUser t WHERE t.supervisedOn = :supervisedOn")
    , @NamedQuery(name = "TbUser.findByIsSupervised", query = "SELECT t FROM TbUser t WHERE t.isSupervised = :isSupervised")
    , @NamedQuery(name = "TbUser.findByMustChangePassword", query = "SELECT t FROM TbUser t WHERE t.mustChangePassword = :mustChangePassword")
    , @NamedQuery(name = "TbUser.findByEmail", query = "SELECT t FROM TbUser t WHERE t.email = :email")
    , @NamedQuery(name = "TbUser.findByEmailConfirmed", query = "SELECT t FROM TbUser t WHERE t.emailConfirmed = :emailConfirmed")
    , @NamedQuery(name = "TbUser.findByPhoneNumberConfirmed", query = "SELECT t FROM TbUser t WHERE t.phoneNumberConfirmed = :phoneNumberConfirmed")
    , @NamedQuery(name = "TbUser.findByTwoFactorEnabled", query = "SELECT t FROM TbUser t WHERE t.twoFactorEnabled = :twoFactorEnabled")
    , @NamedQuery(name = "TbUser.findByLockoutEndDateUtc", query = "SELECT t FROM TbUser t WHERE t.lockoutEndDateUtc = :lockoutEndDateUtc")
    , @NamedQuery(name = "TbUser.findByLockoutEnabled", query = "SELECT t FROM TbUser t WHERE t.lockoutEnabled = :lockoutEnabled")
    , @NamedQuery(name = "TbUser.findByAccessFailedCount", query = "SELECT t FROM TbUser t WHERE t.accessFailedCount = :accessFailedCount")
    , @NamedQuery(name = "TbUser.findByUserName", query = "SELECT t FROM TbUser t WHERE t.userName = :userName")
    , @NamedQuery(name = "TbUser.findByOtp", query = "SELECT t FROM TbUser t WHERE t.otp = :otp")
    , @NamedQuery(name = "TbUser.findByStatus", query = "SELECT t FROM TbUser t WHERE t.status = :status")})
public class TbUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "Id")
    private String id;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "FirstName")
    private String firstName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "MiddleName")
    private String middleName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "LastName")
    private String lastName;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Country")
    private String country;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "MerchantID")
    private String merchantID;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "BranchID")
    private String branchID;
    @Column(name = "LastPasswordChangeOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordChangeOn;
    @Column(name = "TimeLoggedIn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeLoggedIn;
    @Column(name = "TimeLoggedOut")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeLoggedOut;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IsLoginDisabled")
    private boolean isLoginDisabled;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "CreatedBy")
    private String createdBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "ModifiedBy")
    private String modifiedBy;
    @Column(name = "ModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "SupervisedBy")
    private String supervisedBy;
    @Column(name = "SupervisedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date supervisedOn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IsSupervised")
    private boolean isSupervised;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MustChangePassword")
    private boolean mustChangePassword;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 512)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EmailConfirmed")
    private boolean emailConfirmed;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "PasswordHash")
    private String passwordHash;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "SecurityStamp")
    private String securityStamp;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PhoneNumberConfirmed")
    private boolean phoneNumberConfirmed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TwoFactorEnabled")
    private boolean twoFactorEnabled;
    @Column(name = "LockoutEndDateUtc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lockoutEndDateUtc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LockoutEnabled")
    private boolean lockoutEnabled;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AccessFailedCount")
    private int accessFailedCount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "UserName")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "otp")
    private String otp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;

    public TbUser() {
    }

    public TbUser(String id) {
        this.id = id;
    }

    public TbUser(String id, boolean isLoginDisabled, Date createdOn, boolean isSupervised, boolean mustChangePassword, boolean emailConfirmed, boolean phoneNumberConfirmed, boolean twoFactorEnabled, boolean lockoutEnabled, int accessFailedCount, String userName, String otp, int status) {
        this.id = id;
        this.isLoginDisabled = isLoginDisabled;
        this.createdOn = createdOn;
        this.isSupervised = isSupervised;
        this.mustChangePassword = mustChangePassword;
        this.emailConfirmed = emailConfirmed;
        this.phoneNumberConfirmed = phoneNumberConfirmed;
        this.twoFactorEnabled = twoFactorEnabled;
        this.lockoutEnabled = lockoutEnabled;
        this.accessFailedCount = accessFailedCount;
        this.userName = userName;
        this.otp = otp;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public Date getLastPasswordChangeOn() {
        return lastPasswordChangeOn;
    }

    public void setLastPasswordChangeOn(Date lastPasswordChangeOn) {
        this.lastPasswordChangeOn = lastPasswordChangeOn;
    }

    public Date getTimeLoggedIn() {
        return timeLoggedIn;
    }

    public void setTimeLoggedIn(Date timeLoggedIn) {
        this.timeLoggedIn = timeLoggedIn;
    }

    public Date getTimeLoggedOut() {
        return timeLoggedOut;
    }

    public void setTimeLoggedOut(Date timeLoggedOut) {
        this.timeLoggedOut = timeLoggedOut;
    }

    public boolean getIsLoginDisabled() {
        return isLoginDisabled;
    }

    public void setIsLoginDisabled(boolean isLoginDisabled) {
        this.isLoginDisabled = isLoginDisabled;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getSupervisedBy() {
        return supervisedBy;
    }

    public void setSupervisedBy(String supervisedBy) {
        this.supervisedBy = supervisedBy;
    }

    public Date getSupervisedOn() {
        return supervisedOn;
    }

    public void setSupervisedOn(Date supervisedOn) {
        this.supervisedOn = supervisedOn;
    }

    public boolean getIsSupervised() {
        return isSupervised;
    }

    public void setIsSupervised(boolean isSupervised) {
        this.isSupervised = isSupervised;
    }

    public boolean getMustChangePassword() {
        return mustChangePassword;
    }

    public void setMustChangePassword(boolean mustChangePassword) {
        this.mustChangePassword = mustChangePassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSecurityStamp() {
        return securityStamp;
    }

    public void setSecurityStamp(String securityStamp) {
        this.securityStamp = securityStamp;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getPhoneNumberConfirmed() {
        return phoneNumberConfirmed;
    }

    public void setPhoneNumberConfirmed(boolean phoneNumberConfirmed) {
        this.phoneNumberConfirmed = phoneNumberConfirmed;
    }

    public boolean getTwoFactorEnabled() {
        return twoFactorEnabled;
    }

    public void setTwoFactorEnabled(boolean twoFactorEnabled) {
        this.twoFactorEnabled = twoFactorEnabled;
    }

    public Date getLockoutEndDateUtc() {
        return lockoutEndDateUtc;
    }

    public void setLockoutEndDateUtc(Date lockoutEndDateUtc) {
        this.lockoutEndDateUtc = lockoutEndDateUtc;
    }

    public boolean getLockoutEnabled() {
        return lockoutEnabled;
    }

    public void setLockoutEnabled(boolean lockoutEnabled) {
        this.lockoutEnabled = lockoutEnabled;
    }

    public int getAccessFailedCount() {
        return accessFailedCount;
    }

    public void setAccessFailedCount(int accessFailedCount) {
        this.accessFailedCount = accessFailedCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbUser)) {
            return false;
        }
        TbUser other = (TbUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.TbUser[ id=" + id + " ]";
    }
    
}
