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
@Table(name = "agrodealers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agrodealers.findAll", query = "SELECT a FROM Agrodealers a"),
    @NamedQuery(name = "Agrodealers.findByIdagrodealers", query = "SELECT a FROM Agrodealers a WHERE a.idagrodealers = :idagrodealers"),
    @NamedQuery(name = "Agrodealers.findByCompanyname", query = "SELECT a FROM Agrodealers a WHERE a.companyname = :companyname"),
    @NamedQuery(name = "Agrodealers.findByContactaddress", query = "SELECT a FROM Agrodealers a WHERE a.contactaddress = :contactaddress"),
    @NamedQuery(name = "Agrodealers.findByPhone", query = "SELECT a FROM Agrodealers a WHERE a.phone = :phone"),
    @NamedQuery(name = "Agrodealers.findByCreatedon", query = "SELECT a FROM Agrodealers a WHERE a.createdon = :createdon"),
    @NamedQuery(name = "Agrodealers.findByRegistrationcertid", query = "SELECT a FROM Agrodealers a WHERE a.registrationcertid = :registrationcertid"),
    @NamedQuery(name = "Agrodealers.findByDateregistered", query = "SELECT a FROM Agrodealers a WHERE a.dateregistered = :dateregistered")})
public class Agrodealers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idagrodealers")
    private Integer idagrodealers;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "companyname")
    private String companyname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "contactaddress")
    private String contactaddress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "phone")
    private int phone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdon")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "registrationcertid")
    private String registrationcertid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateregistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateregistered;
    @OneToMany(mappedBy = "dealerid")
    private Collection<Agrodealerproducts> agrodealerproductsCollection;
    @JoinColumn(name = "statusID", referencedColumnName = "idstatus")
    @ManyToOne(optional = false)
    private Status statusID;
    @JoinColumn(name = "createdby", referencedColumnName = "idusers")
    @ManyToOne(optional = false)
    private User createdby;
    @JoinColumn(name = "ownerid", referencedColumnName = "idusers")
    @ManyToOne
    private User ownerid;
    @JoinColumn(name = "regionid", referencedColumnName = "idregions")
    @ManyToOne
    private Regions regionid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agrodealer")
    private Collection<Distributionchannel> distributionchannelCollection;

    public Agrodealers() {
    }

    public Agrodealers(Integer idagrodealers) {
        this.idagrodealers = idagrodealers;
    }

    public Agrodealers(Integer idagrodealers, String companyname, String contactaddress, int phone, Date createdon, String registrationcertid, Date dateregistered) {
        this.idagrodealers = idagrodealers;
        this.companyname = companyname;
        this.contactaddress = contactaddress;
        this.phone = phone;
        this.createdon = createdon;
        this.registrationcertid = registrationcertid;
        this.dateregistered = dateregistered;
    }

    public Integer getIdagrodealers() {
        return idagrodealers;
    }

    public void setIdagrodealers(Integer idagrodealers) {
        this.idagrodealers = idagrodealers;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getContactaddress() {
        return contactaddress;
    }

    public void setContactaddress(String contactaddress) {
        this.contactaddress = contactaddress;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public String getRegistrationcertid() {
        return registrationcertid;
    }

    public void setRegistrationcertid(String registrationcertid) {
        this.registrationcertid = registrationcertid;
    }

    public Date getDateregistered() {
        return dateregistered;
    }

    public void setDateregistered(Date dateregistered) {
        this.dateregistered = dateregistered;
    }

    @XmlTransient
    public Collection<Agrodealerproducts> getAgrodealerproductsCollection() {
        return agrodealerproductsCollection;
    }

    public void setAgrodealerproductsCollection(Collection<Agrodealerproducts> agrodealerproductsCollection) {
        this.agrodealerproductsCollection = agrodealerproductsCollection;
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

    public User getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(User ownerid) {
        this.ownerid = ownerid;
    }

    public Regions getRegionid() {
        return regionid;
    }

    public void setRegionid(Regions regionid) {
        this.regionid = regionid;
    }

    @XmlTransient
    public Collection<Distributionchannel> getDistributionchannelCollection() {
        return distributionchannelCollection;
    }

    public void setDistributionchannelCollection(Collection<Distributionchannel> distributionchannelCollection) {
        this.distributionchannelCollection = distributionchannelCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idagrodealers != null ? idagrodealers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agrodealers)) {
            return false;
        }
        Agrodealers other = (Agrodealers) object;
        if ((this.idagrodealers == null && other.idagrodealers != null) || (this.idagrodealers != null && !this.idagrodealers.equals(other.idagrodealers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amon.db.Agrodealers[ idagrodealers=" + idagrodealers + " ]";
    }
    
}
