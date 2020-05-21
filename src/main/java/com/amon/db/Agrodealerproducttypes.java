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
@Table(name = "agrodealerproducttypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agrodealerproducttypes.findAll", query = "SELECT a FROM Agrodealerproducttypes a"),
    @NamedQuery(name = "Agrodealerproducttypes.findByIdagrodealerproducttypes", query = "SELECT a FROM Agrodealerproducttypes a WHERE a.idagrodealerproducttypes = :idagrodealerproducttypes"),
    @NamedQuery(name = "Agrodealerproducttypes.findByProductname", query = "SELECT a FROM Agrodealerproducttypes a WHERE a.productname = :productname"),
    @NamedQuery(name = "Agrodealerproducttypes.findByCreatedon", query = "SELECT a FROM Agrodealerproducttypes a WHERE a.createdon = :createdon")})
public class Agrodealerproducttypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idagrodealerproducttypes")
    private Integer idagrodealerproducttypes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "productname")
    private String productname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdon")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;
    @OneToMany(mappedBy = "producttype")
    private Collection<Agrodealerproducts> agrodealerproductsCollection;
    @JoinColumn(name = "createdby", referencedColumnName = "idusers")
    @ManyToOne(optional = false)
    private User createdby;
    @JoinColumn(name = "status", referencedColumnName = "idstatus")
    @ManyToOne(optional = false)
    private Status status;

    public Agrodealerproducttypes() {
    }

    public Agrodealerproducttypes(Integer idagrodealerproducttypes) {
        this.idagrodealerproducttypes = idagrodealerproducttypes;
    }

    public Agrodealerproducttypes(Integer idagrodealerproducttypes, String productname, Date createdon) {
        this.idagrodealerproducttypes = idagrodealerproducttypes;
        this.productname = productname;
        this.createdon = createdon;
    }

    public Integer getIdagrodealerproducttypes() {
        return idagrodealerproducttypes;
    }

    public void setIdagrodealerproducttypes(Integer idagrodealerproducttypes) {
        this.idagrodealerproducttypes = idagrodealerproducttypes;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idagrodealerproducttypes != null ? idagrodealerproducttypes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agrodealerproducttypes)) {
            return false;
        }
        Agrodealerproducttypes other = (Agrodealerproducttypes) object;
        if ((this.idagrodealerproducttypes == null && other.idagrodealerproducttypes != null) || (this.idagrodealerproducttypes != null && !this.idagrodealerproducttypes.equals(other.idagrodealerproducttypes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amon.db.Agrodealerproducttypes[ idagrodealerproducttypes=" + idagrodealerproducttypes + " ]";
    }
    
}
