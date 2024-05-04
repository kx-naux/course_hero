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
@Table(name = "PRODUCT_CATEGORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductCategory.findAll", query = "SELECT p FROM ProductCategory p"),
    @NamedQuery(name = "ProductCategory.findByProdcatId", query = "SELECT p FROM ProductCategory p WHERE p.prodcatId = :prodcatId"),
    @NamedQuery(name = "ProductCategory.findByCategoryName", query = "SELECT p FROM ProductCategory p WHERE p.categoryName = :categoryName"),
    @NamedQuery(name = "ProductCategory.findByDescription", query = "SELECT p FROM ProductCategory p WHERE p.description = :description")})
public class ProductCategory implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CATEGORY_NAME")
    private String categoryName;
    @Size(max = 100)
    @Column(name = "DESCRIPTION")
    private String description;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "PRODCAT_ID")
    private String prodcatId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prodcatId")
    private List<Product> productList;

    public ProductCategory() {
    }

    public ProductCategory(String prodcatId) {
        this.prodcatId = prodcatId;
    }

    public ProductCategory(String prodcatId, String categoryName) {
        this.prodcatId = prodcatId;
        this.categoryName = categoryName;
    }

    public String getProdcatId() {
        return prodcatId;
    }

    public void setProdcatId(String prodcatId) {
        this.prodcatId = prodcatId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @XmlTransient
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prodcatId != null ? prodcatId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductCategory)) {
            return false;
        }
        ProductCategory other = (ProductCategory) object;
        if ((this.prodcatId == null && other.prodcatId != null) || (this.prodcatId != null && !this.prodcatId.equals(other.prodcatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.ProductCategory[ prodcatId=" + prodcatId + " ]";
    }

    
}
