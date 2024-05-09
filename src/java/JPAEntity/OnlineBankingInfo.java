/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPAEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author User
 */
@Entity
@Table(name = "ONLINE_BANKING_INFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OnlineBankingInfo.findAll", query = "SELECT o FROM OnlineBankingInfo o"),
    @NamedQuery(name = "OnlineBankingInfo.findByOnlineBankingId", query = "SELECT o FROM OnlineBankingInfo o WHERE o.onlineBankingId = :onlineBankingId"),
    @NamedQuery(name = "OnlineBankingInfo.findByBankName", query = "SELECT o FROM OnlineBankingInfo o WHERE o.bankName = :bankName"),
    @NamedQuery(name = "OnlineBankingInfo.findByDateAdded", query = "SELECT o FROM OnlineBankingInfo o WHERE o.dateAdded = :dateAdded")})
public class OnlineBankingInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "ONLINE_BANKING_ID")
    private String onlineBankingId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "BANK_NAME")
    private String bankName;
    @Column(name = "DATE_ADDED")
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "onlineBankingId")
    private List<UserOnlineBankingInfo> userOnlineBankingInfoList;

    public OnlineBankingInfo() {
    }

    public OnlineBankingInfo(String onlineBankingId) {
        this.onlineBankingId = onlineBankingId;
    }

    public OnlineBankingInfo(String onlineBankingId, String bankName) {
        this.onlineBankingId = onlineBankingId;
        this.bankName = bankName;
    }

    public String getOnlineBankingId() {
        return onlineBankingId;
    }

    public void setOnlineBankingId(String onlineBankingId) {
        this.onlineBankingId = onlineBankingId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @XmlTransient
    public List<UserOnlineBankingInfo> getUserOnlineBankingInfoList() {
        return userOnlineBankingInfoList;
    }

    public void setUserOnlineBankingInfoList(List<UserOnlineBankingInfo> userOnlineBankingInfoList) {
        this.userOnlineBankingInfoList = userOnlineBankingInfoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (onlineBankingId != null ? onlineBankingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OnlineBankingInfo)) {
            return false;
        }
        OnlineBankingInfo other = (OnlineBankingInfo) object;
        if ((this.onlineBankingId == null && other.onlineBankingId != null) || (this.onlineBankingId != null && !this.onlineBankingId.equals(other.onlineBankingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.OnlineBankingInfo[ onlineBankingId=" + onlineBankingId + " ]";
    }
    
}
