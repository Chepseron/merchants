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
@Table(name = "agrodealersperregion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agrodealersperregion.findAll", query = "SELECT a FROM Agrodealersperregion a"),
    @NamedQuery(name = "Agrodealersperregion.findByIdagrodealers", query = "SELECT a FROM Agrodealersperregion a WHERE a.idagrodealers = :idagrodealers"),
    @NamedQuery(name = "Agrodealersperregion.findByAgrodealers", query = "SELECT a FROM Agrodealersperregion a WHERE a.agrodealers = :agrodealers"),
    @NamedQuery(name = "Agrodealersperregion.findByRegionid", query = "SELECT a FROM Agrodealersperregion a WHERE a.regionid = :regionid")})
public class Agrodealersperregion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idagrodealers")
    @Id
    private int idagrodealers;
    @Basic(optional = false)
    @NotNull
    @Column(name = "agrodealers")
    private long agrodealers;
    @JoinColumn(name = "regionid")
    @OneToOne
    private Regions regionid;

    public Agrodealersperregion() {
    }

    public int getIdagrodealers() {
        return idagrodealers;
    }

    public void setIdagrodealers(int idagrodealers) {
        this.idagrodealers = idagrodealers;
    }

    public long getAgrodealers() {
        return agrodealers;
    }

    public void setAgrodealers(long agrodealers) {
        this.agrodealers = agrodealers;
    }

    /**
     * @return the regionid
     */
    public Regions getRegionid() {
        return regionid;
    }

    /**
     * @param regionid the regionid to set
     */
    public void setRegionid(Regions regionid) {
        this.regionid = regionid;
    }

}
