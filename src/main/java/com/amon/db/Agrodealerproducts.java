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
import javax.persistence.CascadeType;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FAZOUL
 */
@Entity
@Table(name = "agrodealerproducts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agrodealerproducts.findAll", query = "SELECT a FROM Agrodealerproducts a"),
    @NamedQuery(name = "Agrodealerproducts.findByIdagrodealerproducts", query = "SELECT a FROM Agrodealerproducts a WHERE a.idagrodealerproducts = :idagrodealerproducts"),
    @NamedQuery(name = "Agrodealerproducts.findByPhotourl", query = "SELECT a FROM Agrodealerproducts a WHERE a.photourl = :photourl"),
    @NamedQuery(name = "Agrodealerproducts.findByProductname", query = "SELECT a FROM Agrodealerproducts a WHERE a.productname = :productname"),
    @NamedQuery(name = "Agrodealerproducts.findByPrice", query = "SELECT a FROM Agrodealerproducts a WHERE a.price = :price"),
    @NamedQuery(name = "Agrodealerproducts.findByCreatedon", query = "SELECT a FROM Agrodealerproducts a WHERE a.createdon = :createdon"),
    @NamedQuery(name = "Agrodealerproducts.findByOrderid", query = "SELECT a FROM Agrodealerproducts a WHERE a.orderid = :orderid"),
    @NamedQuery(name = "Agrodealerproducts.findByUbsid", query = "SELECT a FROM Agrodealerproducts a WHERE a.ubsid = :ubsid"),
    @NamedQuery(name = "Agrodealerproducts.findByQuantitypresent", query = "SELECT a FROM Agrodealerproducts a WHERE a.quantitypresent = :quantitypresent"),
    @NamedQuery(name = "Agrodealerproducts.findByDescription", query = "SELECT a FROM Agrodealerproducts a WHERE a.description = :description"),
    @NamedQuery(name = "Agrodealerproducts.findByProductSerial", query = "SELECT a FROM Agrodealerproducts a WHERE a.productSerial = :productSerial"),
    @NamedQuery(name = "Agrodealerproducts.findByQtypurchased", query = "SELECT a FROM Agrodealerproducts a WHERE a.qtypurchased = :qtypurchased")})
public class Agrodealerproducts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idagrodealerproducts")
    private Integer idagrodealerproducts;
    @Size(max = 200)
    @Column(name = "photourl")
    private String photourl;
    @Size(max = 100)
    @Column(name = "productname")
    private String productname;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Column(name = "createdon")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;
    @Column(name = "orderid")
    private Integer orderid;
    @Size(max = 200)
    @Column(name = "ubsid")
    private String ubsid;
    @Column(name = "quantitypresent")
    private Integer quantitypresent;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @Size(max = 200)
    @Column(name = "productSerial")
    private String productSerial;
    @Column(name = "qtypurchased")
    private Integer qtypurchased;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agrodealerproductid")
    private Collection<Userpurchase> userpurchaseCollection;
    @JoinColumn(name = "createdby", referencedColumnName = "idusers")
    @ManyToOne
    private User createdby;
    @JoinColumn(name = "status", referencedColumnName = "idstatus")
    @ManyToOne
    private Status status;
    @JoinColumn(name = "dealerid", referencedColumnName = "idagrodealers")
    @ManyToOne
    private Agrodealers dealerid;
    @JoinColumn(name = "agrodealerproductunit", referencedColumnName = "idagrodealerproductunits")
    @ManyToOne
    private Agrodealerproductunits agrodealerproductunit;
    @OneToMany(mappedBy = "variety")
    private Collection<Agrodealerproducts> agrodealerproductsCollection;
    @JoinColumn(name = "variety", referencedColumnName = "idagrodealerproducts")
    @ManyToOne
    private Agrodealerproducts variety;
    @JoinColumn(name = "producttype", referencedColumnName = "idagrodealerproducttypes")
    @ManyToOne
    private Agrodealerproducttypes producttype;
    @OneToMany(mappedBy = "productpackage")
    private Collection<Agrodealerproducts> agrodealerproductsCollection1;
    @JoinColumn(name = "productpackage", referencedColumnName = "idagrodealerproducts")
    @ManyToOne
    private Agrodealerproducts productpackage;

    public Agrodealerproducts() {
    }

    public Agrodealerproducts(Integer idagrodealerproducts) {
        this.idagrodealerproducts = idagrodealerproducts;
    }

    public Integer getIdagrodealerproducts() {
        return idagrodealerproducts;
    }

    public void setIdagrodealerproducts(Integer idagrodealerproducts) {
        this.idagrodealerproducts = idagrodealerproducts;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getUbsid() {
        return ubsid;
    }

    public void setUbsid(String ubsid) {
        this.ubsid = ubsid;
    }

    public Integer getQuantitypresent() {
        return quantitypresent;
    }

    public void setQuantitypresent(Integer quantitypresent) {
        this.quantitypresent = quantitypresent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductSerial() {
        return productSerial;
    }

    public void setProductSerial(String productSerial) {
        this.productSerial = productSerial;
    }

    public Integer getQtypurchased() {
        return qtypurchased;
    }

    public void setQtypurchased(Integer qtypurchased) {
        this.qtypurchased = qtypurchased;
    }

    @XmlTransient
    public Collection<Userpurchase> getUserpurchaseCollection() {
        return userpurchaseCollection;
    }

    public void setUserpurchaseCollection(Collection<Userpurchase> userpurchaseCollection) {
        this.userpurchaseCollection = userpurchaseCollection;
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

    public Agrodealers getDealerid() {
        return dealerid;
    }

    public void setDealerid(Agrodealers dealerid) {
        this.dealerid = dealerid;
    }

    public Agrodealerproductunits getAgrodealerproductunit() {
        return agrodealerproductunit;
    }

    public void setAgrodealerproductunit(Agrodealerproductunits agrodealerproductunit) {
        this.agrodealerproductunit = agrodealerproductunit;
    }

    @XmlTransient
    public Collection<Agrodealerproducts> getAgrodealerproductsCollection() {
        return agrodealerproductsCollection;
    }

    public void setAgrodealerproductsCollection(Collection<Agrodealerproducts> agrodealerproductsCollection) {
        this.agrodealerproductsCollection = agrodealerproductsCollection;
    }

    public Agrodealerproducts getVariety() {
        return variety;
    }

    public void setVariety(Agrodealerproducts variety) {
        this.variety = variety;
    }

    public Agrodealerproducttypes getProducttype() {
        return producttype;
    }

    public void setProducttype(Agrodealerproducttypes producttype) {
        this.producttype = producttype;
    }

    @XmlTransient
    public Collection<Agrodealerproducts> getAgrodealerproductsCollection1() {
        return agrodealerproductsCollection1;
    }

    public void setAgrodealerproductsCollection1(Collection<Agrodealerproducts> agrodealerproductsCollection1) {
        this.agrodealerproductsCollection1 = agrodealerproductsCollection1;
    }

    public Agrodealerproducts getProductpackage() {
        return productpackage;
    }

    public void setProductpackage(Agrodealerproducts productpackage) {
        this.productpackage = productpackage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idagrodealerproducts != null ? idagrodealerproducts.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agrodealerproducts)) {
            return false;
        }
        Agrodealerproducts other = (Agrodealerproducts) object;
        if ((this.idagrodealerproducts == null && other.idagrodealerproducts != null) || (this.idagrodealerproducts != null && !this.idagrodealerproducts.equals(other.idagrodealerproducts))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amon.db.Agrodealerproducts[ idagrodealerproducts=" + idagrodealerproducts + " ]";
    }
    
}
