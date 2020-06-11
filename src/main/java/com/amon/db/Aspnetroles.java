/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amon.db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amon.Sabul
 */
@Entity
@Table(name = "aspnetroles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aspnetroles.findAll", query = "SELECT a FROM Aspnetroles a")
    , @NamedQuery(name = "Aspnetroles.findById", query = "SELECT a FROM Aspnetroles a WHERE a.id = :id")
    , @NamedQuery(name = "Aspnetroles.findByName", query = "SELECT a FROM Aspnetroles a WHERE a.name = :name")})
public class Aspnetroles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "Id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "Name")
    private String name;

    public Aspnetroles() {
    }

    public Aspnetroles(String id) {
        this.id = id;
    }

    public Aspnetroles(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aspnetroles)) {
            return false;
        }
        Aspnetroles other = (Aspnetroles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Aspnetroles[ id=" + id + " ]";
    }
    
}
