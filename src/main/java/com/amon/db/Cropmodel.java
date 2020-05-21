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
 * @author amon.sabul
 */
@Entity
@Table(name = "cropmodel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cropmodel.findAll", query = "SELECT c FROM Cropmodel c")
    , @NamedQuery(name = "Cropmodel.findByFarmers", query = "SELECT c FROM Cropmodel c WHERE c.farmers = :farmers")
    , @NamedQuery(name = "Cropmodel.findByCropgrown", query = "SELECT c FROM Cropmodel c WHERE c.cropgrown = :cropgrown")})
public class Cropmodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "farmers")
    private long farmers;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cropgrown")
    @Id
    private String cropgrown;

    public Cropmodel() {
    }

    public long getFarmers() {
        return farmers;
    }

    public void setFarmers(long farmers) {
        this.farmers = farmers;
    }

    public String getCropgrown() {
        return cropgrown;
    }

    public void setCropgrown(String cropgrown) {
        this.cropgrown = cropgrown;
    }
    
}
