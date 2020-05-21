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
@Table(name = "farmergroups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Farmergroups.findAll", query = "SELECT f FROM Farmergroups f"),
    @NamedQuery(name = "Farmergroups.findByIdfarmergroups", query = "SELECT f FROM Farmergroups f WHERE f.idfarmergroups = :idfarmergroups"),
    @NamedQuery(name = "Farmergroups.findByName", query = "SELECT f FROM Farmergroups f WHERE f.name = :name"),
    @NamedQuery(name = "Farmergroups.findByCreatedon", query = "SELECT f FROM Farmergroups f WHERE f.createdon = :createdon")})
public class Farmergroups implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfarmergroups")
    private Integer idfarmergroups;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "createdon")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;
    @JoinColumn(name = "createdby", referencedColumnName = "idusers")
    @ManyToOne(optional = false)
    private User createdby;
    @JoinColumn(name = "statusID", referencedColumnName = "idstatus")
    @ManyToOne(optional = false)
    private Status statusID;
    @JoinColumn(name = "region", referencedColumnName = "idregions")
    @ManyToOne(optional = false)
    private Regions region;

    public Farmergroups() {
    }

    public Farmergroups(Integer idfarmergroups) {
        this.idfarmergroups = idfarmergroups;
    }

    public Farmergroups(Integer idfarmergroups, String name, Date createdon) {
        this.idfarmergroups = idfarmergroups;
        this.name = name;
        this.createdon = createdon;
    }

    public Integer getIdfarmergroups() {
        return idfarmergroups;
    }

    public void setIdfarmergroups(Integer idfarmergroups) {
        this.idfarmergroups = idfarmergroups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
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

    public Regions getRegion() {
        return region;
    }

    public void setRegion(Regions region) {
        this.region = region;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfarmergroups != null ? idfarmergroups.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Farmergroups)) {
            return false;
        }
        Farmergroups other = (Farmergroups) object;
        if ((this.idfarmergroups == null && other.idfarmergroups != null) || (this.idfarmergroups != null && !this.idfarmergroups.equals(other.idfarmergroups))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amon.db.Farmergroups[ idfarmergroups=" + idfarmergroups + " ]";
    }
    
}
