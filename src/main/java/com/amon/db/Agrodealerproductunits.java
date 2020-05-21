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
@Table(name = "agrodealerproductunits")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agrodealerproductunits.findAll", query = "SELECT a FROM Agrodealerproductunits a"),
    @NamedQuery(name = "Agrodealerproductunits.findByIdagrodealerproductunits", query = "SELECT a FROM Agrodealerproductunits a WHERE a.idagrodealerproductunits = :idagrodealerproductunits"),
    @NamedQuery(name = "Agrodealerproductunits.findByUnitname", query = "SELECT a FROM Agrodealerproductunits a WHERE a.unitname = :unitname"),
    @NamedQuery(name = "Agrodealerproductunits.findByDescription", query = "SELECT a FROM Agrodealerproductunits a WHERE a.description = :description"),
    @NamedQuery(name = "Agrodealerproductunits.findByCreatedon", query = "SELECT a FROM Agrodealerproductunits a WHERE a.createdon = :createdon")})
public class Agrodealerproductunits implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idagrodealerproductunits")
    private Integer idagrodealerproductunits;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "unitname")
    private String unitname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdon")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;
    @OneToMany(mappedBy = "agrodealerproductunit")
    private Collection<Agrodealerproducts> agrodealerproductsCollection;
    @JoinColumn(name = "createdby", referencedColumnName = "idusers")
    @ManyToOne(optional = false)
    private User createdby;
    @JoinColumn(name = "statusID", referencedColumnName = "idstatus")
    @ManyToOne
    private Status statusID;

    public Agrodealerproductunits() {
    }

    public Agrodealerproductunits(Integer idagrodealerproductunits) {
        this.idagrodealerproductunits = idagrodealerproductunits;
    }

    public Agrodealerproductunits(Integer idagrodealerproductunits, String unitname, String description, Date createdon) {
        this.idagrodealerproductunits = idagrodealerproductunits;
        this.unitname = unitname;
        this.description = description;
        this.createdon = createdon;
    }

    public Integer getIdagrodealerproductunits() {
        return idagrodealerproductunits;
    }

    public void setIdagrodealerproductunits(Integer idagrodealerproductunits) {
        this.idagrodealerproductunits = idagrodealerproductunits;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    @XmlTransient
    public Collection<Agrodealerproducts> getAgrodealerproductsCollection() {
        return agrodealerproductsCollection;
    }

    public void setAgrodealerproductsCollection(Collection<Agrodealerproducts> agrodealerproductsCollection) {
        this.agrodealerproductsCollection = agrodealerproductsCollection;
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
        hash += (idagrodealerproductunits != null ? idagrodealerproductunits.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agrodealerproductunits)) {
            return false;
        }
        Agrodealerproductunits other = (Agrodealerproductunits) object;
        if ((this.idagrodealerproductunits == null && other.idagrodealerproductunits != null) || (this.idagrodealerproductunits != null && !this.idagrodealerproductunits.equals(other.idagrodealerproductunits))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amon.db.Agrodealerproductunits[ idagrodealerproductunits=" + idagrodealerproductunits + " ]";
    }
    
}
