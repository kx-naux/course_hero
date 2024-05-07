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
@Table(name = "SHIPPING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shipping.findAll", query = "SELECT s FROM Shipping s"),
    @NamedQuery(name = "Shipping.findByShippingId", query = "SELECT s FROM Shipping s WHERE s.shippingId = :shippingId"),
    @NamedQuery(name = "Shipping.findByShippingDate", query = "SELECT s FROM Shipping s WHERE s.shippingDate = :shippingDate"),
    @NamedQuery(name = "Shipping.findByExpectedDeliveryDate", query = "SELECT s FROM Shipping s WHERE s.expectedDeliveryDate = :expectedDeliveryDate"),
    @NamedQuery(name = "Shipping.findByShippingCost", query = "SELECT s FROM Shipping s WHERE s.shippingCost = :shippingCost"),
    @NamedQuery(name = "Shipping.findByTtlWeightKg", query = "SELECT s FROM Shipping s WHERE s.ttlWeightKg = :ttlWeightKg"),
    @NamedQuery(name = "Shipping.findByDimenstionCmHxwxl", query = "SELECT s FROM Shipping s WHERE s.dimenstionCmHxwxl = :dimenstionCmHxwxl"),
    @NamedQuery(name = "Shipping.findByShippingNotes", query = "SELECT s FROM Shipping s WHERE s.shippingNotes = :shippingNotes")})
public class Shipping implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "SHIPPING_DATE")
    @Temporal(TemporalType.DATE)
    private Date shippingDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXPECTED_DELIVERY_DATE")
    @Temporal(TemporalType.DATE)
    private Date expectedDeliveryDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SHIPPING_COST")
    private double shippingCost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TTL_WEIGHT_KG")
    private double ttlWeightKg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "DIMENSTION_CM_HXWXL")
    private String dimenstionCmHxwxl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "SHIPPING_NOTES")
    private String shippingNotes;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "SHIPPING_ID")
    private String shippingId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shippingId")
    private List<Transactions> transactionsList;
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID")
    @ManyToOne(optional = false)
    private BillingAddress addressId;

    public Shipping() {
    }

    public Shipping(String shippingId) {
        this.shippingId = shippingId;
    }

    public Shipping(String shippingId, Date shippingDate, Date expectedDeliveryDate, double shippingCost, double ttlWeightKg, String dimenstionCmHxwxl, String shippingNotes) {
        this.shippingId = shippingId;
        this.shippingDate = shippingDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.shippingCost = shippingCost;
        this.ttlWeightKg = ttlWeightKg;
        this.dimenstionCmHxwxl = dimenstionCmHxwxl;
        this.shippingNotes = shippingNotes;
    }

    public String getShippingId() {
        return shippingId;
    }

    public void setShippingId(String shippingId) {
        this.shippingId = shippingId;
    }
    
    public void setShippingId(long count) {
        this.shippingId = String.format("SH%07d", count);
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public double getTtlWeightKg() {
        return ttlWeightKg;
    }

    public void setTtlWeightKg(double ttlWeightKg) {
        this.ttlWeightKg = ttlWeightKg;
    }

    public String getDimenstionCmHxwxl() {
        return dimenstionCmHxwxl;
    }

    public void setDimenstionCmHxwxl(String dimenstionCmHxwxl) {
        this.dimenstionCmHxwxl = dimenstionCmHxwxl;
    }

    public String getShippingNotes() {
        return shippingNotes;
    }

    public void setShippingNotes(String shippingNotes) {
        this.shippingNotes = shippingNotes;
    }

    @XmlTransient
    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }

    public BillingAddress getAddressId() {
        return addressId;
    }

    public void setAddressId(BillingAddress addressId) {
        this.addressId = addressId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shippingId != null ? shippingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shipping)) {
            return false;
        }
        Shipping other = (Shipping) object;
        if ((this.shippingId == null && other.shippingId != null) || (this.shippingId != null && !this.shippingId.equals(other.shippingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.Shipping[ shippingId=" + shippingId + " ]";
    }   

    

}
