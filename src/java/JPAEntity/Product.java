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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "PRODUCT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByProductId", query = "SELECT p FROM Product p WHERE p.productId = :productId"),
    @NamedQuery(name = "Product.findByProdName", query = "SELECT p FROM Product p WHERE p.prodName = :prodName"),
    @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price"),
    @NamedQuery(name = "Product.findByRateWeightage", query = "SELECT p FROM Product p WHERE p.rateWeightage = :rateWeightage"),
    @NamedQuery(name = "Product.findByAvgRating", query = "SELECT p FROM Product p WHERE p.avgRating = :avgRating"),
    @NamedQuery(name = "Product.findByDiscount", query = "SELECT p FROM Product p WHERE p.discount = :discount"),
    @NamedQuery(name = "Product.findByImagePath", query = "SELECT p FROM Product p WHERE p.imagePath = :imagePath"),
    @NamedQuery(name = "Product.findByStatus", query = "SELECT p FROM Product p WHERE p.status = :status"),
    @NamedQuery(name = "Product.findByProdNameFilter", query = "SELECT p FROM Product p WHERE LOWER(p.prodName) LIKE LOWER(CONCAT('%',:prodName,'%'))")
})

public class Product implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PROD_NAME")
    private String prodName;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 1000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull()
    @Column(name = "PRICE")
    private double price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RATE_WEIGHTAGE")
    private int rateWeightage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AVG_RATING")
    private double avgRating;
    @Basic(optional = false)
    @NotNull()
    @Column(name = "DISCOUNT")
    private double discount;
    @Size(max = 100)
    @Column(name = "IMAGE_PATH")
    private String imagePath;
    @Size(max = 30)
    @Column(name = "STATUS")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private List<Wishlist> wishlistList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private List<CartItems> cartItemsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Orders> ordersList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private List<Merchandise> merchandiseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private List<Ratings> ratingsList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "PRODUCT_ID")
    private String productId;
    @JoinColumn(name = "PRODCAT_ID", referencedColumnName = "PRODCAT_ID")
    @ManyToOne(optional = false)
    private ProductCategory prodcatId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private List<Courses> coursesList;

    public Product() {
    }

    public Product(String productId) {
        this.productId = productId;
    }

    public Product(String productId, String prodName, double price, int rateWeightage, double avgRating, double discount) {
        this.productId = productId;
        this.prodName = prodName;
        this.price = price;
        this.rateWeightage = rateWeightage;
        this.avgRating = avgRating;
        this.discount = discount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductId(long count) {
        this.productId = String.format("PR%07d", count);
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public int getRateWeightage() {
        return rateWeightage;
    }

    public void setRateWeightage(int rateWeightage) {
        this.rateWeightage = rateWeightage;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public ProductCategory getProdcatId() {
        return prodcatId;
    }

    public void setProdcatId(ProductCategory prodcatId) {
        this.prodcatId = prodcatId;
    }

    @XmlTransient
    public List<Courses> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Courses> coursesList) {
        this.coursesList = coursesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.Product[ productId=" + productId + " ]";
    }

    @XmlTransient
    public List<CartItems> getCartItemsList() {
        return cartItemsList;
    }

    public void setCartItemsList(List<CartItems> cartItemsList) {
        this.cartItemsList = cartItemsList;
    }

    @XmlTransient
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    @XmlTransient
    public List<Merchandise> getMerchandiseList() {
        return merchandiseList;
    }

    public void setMerchandiseList(List<Merchandise> merchandiseList) {
        this.merchandiseList = merchandiseList;
    }

    @XmlTransient
    public List<Ratings> getRatingsList() {
        return ratingsList;
    }

    public void setRatingsList(List<Ratings> ratingsList) {
        this.ratingsList = ratingsList;
    }

    @XmlTransient
    public List<Wishlist> getWishlistList() {
        return wishlistList;
    }

    public void setWishlistList(List<Wishlist> wishlistList) {
        this.wishlistList = wishlistList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
