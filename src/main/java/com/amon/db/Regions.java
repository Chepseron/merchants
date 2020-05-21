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
@Table(name = "regions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Regions.findAll", query = "SELECT r FROM Regions r"),
    @NamedQuery(name = "Regions.findByIdregions", query = "SELECT r FROM Regions r WHERE r.idregions = :idregions"),
    @NamedQuery(name = "Regions.findByProvince", query = "SELECT r FROM Regions r WHERE r.province = :province"),
    @NamedQuery(name = "Regions.findByConstituency", query = "SELECT r FROM Regions r WHERE r.constituency = :constituency"),
    @NamedQuery(name = "Regions.findByMunicipality", query = "SELECT r FROM Regions r WHERE r.municipality = :municipality"),
    @NamedQuery(name = "Regions.findByCounty", query = "SELECT r FROM Regions r WHERE r.county = :county"),
    @NamedQuery(name = "Regions.findBySubcounty", query = "SELECT r FROM Regions r WHERE r.subcounty = :subcounty"),
    @NamedQuery(name = "Regions.findByWard", query = "SELECT r FROM Regions r WHERE r.ward = :ward"),
    @NamedQuery(name = "Regions.findByCreatedon", query = "SELECT r FROM Regions r WHERE r.createdon = :createdon"),
    @NamedQuery(name = "Regions.findByVillage", query = "SELECT r FROM Regions r WHERE r.village = :village")})
public class Regions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idregions")
    private Integer idregions;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "province")
    private String province;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "constituency")
    private String constituency;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "municipality")
    private String municipality;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "county")
    private String county;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "subcounty")
    private String subcounty;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ward")
    private String ward;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdon")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "village")
    private String village;
    @JoinColumn(name = "createdby", referencedColumnName = "idusers")
    @ManyToOne(optional = false)
    private User createdby;
    @JoinColumn(name = "statusid", referencedColumnName = "idstatus")
    @ManyToOne(optional = false)
    private Status statusid;
    @OneToMany(mappedBy = "regionid")
    private Collection<Agrodealers> agrodealersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
    private Collection<Distributionchannel> distributionchannelCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
    private Collection<Farmergroups> farmergroupsCollection;

    public Regions() {
    }

    public Regions(Integer idregions) {
        this.idregions = idregions;
    }

    public Regions(Integer idregions, String province, String constituency, String municipality, String county, String subcounty, String ward, Date createdon, String village) {
        this.idregions = idregions;
        this.province = province;
        this.constituency = constituency;
        this.municipality = municipality;
        this.county = county;
        this.subcounty = subcounty;
        this.ward = ward;
        this.createdon = createdon;
        this.village = village;
    }

    public Integer getIdregions() {
        return idregions;
    }

    public void setIdregions(Integer idregions) {
        this.idregions = idregions;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getSubcounty() {
        return subcounty;
    }

    public void setSubcounty(String subcounty) {
        this.subcounty = subcounty;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public User getCreatedby() {
        return createdby;
    }

    public void setCreatedby(User createdby) {
        this.createdby = createdby;
    }

    public Status getStatusid() {
        return statusid;
    }

    public void setStatusid(Status statusid) {
        this.statusid = statusid;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idregions != null ? idregions.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Regions)) {
            return false;
        }
        Regions other = (Regions) object;
        if ((this.idregions == null && other.idregions != null) || (this.idregions != null && !this.idregions.equals(other.idregions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amon.db.Regions[ idregions=" + idregions + " ]";
    }
    
}
