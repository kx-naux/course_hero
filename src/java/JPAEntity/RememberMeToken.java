/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPAEntity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "REMEMBER_ME_TOKEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RememberMeToken.findAll", query = "SELECT r FROM RememberMeToken r"),
    @NamedQuery(name = "RememberMeToken.findByToken", query = "SELECT r FROM RememberMeToken r WHERE r.token = :token"),
    @NamedQuery(name = "RememberMeToken.findByDateIssued", query = "SELECT r FROM RememberMeToken r WHERE r.dateIssued = :dateIssued")})
public class RememberMeToken implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_ISSUED")
    @Temporal(TemporalType.TIMESTAMP)   
    private Date dateIssued;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TOKEN")
    private String token;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private Users userId;

    public RememberMeToken() {
    }

    public RememberMeToken(String token) {
        this.token = token;
    }

    public RememberMeToken(String token, Date dateIssued) {
        this.token = token;
        this.dateIssued = dateIssued;
    }
    
    public RememberMeToken(String token, Date dateIssued, Users userID) {
        this.userId = userID;
        this.token = token;
        this.dateIssued = dateIssued;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(Date dateIssued) {
        this.dateIssued = dateIssued;
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
        hash += (token != null ? token.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RememberMeToken)) {
            return false;
        }
        RememberMeToken other = (RememberMeToken) object;
        if ((this.token == null && other.token != null) || (this.token != null && !this.token.equals(other.token))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.RememberMeToken[ token=" + token + " ]";
    }




}
