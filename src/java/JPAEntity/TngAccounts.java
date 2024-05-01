/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPAEntity;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "TNG_ACCOUNTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TngAccounts.findAll", query = "SELECT t FROM TngAccounts t"),
    @NamedQuery(name = "TngAccounts.findByTngId", query = "SELECT t FROM TngAccounts t WHERE t.tngId = :tngId"),
    @NamedQuery(name = "TngAccounts.findByPhoneno", query = "SELECT t FROM TngAccounts t WHERE t.phoneno = :phoneno")})
public class TngAccounts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "TNG_ID")
    private String tngId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "PHONENO")
    private String phoneno;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @OneToOne(optional = false)
    private Users userId;

    public TngAccounts() {
    }

    public TngAccounts(String tngId) {
        this.tngId = tngId;
    }

    public TngAccounts(String tngId, String phoneno) {
        this.tngId = tngId;
        this.phoneno = phoneno;
    }

    public String getTngId() {
        return tngId;
    }

    public void setTngId(String tngId) {
        this.tngId = tngId;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tngId != null ? tngId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TngAccounts)) {
            return false;
        }
        TngAccounts other = (TngAccounts) object;
        if ((this.tngId == null && other.tngId != null) || (this.tngId != null && !this.tngId.equals(other.tngId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.TngAccounts[ tngId=" + tngId + " ]";
    }
    
}
