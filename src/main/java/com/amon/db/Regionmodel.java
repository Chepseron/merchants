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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author amon.sabul
 */
@Entity
@Table(name = "regionmodel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Regionmodel.findAll", query = "SELECT r FROM Regionmodel r")
    , @NamedQuery(name = "Regionmodel.findByIdusers", query = "SELECT r FROM Regionmodel r WHERE r.idusers = :idusers")
    , @NamedQuery(name = "Regionmodel.findByFarmers", query = "SELECT r FROM Regionmodel r WHERE r.farmers = :farmers")
    , @NamedQuery(name = "Regionmodel.findByRegion", query = "SELECT r FROM Regionmodel r WHERE r.region = :region")})
public class Regionmodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idusers")
    @Id
    private int idusers;
    @Basic(optional = false)
    @NotNull
    @Column(name = "farmers")
    private long farmers;
    @JoinColumn(name = "region")
    @OneToOne
    private Regions region;

    public Regionmodel() {
    }

    public int getIdusers() {
        return idusers;
    }

    public void setIdusers(int idusers) {
        this.idusers = idusers;
    }

    public long getFarmers() {
        return farmers;
    }

    public void setFarmers(long farmers) {
        this.farmers = farmers;
    }

    /**
     * @return the region
     */
    public Regions getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(Regions region) {
        this.region = region;
    }

}
