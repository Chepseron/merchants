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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FAZOUL
 */
@Entity
@Table(name = "nira")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nira.findAll", query = "SELECT n FROM Nira n"),
    @NamedQuery(name = "Nira.findByIdNIRA", query = "SELECT n FROM Nira n WHERE n.idNIRA = :idNIRA"),
    @NamedQuery(name = "Nira.findByIdNumber", query = "SELECT n FROM Nira n WHERE n.idNumber = :idNumber")})
public class Nira implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNIRA")
    private Integer idNIRA;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "idNumber")
    private String idNumber;

    public Nira() {
    }

    public Nira(Integer idNIRA) {
        this.idNIRA = idNIRA;
    }

    public Nira(Integer idNIRA, String idNumber) {
        this.idNIRA = idNIRA;
        this.idNumber = idNumber;
    }

    public Integer getIdNIRA() {
        return idNIRA;
    }

    public void setIdNIRA(Integer idNIRA) {
        this.idNIRA = idNIRA;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNIRA != null ? idNIRA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nira)) {
            return false;
        }
        Nira other = (Nira) object;
        if ((this.idNIRA == null && other.idNIRA != null) || (this.idNIRA != null && !this.idNIRA.equals(other.idNIRA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amon.db.Nira[ idNIRA=" + idNIRA + " ]";
    }
    
}
