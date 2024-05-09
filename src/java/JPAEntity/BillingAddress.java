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
import javax.persistence.OneToOne;
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
@Table(name = "BILLING_ADDRESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BillingAddress.findAll", query = "SELECT b FROM BillingAddress b"),
    @NamedQuery(name = "BillingAddress.findByAddressId", query = "SELECT b FROM BillingAddress b WHERE b.addressId = :addressId"),
    @NamedQuery(name = "BillingAddress.findByLine1", query = "SELECT b FROM BillingAddress b WHERE b.line1 = :line1"),
    @NamedQuery(name = "BillingAddress.findByLine2", query = "SELECT b FROM BillingAddress b WHERE b.line2 = :line2"),
    @NamedQuery(name = "BillingAddress.findByCity", query = "SELECT b FROM BillingAddress b WHERE b.city = :city"),
    @NamedQuery(name = "BillingAddress.findByStateResides", query = "SELECT b FROM BillingAddress b WHERE b.stateResides = :stateResides"),
    @NamedQuery(name = "BillingAddress.findByPostalcode", query = "SELECT b FROM BillingAddress b WHERE b.postalcode = :postalcode"),
    @NamedQuery(name = "BillingAddress.findByCountry", query = "SELECT b FROM BillingAddress b WHERE b.country = :country")})
public class BillingAddress implements Serializable {

    @Size(max = 50)
    @Column(name = "LINE_1")
    private String line1;
    @Size(max = 50)
    @Column(name = "LINE_2")
    private String line2;
    @Size(max = 20)
    @Column(name = "CITY")
    private String city;
    @Size(max = 20)
    @Column(name = "STATE_RESIDES")
    private String stateResides;
    @Size(max = 9)
    @Column(name = "POSTALCODE")
    private String postalcode;
    @Size(max = 40)
    @Column(name = "COUNTRY")
    private String country;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "addressId")
    private List<Shipping> shippingList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "ADDRESS_ID")
    private String addressId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "addressId")
    private Users users;

    public BillingAddress() {
    }

    public BillingAddress(String addressId) {
        this.addressId = addressId;
    }

    public BillingAddress(String addressId, String line1, String city, String stateResides, String postalcode, String country) {
        this.addressId = addressId;
        this.line1 = line1;
        this.line2 = "";
        this.city = city;
        this.stateResides = stateResides;
        this.postalcode = postalcode;
        this.country = country;
    }
    
    public BillingAddress(String line1, String city, String stateResides, String postalcode, String country) {
        this.line1 = line1;
        this.city = city;
        this.stateResides = stateResides;
        this.postalcode = postalcode;
        this.country = country;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
    
    public void setAddressId(long count){
        this.addressId = String.format("BA%07d", count);
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }


    public String getStateResides() {
        return stateResides;
    }

    public void setStateResides(String stateResides) {
        this.stateResides = stateResides;
    }


    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addressId != null ? addressId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BillingAddress)) {
            return false;
        }
        BillingAddress other = (BillingAddress) object;
        if ((this.addressId == null && other.addressId != null) || (this.addressId != null && !this.addressId.equals(other.addressId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.BillingAddress[ addressId=" + addressId + " ]";
    }

    

    @XmlTransient
    public List<Shipping> getShippingList() {
        return shippingList;
    }

    public void setShippingList(List<Shipping> shippingList) {
        this.shippingList = shippingList;
    }

   

 
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
}
