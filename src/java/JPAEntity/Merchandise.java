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
@Table(name = "MERCHANDISE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Merchandise.findAll", query = "SELECT m FROM Merchandise m"),
    @NamedQuery(name = "Merchandise.findByMerchId", query = "SELECT m FROM Merchandise m WHERE m.merchId = :merchId"),
    @NamedQuery(name = "Merchandise.findByDimensionHCm", query = "SELECT m FROM Merchandise m WHERE m.dimensionHCm = :dimensionHCm"),
    @NamedQuery(name = "Merchandise.findByDimensionWCm", query = "SELECT m FROM Merchandise m WHERE m.dimensionWCm = :dimensionWCm"),
    @NamedQuery(name = "Merchandise.findByDimensionLCm", query = "SELECT m FROM Merchandise m WHERE m.dimensionLCm = :dimensionLCm"),
    @NamedQuery(name = "Merchandise.findByWeightKg", query = "SELECT m FROM Merchandise m WHERE m.weightKg = :weightKg"),
    @NamedQuery(name = "Merchandise.findByStockBalance", query = "SELECT m FROM Merchandise m WHERE m.stockBalance = :stockBalance")})
public class Merchandise implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "DIMENSION_H_CM")
    private double dimensionHCm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIMENSION_W_CM")
    private double dimensionWCm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIMENSION_L_CM")
    private double dimensionLCm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WEIGHT_KG")
    private double weightKg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STOCK_BALANCE")
    private int stockBalance;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "MERCH_ID")
    private String merchId;
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    @ManyToOne(optional = false)
    private Product productId;

    public Merchandise() {
    }

    public Merchandise(String merchId) {
        this.merchId = merchId;
    }

    public Merchandise(String merchId, double dimensionHCm, double dimensionWCm, double dimensionLCm, double weightKg, int stockBalance) {
        this.merchId = merchId;
        this.dimensionHCm = dimensionHCm;
        this.dimensionWCm = dimensionWCm;
        this.dimensionLCm = dimensionLCm;
        this.weightKg = weightKg;
        this.stockBalance = stockBalance;
    }

    public String getMerchId() {
        return merchId;
    }

    public void setMerchId(String merchId) {
        this.merchId = merchId;
    }

    public double getDimensionHCm() {
        return dimensionHCm;
    }

    public void setDimensionHCm(double dimensionHCm) {
        this.dimensionHCm = dimensionHCm;
    }

    public double getDimensionWCm() {
        return dimensionWCm;
    }

    public void setDimensionWCm(double dimensionWCm) {
        this.dimensionWCm = dimensionWCm;
    }

    public double getDimensionLCm() {
        return dimensionLCm;
    }

    public void setDimensionLCm(double dimensionLCm) {
        this.dimensionLCm = dimensionLCm;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
    }

    public int getStockBalance() {
        return stockBalance;
    }

    public void setStockBalance(int stockBalance) {
        this.stockBalance = stockBalance;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (merchId != null ? merchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Merchandise)) {
            return false;
        }
        Merchandise other = (Merchandise) object;
        if ((this.merchId == null && other.merchId != null) || (this.merchId != null && !this.merchId.equals(other.merchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.Merchandise[ merchId=" + merchId + " ]";
    }

    

    
    
}
