/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.br.lp3.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rodrigo T. L. Takeuti
 */
@Entity
@Table(name = "PROFILE_ACCOUNT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfileAccount.findAll", query = "SELECT p FROM ProfileAccount p"),
    @NamedQuery(name = "ProfileAccount.findByIdAccount", query = "SELECT p FROM ProfileAccount p WHERE p.idAccount = :idAccount"),
    @NamedQuery(name = "ProfileAccount.findByName", query = "SELECT p FROM ProfileAccount p WHERE p.name = :name"),
    @NamedQuery(name = "ProfileAccount.findByBirthDate", query = "SELECT p FROM ProfileAccount p WHERE p.birthDate = :birthDate"),
    @NamedQuery(name = "ProfileAccount.findBySignupDate", query = "SELECT p FROM ProfileAccount p WHERE p.signupDate = :signupDate")})
public class ProfileAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ACCOUNT")
    private Integer idAccount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BIRTH_DATE")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SIGNUP_DATE")
    @Temporal(TemporalType.DATE)
    private Date signupDate;
    @JoinColumn(name = "ID_ACCOUNT", referencedColumnName = "ID_ACCOUNT", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Account account;

    public ProfileAccount() {
    }

    public ProfileAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public ProfileAccount(Integer idAccount, String name, Date birthDate, Date signupDate) {
        this.idAccount = idAccount;
        this.name = name;
        this.birthDate = birthDate;
        this.signupDate = signupDate;
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(Date signupDate) {
        this.signupDate = signupDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccount != null ? idAccount.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfileAccount)) {
            return false;
        }
        ProfileAccount other = (ProfileAccount) object;
        if ((this.idAccount == null && other.idAccount != null) || (this.idAccount != null && !this.idAccount.equals(other.idAccount))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.br.lp3.model.entities.ProfileAccount[ idAccount=" + idAccount + " ]";
    }
    
}
