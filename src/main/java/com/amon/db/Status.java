/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amon.db;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FAZOUL
 */
@Entity
@Table(name = "status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Status.findAll", query = "SELECT s FROM Status s"),
    @NamedQuery(name = "Status.findByIdstatus", query = "SELECT s FROM Status s WHERE s.idstatus = :idstatus"),
    @NamedQuery(name = "Status.findByName", query = "SELECT s FROM Status s WHERE s.name = :name"),
    @NamedQuery(name = "Status.findByDescription", query = "SELECT s FROM Status s WHERE s.description = :description"),
    @NamedQuery(name = "Status.findByCreatedBy", query = "SELECT s FROM Status s WHERE s.createdBy = :createdBy")})
public class Status implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idstatus")
    private Integer idstatus;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Column(name = "createdBy")
    private Integer createdBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusid")
    private Collection<Regions> regionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusID")
    private Collection<Userpurchase> userpurchaseCollection;
    @OneToMany(mappedBy = "statusID")
    private Collection<Banks> banksCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusid")
    private Collection<Cropproducts> cropproductsCollection;
    @OneToMany(mappedBy = "statusID")
    private Collection<Ewallet> ewalletCollection;
    @OneToMany(mappedBy = "status")
    private Collection<Agrodealerproducts> agrodealerproductsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusID")
    private Collection<Agrodealers> agrodealersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusid")
    private Collection<Distributionchannel> distributionchannelCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusID")
    private Collection<Farmergroups> farmergroupsCollection;
    @OneToMany(mappedBy = "statusID")
    private Collection<Agrodealerproductunits> agrodealerproductunitsCollection;
    @OneToMany(mappedBy = "statusID")
    private Collection<Usergroup> usergroupCollection;
    @OneToMany(mappedBy = "statusID")
    private Collection<Govtpayments> govtpaymentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusID")
    private Collection<Orders> ordersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private Collection<Agrodealerproducttypes> agrodealerproducttypesCollection;
    @OneToMany(mappedBy = "statusID")
    private Collection<User> userCollection;

    public Status() {
    }

    public Status(Integer idstatus) {
        this.idstatus = idstatus;
    }

    public Integer getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(Integer idstatus) {
        this.idstatus = idstatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @XmlTransient
    public Collection<Regions> getRegionsCollection() {
        return regionsCollection;
    }

    public void setRegionsCollection(Collection<Regions> regionsCollection) {
        this.regionsCollection = regionsCollection;
    }

    @XmlTransient
    public Collection<Userpurchase> getUserpurchaseCollection() {
        return userpurchaseCollection;
    }

    public void setUserpurchaseCollection(Collection<Userpurchase> userpurchaseCollection) {
        this.userpurchaseCollection = userpurchaseCollection;
    }

    @XmlTransient
    public Collection<Banks> getBanksCollection() {
        return banksCollection;
    }

    public void setBanksCollection(Collection<Banks> banksCollection) {
        this.banksCollection = banksCollection;
    }

    @XmlTransient
    public Collection<Cropproducts> getCropproductsCollection() {
        return cropproductsCollection;
    }

    public void setCropproductsCollection(Collection<Cropproducts> cropproductsCollection) {
        this.cropproductsCollection = cropproductsCollection;
    }

    @XmlTransient
    public Collection<Ewallet> getEwalletCollection() {
        return ewalletCollection;
    }

    public void setEwalletCollection(Collection<Ewallet> ewalletCollection) {
        this.ewalletCollection = ewalletCollection;
    }

    @XmlTransient
    public Collection<Agrodealerproducts> getAgrodealerproductsCollection() {
        return agrodealerproductsCollection;
    }

    public void setAgrodealerproductsCollection(Collection<Agrodealerproducts> agrodealerproductsCollection) {
        this.agrodealerproductsCollection = agrodealerproductsCollection;
    }

    @XmlTransient
    public Collection<Agrodealers> getAgrodealersCollection() {
        return agrodealersCollection;
    }

    public void setAgrodealersCollection(Collection<Agrodealers> agrodealersCollection) {
        this.agrodealersCollection = agrodealersCollection;
    }

    @XmlTransient
    public Collection<Distributionchannel> getDistributionchannelCollection() {
        return distributionchannelCollection;
    }

    public void setDistributionchannelCollection(Collection<Distributionchannel> distributionchannelCollection) {
        this.distributionchannelCollection = distributionchannelCollection;
    }

    @XmlTransient
    public Collection<Farmergroups> getFarmergroupsCollection() {
        return farmergroupsCollection;
    }

    public void setFarmergroupsCollection(Collection<Farmergroups> farmergroupsCollection) {
        this.farmergroupsCollection = farmergroupsCollection;
    }

    @XmlTransient
    public Collection<Agrodealerproductunits> getAgrodealerproductunitsCollection() {
        return agrodealerproductunitsCollection;
    }

    public void setAgrodealerproductunitsCollection(Collection<Agrodealerproductunits> agrodealerproductunitsCollection) {
        this.agrodealerproductunitsCollection = agrodealerproductunitsCollection;
    }

    @XmlTransient
    public Collection<Usergroup> getUsergroupCollection() {
        return usergroupCollection;
    }

    public void setUsergroupCollection(Collection<Usergroup> usergroupCollection) {
        this.usergroupCollection = usergroupCollection;
    }

    @XmlTransient
    public Collection<Govtpayments> getGovtpaymentsCollection() {
        return govtpaymentsCollection;
    }

    public void setGovtpaymentsCollection(Collection<Govtpayments> govtpaymentsCollection) {
        this.govtpaymentsCollection = govtpaymentsCollection;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @XmlTransient
    public Collection<Agrodealerproducttypes> getAgrodealerproducttypesCollection() {
        return agrodealerproducttypesCollection;
    }

    public void setAgrodealerproducttypesCollection(Collection<Agrodealerproducttypes> agrodealerproducttypesCollection) {
        this.agrodealerproducttypesCollection = agrodealerproducttypesCollection;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstatus != null ? idstatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Status)) {
            return false;
        }
        Status other = (Status) object;
        if ((this.idstatus == null && other.idstatus != null) || (this.idstatus != null && !this.idstatus.equals(other.idstatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amon.db.Status[ idstatus=" + idstatus + " ]";
    }
    
}
