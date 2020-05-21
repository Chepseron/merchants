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
import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FAZOUL
 */
@Entity
@Table(name = "userpurchase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userpurchase.findAll", query = "SELECT u FROM Userpurchase u"),
    @NamedQuery(name = "Userpurchase.findByIduserpurchase", query = "SELECT u FROM Userpurchase u WHERE u.iduserpurchase = :iduserpurchase"),
    @NamedQuery(name = "Userpurchase.findByPrice", query = "SELECT u FROM Userpurchase u WHERE u.price = :price"),
    @NamedQuery(name = "Userpurchase.findByCreatedon", query = "SELECT u FROM Userpurchase u WHERE u.createdon = :createdon"),
    @NamedQuery(name = "Userpurchase.findByQuantity", query = "SELECT u FROM Userpurchase u WHERE u.quantity = :quantity")})
public class Userpurchase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iduserpurchase")
    private Integer iduserpurchase;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdon")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private double quantity;
    @JoinColumn(name = "paymentmode", referencedColumnName = "idbanks")
    @ManyToOne
    private Banks paymentmode;
    @JoinColumn(name = "statusID", referencedColumnName = "idstatus")
    @ManyToOne(optional = false)
    private Status statusID;
    @JoinColumn(name = "createdby", referencedColumnName = "idusers")
    @ManyToOne(optional = false)
    private User createdby;
    @JoinColumn(name = "userid", referencedColumnName = "idusers")
    @ManyToOne(optional = false)
    private User userid;
    @JoinColumn(name = "agrodealerproductid", referencedColumnName = "idagrodealerproducts")
    @ManyToOne(optional = false)
    private Agrodealerproducts agrodealerproductid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchaseID")
    private Collection<Orders> ordersCollection;

    public Userpurchase() {
    }

    public Userpurchase(Integer iduserpurchase) {
        this.iduserpurchase = iduserpurchase;
    }

    public Userpurchase(Integer iduserpurchase, double price, Date createdon, double quantity) {
        this.iduserpurchase = iduserpurchase;
        this.price = price;
        this.createdon = createdon;
        this.quantity = quantity;
    }

    public Integer getIduserpurchase() {
        return iduserpurchase;
    }

    public void setIduserpurchase(Integer iduserpurchase) {
        this.iduserpurchase = iduserpurchase;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Banks getPaymentmode() {
        return paymentmode;
    }

    public void setPaymentmode(Banks paymentmode) {
        this.paymentmode = paymentmode;
    }

    public Status getStatusID() {
        return statusID;
    }

    public void setStatusID(Status statusID) {
        this.statusID = statusID;
    }

    public User getCreatedby() {
        return createdby;
    }

    public void setCreatedby(User createdby) {
        this.createdby = createdby;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    public Agrodealerproducts getAgrodealerproductid() {
        return agrodealerproductid;
    }

    public void setAgrodealerproductid(Agrodealerproducts agrodealerproductid) {
        this.agrodealerproductid = agrodealerproductid;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iduserpurchase != null ? iduserpurchase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userpurchase)) {
            return false;
        }
        Userpurchase other = (Userpurchase) object;
        if ((this.iduserpurchase == null && other.iduserpurchase != null) || (this.iduserpurchase != null && !this.iduserpurchase.equals(other.iduserpurchase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amon.db.Userpurchase[ iduserpurchase=" + iduserpurchase + " ]";
    }
    
}
