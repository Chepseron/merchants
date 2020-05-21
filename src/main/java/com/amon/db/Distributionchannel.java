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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FAZOUL
 */
@Entity
@Table(name = "distributionchannel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Distributionchannel.findAll", query = "SELECT d FROM Distributionchannel d"),
    @NamedQuery(name = "Distributionchannel.findByIddistributionChannel", query = "SELECT d FROM Distributionchannel d WHERE d.iddistributionChannel = :iddistributionChannel"),
    @NamedQuery(name = "Distributionchannel.findByName", query = "SELECT d FROM Distributionchannel d WHERE d.name = :name"),
    @NamedQuery(name = "Distributionchannel.findByContactphone", query = "SELECT d FROM Distributionchannel d WHERE d.contactphone = :contactphone"),
    @NamedQuery(name = "Distributionchannel.findByCreatedon", query = "SELECT d FROM Distributionchannel d WHERE d.createdon = :createdon")})
public class Distributionchannel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddistributionChannel")
    private Integer iddistributionChannel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "contactphone")
    private String contactphone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdon")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;
    @JoinColumn(name = "agrodealer", referencedColumnName = "idagrodealers")
    @ManyToOne(optional = false)
    private Agrodealers agrodealer;
    @JoinColumn(name = "region", referencedColumnName = "idregions")
    @ManyToOne(optional = false)
    private Regions region;
    @JoinColumn(name = "statusid", referencedColumnName = "idstatus")
    @ManyToOne(optional = false)
    private Status statusid;
    @JoinColumn(name = "createdby", referencedColumnName = "idusers")
    @ManyToOne(optional = false)
    private User createdby;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "channeledTo")
    private Collection<Orders> ordersCollection;

    public Distributionchannel() {
    }

    public Distributionchannel(Integer iddistributionChannel) {
        this.iddistributionChannel = iddistributionChannel;
    }

    public Distributionchannel(Integer iddistributionChannel, String name, String contactphone, Date createdon) {
        this.iddistributionChannel = iddistributionChannel;
        this.name = name;
        this.contactphone = contactphone;
        this.createdon = createdon;
    }

    public Integer getIddistributionChannel() {
        return iddistributionChannel;
    }

    public void setIddistributionChannel(Integer iddistributionChannel) {
        this.iddistributionChannel = iddistributionChannel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public Agrodealers getAgrodealer() {
        return agrodealer;
    }

    public void setAgrodealer(Agrodealers agrodealer) {
        this.agrodealer = agrodealer;
    }

    public Regions getRegion() {
        return region;
    }

    public void setRegion(Regions region) {
        this.region = region;
    }

    public Status getStatusid() {
        return statusid;
    }

    public void setStatusid(Status statusid) {
        this.statusid = statusid;
    }

    public User getCreatedby() {
        return createdby;
    }

    public void setCreatedby(User createdby) {
        this.createdby = createdby;
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
        hash += (iddistributionChannel != null ? iddistributionChannel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distributionchannel)) {
            return false;
        }
        Distributionchannel other = (Distributionchannel) object;
        if ((this.iddistributionChannel == null && other.iddistributionChannel != null) || (this.iddistributionChannel != null && !this.iddistributionChannel.equals(other.iddistributionChannel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amon.db.Distributionchannel[ iddistributionChannel=" + iddistributionChannel + " ]";
    }
    
}
