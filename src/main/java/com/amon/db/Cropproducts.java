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
@Table(name = "cropproducts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cropproducts.findAll", query = "SELECT c FROM Cropproducts c"),
    @NamedQuery(name = "Cropproducts.findByIdcropproducts", query = "SELECT c FROM Cropproducts c WHERE c.idcropproducts = :idcropproducts"),
    @NamedQuery(name = "Cropproducts.findByName", query = "SELECT c FROM Cropproducts c WHERE c.name = :name"),
    @NamedQuery(name = "Cropproducts.findByCreatedon", query = "SELECT c FROM Cropproducts c WHERE c.createdon = :createdon")})
public class Cropproducts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcropproducts")
    private Integer idcropproducts;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
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
    @JoinColumn(name = "statusid", referencedColumnName = "idstatus")
    @ManyToOne(optional = false)
    private Status statusid;

    public Cropproducts() {
    }

    public Cropproducts(Integer idcropproducts) {
        this.idcropproducts = idcropproducts;
    }

    public Cropproducts(Integer idcropproducts, String name, Date createdon) {
        this.idcropproducts = idcropproducts;
        this.name = name;
        this.createdon = createdon;
    }

    public Integer getIdcropproducts() {
        return idcropproducts;
    }

    public void setIdcropproducts(Integer idcropproducts) {
        this.idcropproducts = idcropproducts;
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

    public Status getStatusid() {
        return statusid;
    }

    public void setStatusid(Status statusid) {
        this.statusid = statusid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcropproducts != null ? idcropproducts.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cropproducts)) {
            return false;
        }
        Cropproducts other = (Cropproducts) object;
        if ((this.idcropproducts == null && other.idcropproducts != null) || (this.idcropproducts != null && !this.idcropproducts.equals(other.idcropproducts))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amon.db.Cropproducts[ idcropproducts=" + idcropproducts + " ]";
    }
    
}
