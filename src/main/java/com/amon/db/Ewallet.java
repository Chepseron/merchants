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
@Table(name = "ewallet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ewallet.findAll", query = "SELECT e FROM Ewallet e"),
    @NamedQuery(name = "Ewallet.findByBalance", query = "SELECT e FROM Ewallet e WHERE e.balance = :balance"),
    @NamedQuery(name = "Ewallet.findByCreatedon", query = "SELECT e FROM Ewallet e WHERE e.createdon = :createdon"),
    @NamedQuery(name = "Ewallet.findByIdewallet", query = "SELECT e FROM Ewallet e WHERE e.idewallet = :idewallet"),
    @NamedQuery(name = "Ewallet.findByActionlastperformed", query = "SELECT e FROM Ewallet e WHERE e.actionlastperformed = :actionlastperformed")})
public class Ewallet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "balance")
    private double balance;
    @Column(name = "createdon")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idewallet")
    private Integer idewallet;
    @Size(max = 200)
    @Column(name = "actionlastperformed")
    private String actionlastperformed;
    @JoinColumn(name = "statusID", referencedColumnName = "idstatus")
    @ManyToOne
    private Status statusID;
    @JoinColumn(name = "userid", referencedColumnName = "idusers")
    @ManyToOne(optional = false)
    private User userid;
    @JoinColumn(name = "createdby", referencedColumnName = "idusers")
    @ManyToOne
    private User createdby;

    public Ewallet() {
    }

    public Ewallet(Integer idewallet) {
        this.idewallet = idewallet;
    }

    public Ewallet(Integer idewallet, double balance) {
        this.idewallet = idewallet;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public Integer getIdewallet() {
        return idewallet;
    }

    public void setIdewallet(Integer idewallet) {
        this.idewallet = idewallet;
    }

    public String getActionlastperformed() {
        return actionlastperformed;
    }

    public void setActionlastperformed(String actionlastperformed) {
        this.actionlastperformed = actionlastperformed;
    }

    public Status getStatusID() {
        return statusID;
    }

    public void setStatusID(Status statusID) {
        this.statusID = statusID;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    public User getCreatedby() {
        return createdby;
    }

    public void setCreatedby(User createdby) {
        this.createdby = createdby;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idewallet != null ? idewallet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ewallet)) {
            return false;
        }
        Ewallet other = (Ewallet) object;
        if ((this.idewallet == null && other.idewallet != null) || (this.idewallet != null && !this.idewallet.equals(other.idewallet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amon.db.Ewallet[ idewallet=" + idewallet + " ]";
    }
    
}
