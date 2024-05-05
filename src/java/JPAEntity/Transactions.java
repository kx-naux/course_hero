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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "TRANSACTIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactions.findAll", query = "SELECT t FROM Transactions t"),
    @NamedQuery(name = "Transactions.findByTransactionId", query = "SELECT t FROM Transactions t WHERE t.transactionId = :transactionId"),
    @NamedQuery(name = "Transactions.findBySubtotal", query = "SELECT t FROM Transactions t WHERE t.subtotal = :subtotal"),
    @NamedQuery(name = "Transactions.findByPromoAmount", query = "SELECT t FROM Transactions t WHERE t.promoAmount = :promoAmount"),
    @NamedQuery(name = "Transactions.findByTax", query = "SELECT t FROM Transactions t WHERE t.tax = :tax"),
    @NamedQuery(name = "Transactions.findByTransactionType", query = "SELECT t FROM Transactions t WHERE t.transactionType = :transactionType"),
    @NamedQuery(name = "Transactions.findByTransactionFee", query = "SELECT t FROM Transactions t WHERE t.transactionFee = :transactionFee"),
    @NamedQuery(name = "Transactions.findByTotal", query = "SELECT t FROM Transactions t WHERE t.total = :total"),
    @NamedQuery(name = "Transactions.findByStatus", query = "SELECT t FROM Transactions t WHERE t.status = :status"),
    @NamedQuery(name = "Transactions.findByTimeAdded", query = "SELECT t FROM Transactions t WHERE t.timeAdded = :timeAdded")})
public class Transactions implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "SUBTOTAL")
    private double subtotal;
    @Basic(optional = false)
    @NotNull()
    @Column(name = "PROMO_AMOUNT")
    private double promoAmount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TAX")
    private double tax;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSACTION_FEE")
    private double transactionFee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private double total;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIME_ADDED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeAdded;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "TRANSACTION_ID")
    private String transactionId;
    @JoinColumn(name = "PROMOTION_ID", referencedColumnName = "PROMOTION_ID")
    @ManyToOne
    private Promotions promotionId;
    @JoinColumn(name = "SHIPPING_ID", referencedColumnName = "SHIPPING_ID")
    @ManyToOne(optional = false)
    private Shipping shippingId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private Users userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactions")
    private List<Orders> ordersList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "transactionId")
    private Payments payments;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactionId")
    private List<Refunds> refundsList;

    public Transactions() {
    }

    public Transactions(String transactionId) {
        this.transactionId = transactionId;
    }

    public Transactions(String transactionId, double subtotal, double promoAmount, double tax, String transactionType, double transactionFee, double total, String status, Date timeAdded) {
        this.transactionId = transactionId;
        this.subtotal = subtotal;
        this.promoAmount = promoAmount;
        this.tax = tax;
        this.transactionType = transactionType;
        this.transactionFee = transactionFee;
        this.total = total;
        this.status = status;
        this.timeAdded = timeAdded;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }


    public double getPromoAmount() {
        return promoAmount;
    }

    public void setPromoAmount(double promoAmount) {
        this.promoAmount = promoAmount;
    }


    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }


    public Date getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Date timeAdded) {
        this.timeAdded = timeAdded;
    }

    public Promotions getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Promotions promotionId) {
        this.promotionId = promotionId;
    }

    public Shipping getShippingId() {
        return shippingId;
    }

    public void setShippingId(Shipping shippingId) {
        this.shippingId = shippingId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @XmlTransient
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public Payments getPayments() {
        return payments;
    }

    public void setPayments(Payments payments) {
        this.payments = payments;
    }

    @XmlTransient
    public List<Refunds> getRefundsList() {
        return refundsList;
    }

    public void setRefundsList(List<Refunds> refundsList) {
        this.refundsList = refundsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactions)) {
            return false;
        }
        Transactions other = (Transactions) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.Transactions[ transactionId=" + transactionId + " ]";
    }

   

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }


    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

   
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
