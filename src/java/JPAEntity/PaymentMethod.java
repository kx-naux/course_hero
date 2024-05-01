/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPAEntity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "PAYMENT_METHOD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentMethod.findAll", query = "SELECT p FROM PaymentMethod p"),
    @NamedQuery(name = "PaymentMethod.findByPaymentMethodId", query = "SELECT p FROM PaymentMethod p WHERE p.paymentMethodId = :paymentMethodId"),
    @NamedQuery(name = "PaymentMethod.findByPaymentName", query = "SELECT p FROM PaymentMethod p WHERE p.paymentName = :paymentName"),
    @NamedQuery(name = "PaymentMethod.findByAcceptedCurrencies", query = "SELECT p FROM PaymentMethod p WHERE p.acceptedCurrencies = :acceptedCurrencies")})
public class PaymentMethod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "PAYMENT_METHOD_ID")
    private String paymentMethodId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PAYMENT_NAME")
    private String paymentName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ACCEPTED_CURRENCIES")
    private String acceptedCurrencies;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentMethodId")
    private List<Payments> paymentsList;

    public PaymentMethod() {
    }

    public PaymentMethod(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public PaymentMethod(String paymentMethodId, String paymentName, String acceptedCurrencies) {
        this.paymentMethodId = paymentMethodId;
        this.paymentName = paymentName;
        this.acceptedCurrencies = acceptedCurrencies;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getAcceptedCurrencies() {
        return acceptedCurrencies;
    }

    public void setAcceptedCurrencies(String acceptedCurrencies) {
        this.acceptedCurrencies = acceptedCurrencies;
    }

    @XmlTransient
    public List<Payments> getPaymentsList() {
        return paymentsList;
    }

    public void setPaymentsList(List<Payments> paymentsList) {
        this.paymentsList = paymentsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentMethodId != null ? paymentMethodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentMethod)) {
            return false;
        }
        PaymentMethod other = (PaymentMethod) object;
        if ((this.paymentMethodId == null && other.paymentMethodId != null) || (this.paymentMethodId != null && !this.paymentMethodId.equals(other.paymentMethodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.PaymentMethod[ paymentMethodId=" + paymentMethodId + " ]";
    }
    
}
