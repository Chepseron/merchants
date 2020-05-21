/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amon.db;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FAZOUL
 */
@Entity
@Table(name = "banks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Banks.findAll", query = "SELECT b FROM Banks b"),
    @NamedQuery(name = "Banks.findByIdbanks", query = "SELECT b FROM Banks b WHERE b.idbanks = :idbanks"),
    @NamedQuery(name = "Banks.findByCode", query = "SELECT b FROM Banks b WHERE b.code = :code"),
    @NamedQuery(name = "Banks.findByBankname", query = "SELECT b FROM Banks b WHERE b.bankname = :bankname"),
    @NamedQuery(name = "Banks.findByCreatedon", query = "SELECT b FROM Banks b WHERE b.createdon = :createdon"),
    @NamedQuery(name = "Banks.findByBank", query = "SELECT b FROM Banks b WHERE b.bank = :bank"),
    @NamedQuery(name = "Banks.findByMobile", query = "SELECT b FROM Banks b WHERE b.mobile = :mobile")})
public class Banks implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbanks")
    private Integer idbanks;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "bankname")
    private String bankname;
    @Column(name = "createdon")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bank")
    private int bank;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mobile")
    private int mobile;
    @OneToMany(mappedBy = "paymentmode")
    private Collection<Userpurchase> userpurchaseCollection;
    @JoinColumn(name = "createdby", referencedColumnName = "idusers")
    @ManyToOne
    private User createdby;
    @JoinColumn(name = "statusID", referencedColumnName = "idstatus")
    @ManyToOne
    private Status statusID;

    public Banks() {
    }

    public Banks(Integer idbanks) {
        this.idbanks = idbanks;
    }

    public Banks(Integer idbanks, String code, String bankname, int bank, int mobile) {
        this.idbanks = idbanks;
        this.code = code;
        this.bankname = bankname;
        this.bank = bank;
        this.mobile = mobile;
    }

    public Integer getIdbanks() {
        return idbanks;
    }

    public void setIdbanks(Integer idbanks) {
        this.idbanks = idbanks;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    @XmlTransient
    public Collection<Userpurchase> getUserpurchaseCollection() {
        return userpurchaseCollection;
    }

    public void setUserpurchaseCollection(Collection<Userpurchase> userpurchaseCollection) {
        this.userpurchaseCollection = userpurchaseCollection;
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
        hash += (idbanks != null ? idbanks.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banks)) {
            return false;
        }
        Banks other = (Banks) object;
        if ((this.idbanks == null && other.idbanks != null) || (this.idbanks != null && !this.idbanks.equals(other.idbanks))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amon.db.Banks[ idbanks=" + idbanks + " ]";
    }
    
}
