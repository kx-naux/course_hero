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
@Table(name = "WISHLIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wishlist.findAll", query = "SELECT w FROM Wishlist w"),
    @NamedQuery(name = "Wishlist.findByWishlistId", query = "SELECT w FROM Wishlist w WHERE w.wishlistId = :wishlistId"),
    @NamedQuery(name = "Wishlist.findByDateAdded", query = "SELECT w FROM Wishlist w WHERE w.dateAdded = :dateAdded"),
    @NamedQuery(name = "Wishlist.findByUserIdProductId", query = "SELECT w FROM Wishlist w WHERE w.userId = :userId AND w.productId = :productId"),
    @NamedQuery(name = "Wishlist.countAllByUserId", query = "SELECT COUNT(w) FROM Wishlist w WHERE w.userId = :userId"),
    @NamedQuery(name = "Wishlist.findByUserId", query = "SELECT w FROM Wishlist w WHERE w.userId = :userId")
})
public class Wishlist implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_ADDED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "WISHLIST_ID")
    private String wishlistId;
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    @ManyToOne(optional = false)
    private Product productId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private Users userId;

    public Wishlist() {
    }

    public Wishlist(String wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Wishlist(String wishlistId, Date dateAdded) {
        this.wishlistId = wishlistId;
        this.dateAdded = dateAdded;
    }

    public String getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(String wishlistId) {
        this.wishlistId = wishlistId;
    }
    
    public void setWishlistId(long count) {
        this.wishlistId = String.format("WL%07d", count);
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wishlistId != null ? wishlistId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wishlist)) {
            return false;
        }
        Wishlist other = (Wishlist) object;
        if ((this.wishlistId == null && other.wishlistId != null) || (this.wishlistId != null && !this.wishlistId.equals(other.wishlistId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.Wishlist[ wishlistId=" + wishlistId + " ]";
    }


}
