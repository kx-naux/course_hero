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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
    @NamedQuery(name = "Users.findByDob", query = "SELECT u FROM Users u WHERE u.dob = :dob"),
    @NamedQuery(name = "Users.findByUsertype", query = "SELECT u FROM Users u WHERE u.usertype = :usertype"),
    @NamedQuery(name = "Users.findByValidity", query = "SELECT u FROM Users u WHERE u.validity = :validity"),
    @NamedQuery(name = "Users.findByDateJoined", query = "SELECT u FROM Users u WHERE u.dateJoined = :dateJoined"),
    @NamedQuery(name = "Users.findByGender", query = "SELECT u FROM Users u WHERE u.gender = :gender"),
    @NamedQuery(name = "Users.findByAccountId",query = "SELECT u FROM Users u WHERE u.accountId = :accountId")})
public class Users implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DISPLAY_NAME")
    private String displayName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "USERTYPE")
    private String usertype;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_JOINED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateJoined;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "GENDER")
    private String gender;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userId")
    private List<Wishlist> wishlistList;
    @Lob
    @Column(name = "IMG_DATA")
    private Serializable imgData;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "users")
    private List<PreferredCourse> preferredCourseList;


    
    @JoinTable(name = "PREFERRED_COURSE", joinColumns = {
        @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "COURSECAT_ID", referencedColumnName = "COURSECAT_ID")})
    @ManyToMany
    private List<CourseCategory> courseCategoryList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userId")
    private List<CourseSubscriptions> courseSubscriptionsList;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userId")
    private Bankcardinfo bankcardinfo;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userId")
    private List<CartItems> cartItemsList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userId")
    private List<Transactions> transactionsList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userId")
    private List<CourseCertificates> courseCertificatesList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userId")
    private List<Messages> messagesList;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userId")
    private TngAccounts tngAccounts;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "users")
    private List<UserChatrooms> userChatroomsList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userId")
    private List<Ratings> ratingsList;
    @OneToMany(mappedBy = "userId", orphanRemoval = true)
    private List<RememberMeToken> rememberMeTokenList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "VALIDITY")
    @Temporal(TemporalType.DATE)
    private Date validity;
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID")
    @OneToOne(optional = false, orphanRemoval = true)
    private Accounts accountId;
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID")
    @OneToOne(optional = false, orphanRemoval = true)
    private BillingAddress addressId;

    public Users() {
    }

    public Users(String userId) {
        this.userId = userId;
    }

    public Users(String userId, Date dob, String usertype, Date dateJoined, String gender) {
        this.userId = userId;
        this.dob = dob;
        this.usertype = usertype;
        this.dateJoined = dateJoined;
        this.gender = gender;
    }
    
    public Users(String displayName ,Date dob, String usertype, Date dateJoined, String gender, Accounts account, BillingAddress billAddress) {
        this.displayName = displayName;
        this.dob = dob;
        this.usertype = usertype;
        this.dateJoined = dateJoined;
        this.gender = gender;
        this.accountId = account;
        this.addressId = billAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public void setUserId(long count){
        this.userId = String.format("U%08d", count);
    }


    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }


    public Accounts getAccountId() {
        return accountId;
    }

    public void setAccountId(Accounts accountId) {
        this.accountId = accountId;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.Users[ userId=" + userId + " ]";
    }


    @XmlTransient
    public List<RememberMeToken> getRememberMeTokenList() {
        return rememberMeTokenList;
    }

    public void setRememberMeTokenList(List<RememberMeToken> rememberMeTokenList) {
        this.rememberMeTokenList = rememberMeTokenList;
    }


    @XmlTransient
    public List<CourseCategory> getCourseCategoryList() {
        return courseCategoryList;
    }

    public void setCourseCategoryList(List<CourseCategory> courseCategoryList) {
        this.courseCategoryList = courseCategoryList;
    }

    @XmlTransient
    public List<CourseSubscriptions> getCourseSubscriptionsList() {
        return courseSubscriptionsList;
    }

    public void setCourseSubscriptionsList(List<CourseSubscriptions> courseSubscriptionsList) {
        this.courseSubscriptionsList = courseSubscriptionsList;
    }

    public Bankcardinfo getBankcardinfo() {
        return bankcardinfo;
    }

    public void setBankcardinfo(Bankcardinfo bankcardinfo) {
        this.bankcardinfo = bankcardinfo;
    }

    @XmlTransient
    public List<CartItems> getCartItemsList() {
        return cartItemsList;
    }

    public void setCartItemsList(List<CartItems> cartItemsList) {
        this.cartItemsList = cartItemsList;
    }

    @XmlTransient
    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }

    @XmlTransient
    public List<CourseCertificates> getCourseCertificatesList() {
        return courseCertificatesList;
    }

    public void setCourseCertificatesList(List<CourseCertificates> courseCertificatesList) {
        this.courseCertificatesList = courseCertificatesList;
    }

    @XmlTransient
    public List<Messages> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(List<Messages> messagesList) {
        this.messagesList = messagesList;
    }

    public TngAccounts getTngAccounts() {
        return tngAccounts;
    }

    public void setTngAccounts(TngAccounts tngAccounts) {
        this.tngAccounts = tngAccounts;
    }

    @XmlTransient
    public List<UserChatrooms> getUserChatroomsList() {
        return userChatroomsList;
    }

    public void setUserChatroomsList(List<UserChatrooms> userChatroomsList) {
        this.userChatroomsList = userChatroomsList;
    }

    @XmlTransient
    public List<Ratings> getRatingsList() {
        return ratingsList;
    }

    public void setRatingsList(List<Ratings> ratingsList) {
        this.ratingsList = ratingsList;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }





   


    public Serializable getImgData() {
        return imgData;
    }

    public void setImgData(Serializable imgData) {
        this.imgData = imgData;
    }

    @XmlTransient
    public List<PreferredCourse> getPreferredCourseList() {
        return preferredCourseList;
    }

    public void setPreferredCourseList(List<PreferredCourse> preferredCourseList) {
        this.preferredCourseList = preferredCourseList;
    }

   


    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

   

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @XmlTransient
    public List<Wishlist> getWishlistList() {
        return wishlistList;
    }

    public void setWishlistList(List<Wishlist> wishlistList) {
        this.wishlistList = wishlistList;
    }

}
