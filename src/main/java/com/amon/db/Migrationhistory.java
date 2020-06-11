/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amon.db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
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
@Table(name = "__migrationhistory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Migrationhistory.findAll", query = "SELECT m FROM Migrationhistory m")
    , @NamedQuery(name = "Migrationhistory.findByMigrationId", query = "SELECT m FROM Migrationhistory m WHERE m.migrationhistoryPK.migrationId = :migrationId")
    , @NamedQuery(name = "Migrationhistory.findByContextKey", query = "SELECT m FROM Migrationhistory m WHERE m.migrationhistoryPK.contextKey = :contextKey")
    , @NamedQuery(name = "Migrationhistory.findByProductVersion", query = "SELECT m FROM Migrationhistory m WHERE m.productVersion = :productVersion")})
public class Migrationhistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MigrationhistoryPK migrationhistoryPK;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "Model")
    private byte[] model;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "ProductVersion")
    private String productVersion;

    public Migrationhistory() {
    }

    public Migrationhistory(MigrationhistoryPK migrationhistoryPK) {
        this.migrationhistoryPK = migrationhistoryPK;
    }

    public Migrationhistory(MigrationhistoryPK migrationhistoryPK, byte[] model, String productVersion) {
        this.migrationhistoryPK = migrationhistoryPK;
        this.model = model;
        this.productVersion = productVersion;
    }

    public Migrationhistory(String migrationId, String contextKey) {
        this.migrationhistoryPK = new MigrationhistoryPK(migrationId, contextKey);
    }

    public MigrationhistoryPK getMigrationhistoryPK() {
        return migrationhistoryPK;
    }

    public void setMigrationhistoryPK(MigrationhistoryPK migrationhistoryPK) {
        this.migrationhistoryPK = migrationhistoryPK;
    }

    public byte[] getModel() {
        return model;
    }

    public void setModel(byte[] model) {
        this.model = model;
    }

    public String getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (migrationhistoryPK != null ? migrationhistoryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Migrationhistory)) {
            return false;
        }
        Migrationhistory other = (Migrationhistory) object;
        if ((this.migrationhistoryPK == null && other.migrationhistoryPK != null) || (this.migrationhistoryPK != null && !this.migrationhistoryPK.equals(other.migrationhistoryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Migrationhistory[ migrationhistoryPK=" + migrationhistoryPK + " ]";
    }
    
}
