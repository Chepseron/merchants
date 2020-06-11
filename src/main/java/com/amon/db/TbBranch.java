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
@Table(name = "tb_branch")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbBranch.findAll", query = "SELECT t FROM TbBranch t")
    , @NamedQuery(name = "TbBranch.findById", query = "SELECT t FROM TbBranch t WHERE t.id = :id")
    , @NamedQuery(name = "TbBranch.findByCreatedOn", query = "SELECT t FROM TbBranch t WHERE t.createdOn = :createdOn")})
public class TbBranch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "Id")
    private String id;
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
    @Column(name = "BranchCode")
    private String branchCode;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "BranchName")
    private String branchName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "CreatedBy")
    private String createdBy;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "MobileNumber")
    private String mobileNumber;

    public TbBranch() {
    }

    public TbBranch(String id) {
        this.id = id;
    }

    public TbBranch(String id, Date createdOn) {
        this.id = id;
        this.createdOn = createdOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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
        if (!(object instanceof TbBranch)) {
            return false;
        }
        TbBranch other = (TbBranch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.TbBranch[ id=" + id + " ]";
    }
    
}
