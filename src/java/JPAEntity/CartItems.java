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
@Table(name = "CART_ITEMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CartItems.findAll", query = "SELECT c FROM CartItems c"),
    @NamedQuery(name = "CartItems.findByCartitemId", query = "SELECT c FROM CartItems c WHERE c.cartitemId = :cartitemId"),
    @NamedQuery(name = "CartItems.findByQuantity", query = "SELECT c FROM CartItems c WHERE c.quantity = :quantity"),
    @NamedQuery(name = "CartItems.findByTimeAdded", query = "SELECT c FROM CartItems c WHERE c.timeAdded = :timeAdded")})
public class CartItems implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;
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
    @Column(name = "CARTITEM_ID")
    private String cartitemId;
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    @ManyToOne(optional = false)
    private Product productId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private Users userId;

    public CartItems() {
    }

    public CartItems(String cartitemId) {
        this.cartitemId = cartitemId;
    }

    public CartItems(String cartitemId, int quantity, Date timeAdded) {
        this.cartitemId = cartitemId;
        this.quantity = quantity;
        this.timeAdded = timeAdded;
    }

    public String getCartitemId() {
        return cartitemId;
    }

    public void setCartitemId(String cartitemId) {
        this.cartitemId = cartitemId;
    }


    public Date getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Date timeAdded) {
        this.timeAdded = timeAdded;
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
        hash += (cartitemId != null ? cartitemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CartItems)) {
            return false;
        }
        CartItems other = (CartItems) object;
        if ((this.cartitemId == null && other.cartitemId != null) || (this.cartitemId != null && !this.cartitemId.equals(other.cartitemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.CartItems[ cartitemId=" + cartitemId + " ]";
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
