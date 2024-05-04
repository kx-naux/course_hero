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
@Table(name = "SHIPPING_METHOD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShippingMethod.findAll", query = "SELECT s FROM ShippingMethod s"),
    @NamedQuery(name = "ShippingMethod.findByShippingMethodId", query = "SELECT s FROM ShippingMethod s WHERE s.shippingMethodId = :shippingMethodId"),
    @NamedQuery(name = "ShippingMethod.findByShippingType", query = "SELECT s FROM ShippingMethod s WHERE s.shippingType = :shippingType"),
    @NamedQuery(name = "ShippingMethod.findByDeliverySpeed", query = "SELECT s FROM ShippingMethod s WHERE s.deliverySpeed = :deliverySpeed"),
    @NamedQuery(name = "ShippingMethod.findByDescription", query = "SELECT s FROM ShippingMethod s WHERE s.description = :description"),
    @NamedQuery(name = "ShippingMethod.findByCoverageArea", query = "SELECT s FROM ShippingMethod s WHERE s.coverageArea = :coverageArea"),
    @NamedQuery(name = "ShippingMethod.findByShippingRates", query = "SELECT s FROM ShippingMethod s WHERE s.shippingRates = :shippingRates"),
    @NamedQuery(name = "ShippingMethod.findByReturnPolicy", query = "SELECT s FROM ShippingMethod s WHERE s.returnPolicy = :returnPolicy")})
public class ShippingMethod implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 21)
    @Column(name = "SHIPPING_TYPE")
    private String shippingType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 21)
    @Column(name = "DELIVERY_SPEED")
    private String deliverySpeed;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "COVERAGE_AREA")
    private String coverageArea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SHIPPING_RATES")
    private double shippingRates;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "RETURN_POLICY")
    private String returnPolicy;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "SHIPPING_METHOD_ID")
    private String shippingMethodId;

    public ShippingMethod() {
    }

    public ShippingMethod(String shippingMethodId) {
        this.shippingMethodId = shippingMethodId;
    }

    public ShippingMethod(String shippingMethodId, String shippingType, String deliverySpeed, String description, String coverageArea, double shippingRates, String returnPolicy) {
        this.shippingMethodId = shippingMethodId;
        this.shippingType = shippingType;
        this.deliverySpeed = deliverySpeed;
        this.description = description;
        this.coverageArea = coverageArea;
        this.shippingRates = shippingRates;
        this.returnPolicy = returnPolicy;
    }

    public String getShippingMethodId() {
        return shippingMethodId;
    }

    public void setShippingMethodId(String shippingMethodId) {
        this.shippingMethodId = shippingMethodId;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public String getDeliverySpeed() {
        return deliverySpeed;
    }

    public void setDeliverySpeed(String deliverySpeed) {
        this.deliverySpeed = deliverySpeed;
    }


    public String getCoverageArea() {
        return coverageArea;
    }

    public void setCoverageArea(String coverageArea) {
        this.coverageArea = coverageArea;
    }

    public double getShippingRates() {
        return shippingRates;
    }

    public void setShippingRates(double shippingRates) {
        this.shippingRates = shippingRates;
    }

    public String getReturnPolicy() {
        return returnPolicy;
    }

    public void setReturnPolicy(String returnPolicy) {
        this.returnPolicy = returnPolicy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shippingMethodId != null ? shippingMethodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShippingMethod)) {
            return false;
        }
        ShippingMethod other = (ShippingMethod) object;
        if ((this.shippingMethodId == null && other.shippingMethodId != null) || (this.shippingMethodId != null && !this.shippingMethodId.equals(other.shippingMethodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.ShippingMethod[ shippingMethodId=" + shippingMethodId + " ]";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }  

}
