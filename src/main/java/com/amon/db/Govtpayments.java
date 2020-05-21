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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author FAZOUL
 */
@Entity
@Table(name = "govtpayments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Govtpayments.findAll", query = "SELECT g FROM Govtpayments g"),
    @NamedQuery(name = "Govtpayments.findByIdgovtpayments", query = "SELECT g FROM Govtpayments g WHERE g.idgovtpayments = :idgovtpayments"),
    @NamedQuery(name = "Govtpayments.findByAmount", query = "SELECT g FROM Govtpayments g WHERE g.amount = :amount"),
    @NamedQuery(name = "Govtpayments.findByPayee", query = "SELECT g FROM Govtpayments g WHERE g.payee = :payee"),
    @NamedQuery(name = "Govtpayments.findByCreatedon", query = "SELECT g FROM Govtpayments g WHERE g.createdon = :createdon"),
    @NamedQuery(name = "Govtpayments.findByAgrodealer", query = "SELECT g FROM Govtpayments g WHERE g.agrodealer = :agrodealer"),
    @NamedQuery(name = "Govtpayments.findByVoucherpercentage", query = "SELECT g FROM Govtpayments g WHERE g.voucherpercentage = :voucherpercentage")})
public class Govtpayments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idgovtpayments")
    private Integer idgovtpayments;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "amount")
    private String amount;
    @Size(max = 200)
    @Column(name = "payee")
    private String payee;
    @Column(name = "createdon")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;
    @Size(max = 200)
    @Column(name = "agrodealer")
    private String agrodealer;
    @Size(max = 45)
    @Column(name = "voucherpercentage")
    private String voucherpercentage;
    @JoinColumn(name = "createdby", referencedColumnName = "idusers")
    @ManyToOne
    private User createdby;
    @JoinColumn(name = "statusID", referencedColumnName = "idstatus")
    @ManyToOne
    private Status statusID;

    public Govtpayments() {
    }

    public Govtpayments(Integer idgovtpayments) {
        this.idgovtpayments = idgovtpayments;
    }

    public Govtpayments(Integer idgovtpayments, String amount) {
        this.idgovtpayments = idgovtpayments;
        this.amount = amount;
    }

    public Integer getIdgovtpayments() {
        return idgovtpayments;
    }

    public void setIdgovtpayments(Integer idgovtpayments) {
        this.idgovtpayments = idgovtpayments;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public String getAgrodealer() {
        return agrodealer;
    }

    public void setAgrodealer(String agrodealer) {
        this.agrodealer = agrodealer;
    }

    public String getVoucherpercentage() {
        return voucherpercentage;
    }

    public void setVoucherpercentage(String voucherpercentage) {
        this.voucherpercentage = voucherpercentage;
    }

    public User getCreatedby() {
        return createdby;
    }

    public void setCreatedby(User createdby) {
        this.createdby = createdby;
    }

    public Status getStatusID() {
        return statusID;
    }

    public void setStatusID(Status statusID) {
        this.statusID = statusID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgovtpayments != null ? idgovtpayments.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Govtpayments)) {
            return false;
        }
        Govtpayments other = (Govtpayments) object;
        if ((this.idgovtpayments == null && other.idgovtpayments != null) || (this.idgovtpayments != null && !this.idgovtpayments.equals(other.idgovtpayments))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amon.db.Govtpayments[ idgovtpayments=" + idgovtpayments + " ]";
    }
    
}
