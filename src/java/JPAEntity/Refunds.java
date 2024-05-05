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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "REFUNDS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Refunds.findAll", query = "SELECT r FROM Refunds r"),
    @NamedQuery(name = "Refunds.findByRefundId", query = "SELECT r FROM Refunds r WHERE r.refundId = :refundId"),
    @NamedQuery(name = "Refunds.findByRefundType", query = "SELECT r FROM Refunds r WHERE r.refundType = :refundType"),
    @NamedQuery(name = "Refunds.findByReason", query = "SELECT r FROM Refunds r WHERE r.reason = :reason")})
public class Refunds implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "REFUND_TYPE")
    private String refundType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "REASON")
    private String reason;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "REFUND_ID")
    private String refundId;
    @JoinColumn(name = "TRANSACTION_ID", referencedColumnName = "TRANSACTION_ID")
    @ManyToOne(optional = false)
    private Transactions transactionId;

    public Refunds() {
    }

    public Refunds(String refundId) {
        this.refundId = refundId;
    }

    public Refunds(String refundId, String refundType, String reason) {
        this.refundId = refundId;
        this.refundType = refundType;
        this.reason = reason;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }


    public Transactions getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Transactions transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (refundId != null ? refundId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Refunds)) {
            return false;
        }
        Refunds other = (Refunds) object;
        if ((this.refundId == null && other.refundId != null) || (this.refundId != null && !this.refundId.equals(other.refundId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.Refunds[ refundId=" + refundId + " ]";
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
}
