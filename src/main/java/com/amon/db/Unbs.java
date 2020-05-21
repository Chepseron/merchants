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
@Table(name = "unbs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unbs.findAll", query = "SELECT u FROM Unbs u"),
    @NamedQuery(name = "Unbs.findByIdUNBS", query = "SELECT u FROM Unbs u WHERE u.idUNBS = :idUNBS"),
    @NamedQuery(name = "Unbs.findByProductID", query = "SELECT u FROM Unbs u WHERE u.productID = :productID"),
    @NamedQuery(name = "Unbs.findByProductSerial", query = "SELECT u FROM Unbs u WHERE u.productSerial = :productSerial")})
public class Unbs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUNBS")
    private Integer idUNBS;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "productID")
    private String productID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "productSerial")
    private String productSerial;

    public Unbs() {
    }

    public Unbs(Integer idUNBS) {
        this.idUNBS = idUNBS;
    }

    public Unbs(Integer idUNBS, String productID, String productSerial) {
        this.idUNBS = idUNBS;
        this.productID = productID;
        this.productSerial = productSerial;
    }

    public Integer getIdUNBS() {
        return idUNBS;
    }

    public void setIdUNBS(Integer idUNBS) {
        this.idUNBS = idUNBS;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductSerial() {
        return productSerial;
    }

    public void setProductSerial(String productSerial) {
        this.productSerial = productSerial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUNBS != null ? idUNBS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unbs)) {
            return false;
        }
        Unbs other = (Unbs) object;
        if ((this.idUNBS == null && other.idUNBS != null) || (this.idUNBS != null && !this.idUNBS.equals(other.idUNBS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amon.db.Unbs[ idUNBS=" + idUNBS + " ]";
    }
    
}
