/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amon.db;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amon.Sabul
 */
@Entity
@Table(name = "tb_umimerchants")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbUmimerchants.findAll", query = "SELECT t FROM TbUmimerchants t")
    , @NamedQuery(name = "TbUmimerchants.findByMerchantCode", query = "SELECT t FROM TbUmimerchants t WHERE t.merchantCode = :merchantCode")
    , @NamedQuery(name = "TbUmimerchants.findByName", query = "SELECT t FROM TbUmimerchants t WHERE t.name = :name")
    , @NamedQuery(name = "TbUmimerchants.findByLogo", query = "SELECT t FROM TbUmimerchants t WHERE t.logo = :logo")
    , @NamedQuery(name = "TbUmimerchants.findByMinimumAmount", query = "SELECT t FROM TbUmimerchants t WHERE t.minimumAmount = :minimumAmount")
    , @NamedQuery(name = "TbUmimerchants.findByMaximumAmount", query = "SELECT t FROM TbUmimerchants t WHERE t.maximumAmount = :maximumAmount")
    , @NamedQuery(name = "TbUmimerchants.findByCustomerDiscount", query = "SELECT t FROM TbUmimerchants t WHERE t.customerDiscount = :customerDiscount")
    , @NamedQuery(name = "TbUmimerchants.findByMerchantDiscount", query = "SELECT t FROM TbUmimerchants t WHERE t.merchantDiscount = :merchantDiscount")
    , @NamedQuery(name = "TbUmimerchants.findByMerchantDescription", query = "SELECT t FROM TbUmimerchants t WHERE t.merchantDescription = :merchantDescription")
    , @NamedQuery(name = "TbUmimerchants.findByCustomerDiscountType", query = "SELECT t FROM TbUmimerchants t WHERE t.customerDiscountType = :customerDiscountType")
    , @NamedQuery(name = "TbUmimerchants.findByIdmerchant", query = "SELECT t FROM TbUmimerchants t WHERE t.idmerchant = :idmerchant")})
public class TbUmimerchants implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 10)
    @Column(name = "MerchantCode")
    private String merchantCode;
    @Size(max = 100)
    @Column(name = "Name")
    private String name;
    @Size(max = 100)
    @Column(name = "Logo")
    private String logo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MinimumAmount")
    private BigDecimal minimumAmount;
    @Column(name = "MaximumAmount")
    private BigDecimal maximumAmount;
    @Column(name = "CustomerDiscount")
    private BigDecimal customerDiscount;
    @Column(name = "MerchantDiscount")
    private BigDecimal merchantDiscount;
    @Size(max = 100)
    @Column(name = "MerchantDescription")
    private String merchantDescription;
    @Column(name = "CustomerDiscountType")
    private Character customerDiscountType;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmerchant")
    private Integer idmerchant;

    public TbUmimerchants() {
    }

    public TbUmimerchants(Integer idmerchant) {
        this.idmerchant = idmerchant;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public BigDecimal getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(BigDecimal minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public BigDecimal getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(BigDecimal maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    public BigDecimal getCustomerDiscount() {
        return customerDiscount;
    }

    public void setCustomerDiscount(BigDecimal customerDiscount) {
        this.customerDiscount = customerDiscount;
    }

    public BigDecimal getMerchantDiscount() {
        return merchantDiscount;
    }

    public void setMerchantDiscount(BigDecimal merchantDiscount) {
        this.merchantDiscount = merchantDiscount;
    }

    public String getMerchantDescription() {
        return merchantDescription;
    }

    public void setMerchantDescription(String merchantDescription) {
        this.merchantDescription = merchantDescription;
    }

    public Character getCustomerDiscountType() {
        return customerDiscountType;
    }

    public void setCustomerDiscountType(Character customerDiscountType) {
        this.customerDiscountType = customerDiscountType;
    }

    public Integer getIdmerchant() {
        return idmerchant;
    }

    public void setIdmerchant(Integer idmerchant) {
        this.idmerchant = idmerchant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmerchant != null ? idmerchant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbUmimerchants)) {
            return false;
        }
        TbUmimerchants other = (TbUmimerchants) object;
        if ((this.idmerchant == null && other.idmerchant != null) || (this.idmerchant != null && !this.idmerchant.equals(other.idmerchant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.TbUmimerchants[ idmerchant=" + idmerchant + " ]";
    }
    
}
