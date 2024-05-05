/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPAEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "PROMOTIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promotions.findAll", query = "SELECT p FROM Promotions p"),
    @NamedQuery(name = "Promotions.findByPromotionId", query = "SELECT p FROM Promotions p WHERE p.promotionId = :promotionId"),
    @NamedQuery(name = "Promotions.findByStartTime", query = "SELECT p FROM Promotions p WHERE p.startTime = :startTime"),
    @NamedQuery(name = "Promotions.findByEndTime", query = "SELECT p FROM Promotions p WHERE p.endTime = :endTime"),
    @NamedQuery(name = "Promotions.findByPromoType", query = "SELECT p FROM Promotions p WHERE p.promoType = :promoType"),
    @NamedQuery(name = "Promotions.findByAmount", query = "SELECT p FROM Promotions p WHERE p.amount = :amount"),
    @NamedQuery(name = "Promotions.findByMinReq", query = "SELECT p FROM Promotions p WHERE p.minReq = :minReq"),
    @NamedQuery(name = "Promotions.findByLegalTnc", query = "SELECT p FROM Promotions p WHERE p.legalTnc = :legalTnc"),
    @NamedQuery(name = "Promotions.findByStatus", query = "SELECT p FROM Promotions p WHERE p.status = :status"),
    @NamedQuery(name = "Promotions.findByGeographicRestiriction", query = "SELECT p FROM Promotions p WHERE p.geographicRestiriction = :geographicRestiriction")})
public class Promotions implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PROMOTION_NAME")
    private String promotionName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "PROMO_DESCRIPTION")
    private String promoDescription;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 10)
    @Column(name = "PROMO_CODE")
    private String promoCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "START_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "END_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PROMO_TYPE")
    private String promoType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private double amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MIN_REQ")
    private double minReq;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 30)
    @Column(name = "LEGAL_TNC")
    private String legalTnc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "STATUS")
    private String status;
    @Size(max = 300)
    @Column(name = "GEOGRAPHIC_RESTIRICTION")
    private String geographicRestiriction;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "PROMOTION_ID")
    private String promotionId;
    @OneToMany(mappedBy = "promotionId")
    private List<Transactions> transactionsList;

    public Promotions() {
    }

    public Promotions(String promotionId) {
        this.promotionId = promotionId;
    }

    public Promotions(String promotionId, Date startTime, Date endTime, String promoType, double amount, double minReq, String legalTnc, String status) {
        this.promotionId = promotionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.promoType = promoType;
        this.amount = amount;
        this.minReq = minReq;
        this.legalTnc = legalTnc;
        this.status = status;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPromoType() {
        return promoType;
    }

    public void setPromoType(String promoType) {
        this.promoType = promoType;
    }


    public double getMinReq() {
        return minReq;
    }

    public void setMinReq(double minReq) {
        this.minReq = minReq;
    }

    public String getLegalTnc() {
        return legalTnc;
    }

    public void setLegalTnc(String legalTnc) {
        this.legalTnc = legalTnc;
    }


    public String getGeographicRestiriction() {
        return geographicRestiriction;
    }

    public void setGeographicRestiriction(String geographicRestiriction) {
        this.geographicRestiriction = geographicRestiriction;
    }

    @XmlTransient
    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (promotionId != null ? promotionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promotions)) {
            return false;
        }
        Promotions other = (Promotions) object;
        if ((this.promotionId == null && other.promotionId != null) || (this.promotionId != null && !this.promotionId.equals(other.promotionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.Promotions[ promotionId=" + promotionId + " ]";
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }


    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

   

    public String getPromoDescription() {
        return promoDescription;
    }

    public void setPromoDescription(String promoDescription) {
        this.promoDescription = promoDescription;
    }




    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

   

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   

    
}
