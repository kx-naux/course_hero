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
import javax.persistence.OneToOne;
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
@Table(name = "PAYMENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payments.findAll", query = "SELECT p FROM Payments p"),
    @NamedQuery(name = "Payments.findByPaymentId", query = "SELECT p FROM Payments p WHERE p.paymentId = :paymentId"),
    @NamedQuery(name = "Payments.findByAccountNumber", query = "SELECT p FROM Payments p WHERE p.accountNumber = :accountNumber"),
    @NamedQuery(name = "Payments.findByPaymentGateway", query = "SELECT p FROM Payments p WHERE p.paymentGateway = :paymentGateway"),
    @NamedQuery(name = "Payments.findByAmount", query = "SELECT p FROM Payments p WHERE p.amount = :amount"),
    @NamedQuery(name = "Payments.findByStatus", query = "SELECT p FROM Payments p WHERE p.status = :status"),
    @NamedQuery(name = "Payments.findByDateIssued", query = "SELECT p FROM Payments p WHERE p.dateIssued = :dateIssued")})
public class Payments implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PAYMENT_GATEWAY")
    private String paymentGateway;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private double amount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_ISSUED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateIssued;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "PAYMENT_ID")
    private String paymentId;
    @JoinColumn(name = "PAYMENT_METHOD_ID", referencedColumnName = "PAYMENT_METHOD_ID")
    @ManyToOne(optional = false)
    private PaymentMethod paymentMethodId;
    @JoinColumn(name = "TRANSACTION_ID", referencedColumnName = "TRANSACTION_ID")
    @OneToOne(optional = false)
    private Transactions transactionId;

    public Payments() {
    }

    public Payments(String paymentId) {
        this.paymentId = paymentId;
    }

    public Payments(String paymentId, String accountNumber, String paymentGateway, double amount, String status, Date dateIssued) {
        this.paymentId = paymentId;
        this.accountNumber = accountNumber;
        this.paymentGateway = paymentGateway;
        this.amount = amount;
        this.status = status;
        this.dateIssued = dateIssued;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPaymentGateway() {
        return paymentGateway;
    }

    public void setPaymentGateway(String paymentGateway) {
        this.paymentGateway = paymentGateway;
    }


    public Date getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(Date dateIssued) {
        this.dateIssued = dateIssued;
    }

    public PaymentMethod getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(PaymentMethod paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
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
        hash += (paymentId != null ? paymentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payments)) {
            return false;
        }
        Payments other = (Payments) object;
        if ((this.paymentId == null && other.paymentId != null) || (this.paymentId != null && !this.paymentId.equals(other.paymentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.Payments[ paymentId=" + paymentId + " ]";
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
