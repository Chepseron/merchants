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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amon.Sabul
 */
@Entity
@Table(name = "tb_umipayments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbUmipayments.findAll", query = "SELECT t FROM TbUmipayments t")
    , @NamedQuery(name = "TbUmipayments.findByMerchantCode", query = "SELECT t FROM TbUmipayments t WHERE t.merchantCode = :merchantCode")
    , @NamedQuery(name = "TbUmipayments.findByMerchantReference", query = "SELECT t FROM TbUmipayments t WHERE t.merchantReference = :merchantReference")
    , @NamedQuery(name = "TbUmipayments.findByCreatedOn", query = "SELECT t FROM TbUmipayments t WHERE t.createdOn = :createdOn")
    , @NamedQuery(name = "TbUmipayments.findByIdUmipayments", query = "SELECT t FROM TbUmipayments t WHERE t.idUmipayments = :idUmipayments")})
public class TbUmipayments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 25)
    @Column(name = "MerchantCode")
    private String merchantCode;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "PaymentData")
    private String paymentData;
    @Size(max = 10)
    @Column(name = "MerchantReference")
    private String merchantReference;
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_umipayments")
    private Integer idUmipayments;

    public TbUmipayments() {
    }

    public TbUmipayments(Integer idUmipayments) {
        this.idUmipayments = idUmipayments;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(String paymentData) {
        this.paymentData = paymentData;
    }

    public String getMerchantReference() {
        return merchantReference;
    }

    public void setMerchantReference(String merchantReference) {
        this.merchantReference = merchantReference;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getIdUmipayments() {
        return idUmipayments;
    }

    public void setIdUmipayments(Integer idUmipayments) {
        this.idUmipayments = idUmipayments;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUmipayments != null ? idUmipayments.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbUmipayments)) {
            return false;
        }
        TbUmipayments other = (TbUmipayments) object;
        if ((this.idUmipayments == null && other.idUmipayments != null) || (this.idUmipayments != null && !this.idUmipayments.equals(other.idUmipayments))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.TbUmipayments[ idUmipayments=" + idUmipayments + " ]";
    }
    
}
