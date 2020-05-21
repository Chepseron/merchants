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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FAZOUL
 */
@Entity
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findByIdorders", query = "SELECT o FROM Orders o WHERE o.idorders = :idorders"),
    @NamedQuery(name = "Orders.findByCreatedon", query = "SELECT o FROM Orders o WHERE o.createdon = :createdon")})
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idorders")
    private Integer idorders;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdon")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;
    @JoinColumn(name = "purchaseID", referencedColumnName = "iduserpurchase")
    @ManyToOne(optional = false)
    private Userpurchase purchaseID;
    @JoinColumn(name = "channeledTo", referencedColumnName = "iddistributionChannel")
    @ManyToOne(optional = false)
    private Distributionchannel channeledTo;
    @JoinColumn(name = "createdby", referencedColumnName = "idusers")
    @ManyToOne(optional = false)
    private User createdby;
    @JoinColumn(name = "statusID", referencedColumnName = "idstatus")
    @ManyToOne(optional = false)
    private Status statusID;

    public Orders() {
    }

    public Orders(Integer idorders) {
        this.idorders = idorders;
    }

    public Orders(Integer idorders, Date createdon) {
        this.idorders = idorders;
        this.createdon = createdon;
    }

    public Integer getIdorders() {
        return idorders;
    }

    public void setIdorders(Integer idorders) {
        this.idorders = idorders;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public Userpurchase getPurchaseID() {
        return purchaseID;
    }

    public void setPurchaseID(Userpurchase purchaseID) {
        this.purchaseID = purchaseID;
    }

    public Distributionchannel getChanneledTo() {
        return channeledTo;
    }

    public void setChanneledTo(Distributionchannel channeledTo) {
        this.channeledTo = channeledTo;
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
        hash += (idorders != null ? idorders.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.idorders == null && other.idorders != null) || (this.idorders != null && !this.idorders.equals(other.idorders))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amon.db.Orders[ idorders=" + idorders + " ]";
    }
    
}
