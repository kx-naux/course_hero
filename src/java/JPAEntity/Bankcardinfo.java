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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "BANKCARDINFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bankcardinfo.findAll", query = "SELECT b FROM Bankcardinfo b"),
    @NamedQuery(name = "Bankcardinfo.findByCardinfoid", query = "SELECT b FROM Bankcardinfo b WHERE b.cardinfoid = :cardinfoid"),
    @NamedQuery(name = "Bankcardinfo.findByCardtype", query = "SELECT b FROM Bankcardinfo b WHERE b.cardtype = :cardtype"),
    @NamedQuery(name = "Bankcardinfo.findByCardHolderName", query = "SELECT b FROM Bankcardinfo b WHERE b.cardHolderName = :cardHolderName"),
    @NamedQuery(name = "Bankcardinfo.findByCardNo", query = "SELECT b FROM Bankcardinfo b WHERE b.cardNo = :cardNo"),
    @NamedQuery(name = "Bankcardinfo.findByExpiryDate", query = "SELECT b FROM Bankcardinfo b WHERE b.expiryDate = :expiryDate"),
    @NamedQuery(name = "Bankcardinfo.findByCvv", query = "SELECT b FROM Bankcardinfo b WHERE b.cvv = :cvv")})
public class Bankcardinfo implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "CARDTYPE")
    private String cardtype;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CARD_HOLDER_NAME")
    private String cardHolderName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "CARD_NO")
    private String cardNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "EXPIRY_DATE")
    private String expiryDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CVV")
    private String cvv;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "CARDINFOID")
    private String cardinfoid;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @OneToOne(optional = false)
    private Users userId;

    public Bankcardinfo() {
    }

    public Bankcardinfo(String cardinfoid) {
        this.cardinfoid = cardinfoid;
    }

    public Bankcardinfo(String cardinfoid, String cardtype, String cardHolderName, String cardNo, String expiryDate, String cvv) {
        this.cardinfoid = cardinfoid;
        this.cardtype = cardtype;
        this.cardHolderName = cardHolderName;
        this.cardNo = cardNo;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public String getCardinfoid() {
        return cardinfoid;
    }

    public void setCardinfoid(String cardinfoid) {
        this.cardinfoid = cardinfoid;
    }


    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
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
        hash += (cardinfoid != null ? cardinfoid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bankcardinfo)) {
            return false;
        }
        Bankcardinfo other = (Bankcardinfo) object;
        if ((this.cardinfoid == null && other.cardinfoid != null) || (this.cardinfoid != null && !this.cardinfoid.equals(other.cardinfoid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.Bankcardinfo[ cardinfoid=" + cardinfoid + " ]";
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    

    

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    
}
