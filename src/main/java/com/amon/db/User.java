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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FAZOUL
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByIdusers", query = "SELECT u FROM User u WHERE u.idusers = :idusers"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPword", query = "SELECT u FROM User u WHERE u.pword = :pword"),
    @NamedQuery(name = "User.findByCreatedAt", query = "SELECT u FROM User u WHERE u.createdAt = :createdAt"),
    @NamedQuery(name = "User.findByLastLogin", query = "SELECT u FROM User u WHERE u.lastLogin = :lastLogin"),
    @NamedQuery(name = "User.findByDepartment", query = "SELECT u FROM User u WHERE u.department = :department"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPhone", query = "SELECT u FROM User u WHERE u.phone = :phone"),
    @NamedQuery(name = "User.findByStaffID", query = "SELECT u FROM User u WHERE u.staffID = :staffID"),
    @NamedQuery(name = "User.findByRegion", query = "SELECT u FROM User u WHERE u.region = :region"),
    @NamedQuery(name = "User.findByGender", query = "SELECT u FROM User u WHERE u.gender = :gender"),
    @NamedQuery(name = "User.findByDisability", query = "SELECT u FROM User u WHERE u.disability = :disability"),
    @NamedQuery(name = "User.findByCropgrown", query = "SELECT u FROM User u WHERE u.cropgrown = :cropgrown"),
    @NamedQuery(name = "User.findByFarmergroup", query = "SELECT u FROM User u WHERE u.farmergroup = :farmergroup")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusers")
    private Integer idusers;
    @Size(max = 45)
    @Column(name = "username")
    private String username;
    @Size(max = 45)
    @Column(name = "pword")
    private String pword;
    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "lastLogin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Column(name = "department")
    private Integer department;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "phone")
    private String phone;
    @Column(name = "staffID")
    private Integer staffID;
    @Column(name = "region")
    private Integer region;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "disability")
    private String disability;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cropgrown")
    private String cropgrown;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "farmergroup")
    private String farmergroup;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdby")
    private Collection<Regions> regionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdby")
    private Collection<Userpurchase> userpurchaseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<Userpurchase> userpurchaseCollection1;
    @OneToMany(mappedBy = "createdby")
    private Collection<Banks> banksCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdby")
    private Collection<Cropproducts> cropproductsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<Ewallet> ewalletCollection;
    @OneToMany(mappedBy = "createdby")
    private Collection<Ewallet> ewalletCollection1;
    @OneToMany(mappedBy = "createdby")
    private Collection<Agrodealerproducts> agrodealerproductsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdby")
    private Collection<Agrodealers> agrodealersCollection;
    @OneToMany(mappedBy = "ownerid")
    private Collection<Agrodealers> agrodealersCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdby")
    private Collection<Distributionchannel> distributionchannelCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdby")
    private Collection<Farmergroups> farmergroupsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdby")
    private Collection<Audit> auditCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdby")
    private Collection<Agrodealerproductunits> agrodealerproductunitsCollection;
    @OneToMany(mappedBy = "createdby")
    private Collection<Govtpayments> govtpaymentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdby")
    private Collection<Orders> ordersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdby")
    private Collection<Agrodealerproducttypes> agrodealerproducttypesCollection;
    @OneToMany(mappedBy = "createdBy")
    private Collection<User> userCollection;
    @JoinColumn(name = "createdBy", referencedColumnName = "idusers")
    @ManyToOne
    private User createdBy;
    @JoinColumn(name = "statusID", referencedColumnName = "idstatus")
    @ManyToOne
    private Status statusID;
    @JoinColumn(name = "groupID", referencedColumnName = "idgroups")
    @ManyToOne
    private Usergroup groupID;

    public User() {
    }

    public User(Integer idusers) {
        this.idusers = idusers;
    }

    public User(Integer idusers, String gender, String disability, String cropgrown, String farmergroup) {
        this.idusers = idusers;
        this.gender = gender;
        this.disability = disability;
        this.cropgrown = cropgrown;
        this.farmergroup = farmergroup;
    }

    public Integer getIdusers() {
        return idusers;
    }

    public void setIdusers(Integer idusers) {
        this.idusers = idusers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPword() {
        return pword;
    }

    public void setPword(String pword) {
        this.pword = pword;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStaffID() {
        return staffID;
    }

    public void setStaffID(Integer staffID) {
        this.staffID = staffID;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getCropgrown() {
        return cropgrown;
    }

    public void setCropgrown(String cropgrown) {
        this.cropgrown = cropgrown;
    }

    public String getFarmergroup() {
        return farmergroup;
    }

    public void setFarmergroup(String farmergroup) {
        this.farmergroup = farmergroup;
    }

    @XmlTransient
    public Collection<Regions> getRegionsCollection() {
        return regionsCollection;
    }

    public void setRegionsCollection(Collection<Regions> regionsCollection) {
        this.regionsCollection = regionsCollection;
    }

    @XmlTransient
    public Collection<Userpurchase> getUserpurchaseCollection() {
        return userpurchaseCollection;
    }

    public void setUserpurchaseCollection(Collection<Userpurchase> userpurchaseCollection) {
        this.userpurchaseCollection = userpurchaseCollection;
    }

    @XmlTransient
    public Collection<Userpurchase> getUserpurchaseCollection1() {
        return userpurchaseCollection1;
    }

    public void setUserpurchaseCollection1(Collection<Userpurchase> userpurchaseCollection1) {
        this.userpurchaseCollection1 = userpurchaseCollection1;
    }

    @XmlTransient
    public Collection<Banks> getBanksCollection() {
        return banksCollection;
    }

    public void setBanksCollection(Collection<Banks> banksCollection) {
        this.banksCollection = banksCollection;
    }

    @XmlTransient
    public Collection<Cropproducts> getCropproductsCollection() {
        return cropproductsCollection;
    }

    public void setCropproductsCollection(Collection<Cropproducts> cropproductsCollection) {
        this.cropproductsCollection = cropproductsCollection;
    }

    @XmlTransient
    public Collection<Ewallet> getEwalletCollection() {
        return ewalletCollection;
    }

    public void setEwalletCollection(Collection<Ewallet> ewalletCollection) {
        this.ewalletCollection = ewalletCollection;
    }

    @XmlTransient
    public Collection<Ewallet> getEwalletCollection1() {
        return ewalletCollection1;
    }

    public void setEwalletCollection1(Collection<Ewallet> ewalletCollection1) {
        this.ewalletCollection1 = ewalletCollection1;
    }

    @XmlTransient
    public Collection<Agrodealerproducts> getAgrodealerproductsCollection() {
        return agrodealerproductsCollection;
    }

    public void setAgrodealerproductsCollection(Collection<Agrodealerproducts> agrodealerproductsCollection) {
        this.agrodealerproductsCollection = agrodealerproductsCollection;
    }

    @XmlTransient
    public Collection<Agrodealers> getAgrodealersCollection() {
        return agrodealersCollection;
    }

    public void setAgrodealersCollection(Collection<Agrodealers> agrodealersCollection) {
        this.agrodealersCollection = agrodealersCollection;
    }

    @XmlTransient
    public Collection<Agrodealers> getAgrodealersCollection1() {
        return agrodealersCollection1;
    }

    public void setAgrodealersCollection1(Collection<Agrodealers> agrodealersCollection1) {
        this.agrodealersCollection1 = agrodealersCollection1;
    }

    @XmlTransient
    public Collection<Distributionchannel> getDistributionchannelCollection() {
        return distributionchannelCollection;
    }

    public void setDistributionchannelCollection(Collection<Distributionchannel> distributionchannelCollection) {
        this.distributionchannelCollection = distributionchannelCollection;
    }

    @XmlTransient
    public Collection<Farmergroups> getFarmergroupsCollection() {
        return farmergroupsCollection;
    }

    public void setFarmergroupsCollection(Collection<Farmergroups> farmergroupsCollection) {
        this.farmergroupsCollection = farmergroupsCollection;
    }

    @XmlTransient
    public Collection<Audit> getAuditCollection() {
        return auditCollection;
    }

    public void setAuditCollection(Collection<Audit> auditCollection) {
        this.auditCollection = auditCollection;
    }

    @XmlTransient
    public Collection<Agrodealerproductunits> getAgrodealerproductunitsCollection() {
        return agrodealerproductunitsCollection;
    }

    public void setAgrodealerproductunitsCollection(Collection<Agrodealerproductunits> agrodealerproductunitsCollection) {
        this.agrodealerproductunitsCollection = agrodealerproductunitsCollection;
    }

    @XmlTransient
    public Collection<Govtpayments> getGovtpaymentsCollection() {
        return govtpaymentsCollection;
    }

    public void setGovtpaymentsCollection(Collection<Govtpayments> govtpaymentsCollection) {
        this.govtpaymentsCollection = govtpaymentsCollection;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @XmlTransient
    public Collection<Agrodealerproducttypes> getAgrodealerproducttypesCollection() {
        return agrodealerproducttypesCollection;
    }

    public void setAgrodealerproducttypesCollection(Collection<Agrodealerproducttypes> agrodealerproducttypesCollection) {
        this.agrodealerproducttypesCollection = agrodealerproducttypesCollection;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Status getStatusID() {
        return statusID;
    }

    public void setStatusID(Status statusID) {
        this.statusID = statusID;
    }

    public Usergroup getGroupID() {
        return groupID;
    }

    public void setGroupID(Usergroup groupID) {
        this.groupID = groupID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusers != null ? idusers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.idusers == null && other.idusers != null) || (this.idusers != null && !this.idusers.equals(other.idusers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.amon.db.User[ idusers=" + idusers + " ]";
    }
    
}
