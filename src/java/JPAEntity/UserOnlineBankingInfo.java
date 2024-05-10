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
@Table(name = "USER_ONLINE_BANKING_INFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserOnlineBankingInfo.findAll", query = "SELECT u FROM UserOnlineBankingInfo u"),
    @NamedQuery(name = "UserOnlineBankingInfo.findByUserOnlineBankingInfoId", query = "SELECT u FROM UserOnlineBankingInfo u WHERE u.userOnlineBankingInfoId = :userOnlineBankingInfoId"),
    @NamedQuery(name = "UserOnlineBankingInfo.findByDateAdded", query = "SELECT u FROM UserOnlineBankingInfo u WHERE u.dateAdded = :dateAdded")})
public class UserOnlineBankingInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "USER_ONLINE_BANKING_INFO_ID")
    private String userOnlineBankingInfoId;
    @Column(name = "DATE_ADDED")
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    @JoinColumn(name = "ONLINE_BANKING_ID", referencedColumnName = "ONLINE_BANKING_ID")
    @ManyToOne(optional = false)
    private OnlineBankingInfo onlineBankingId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private Users userId;

    public UserOnlineBankingInfo() {
    }

    public UserOnlineBankingInfo(String userOnlineBankingInfoId) {
        this.userOnlineBankingInfoId = userOnlineBankingInfoId;
    }

    public String getUserOnlineBankingInfoId() {
        return userOnlineBankingInfoId;
    }

    public void setUserOnlineBankingInfoId(String userOnlineBankingInfoId) {
        this.userOnlineBankingInfoId = userOnlineBankingInfoId;
    }
    
    public void setUserOnlineBankingInfoId(long count) {
        this.userOnlineBankingInfoId = String.format("UBNK%05d", count);
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public OnlineBankingInfo getOnlineBankingId() {
        return onlineBankingId;
    }

    public void setOnlineBankingId(OnlineBankingInfo onlineBankingId) {
        this.onlineBankingId = onlineBankingId;
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
        hash += (userOnlineBankingInfoId != null ? userOnlineBankingInfoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserOnlineBankingInfo)) {
            return false;
        }
        UserOnlineBankingInfo other = (UserOnlineBankingInfo) object;
        if ((this.userOnlineBankingInfoId == null && other.userOnlineBankingInfoId != null) || (this.userOnlineBankingInfoId != null && !this.userOnlineBankingInfoId.equals(other.userOnlineBankingInfoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.UserOnlineBankingInfo[ userOnlineBankingInfoId=" + userOnlineBankingInfoId + " ]";
    }
    
}
