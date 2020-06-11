/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amon.db;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "tb_transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbTransaction.findAll", query = "SELECT t FROM TbTransaction t")
    , @NamedQuery(name = "TbTransaction.findById", query = "SELECT t FROM TbTransaction t WHERE t.id = :id")
    , @NamedQuery(name = "TbTransaction.findByAmount", query = "SELECT t FROM TbTransaction t WHERE t.amount = :amount")
    , @NamedQuery(name = "TbTransaction.findByCreatedOn", query = "SELECT t FROM TbTransaction t WHERE t.createdOn = :createdOn")
    , @NamedQuery(name = "TbTransaction.findByIsReconciled", query = "SELECT t FROM TbTransaction t WHERE t.isReconciled = :isReconciled")})
public class TbTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "Id")
    private String id;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "MobileNumber")
    private String mobileNumber;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "ReferenceNumber")
    private String referenceNumber;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Amount")
    private BigDecimal amount;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "MerchantID")
    private String merchantID;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "TillNumber")
    private String tillNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IsReconciled")
    private boolean isReconciled;

    public TbTransaction() {
    }

    public TbTransaction(String id) {
        this.id = id;
    }

    public TbTransaction(String id, BigDecimal amount, Date createdOn, boolean isReconciled) {
        this.id = id;
        this.amount = amount;
        this.createdOn = createdOn;
        this.isReconciled = isReconciled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public String getTillNumber() {
        return tillNumber;
    }

    public void setTillNumber(String tillNumber) {
        this.tillNumber = tillNumber;
    }

    public boolean getIsReconciled() {
        return isReconciled;
    }

    public void setIsReconciled(boolean isReconciled) {
        this.isReconciled = isReconciled;
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
        if (!(object instanceof TbTransaction)) {
            return false;
        }
        TbTransaction other = (TbTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.TbTransaction[ id=" + id + " ]";
    }
    
}
