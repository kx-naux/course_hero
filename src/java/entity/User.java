/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Woo Yu Beng
 */

public class User {
    private String userID;
    private String accountID;
    private LocalDate dob;
    private String addressID;
    private String userType;
    private LocalDate validity;
    private LocalDateTime dateJoined;
    private String gender;

    // Constructor - empty
    public User() {
    }

    // Constructor - fully parameterized
    public User(String userID, Account account, LocalDate dob, BillingAddress billingAddress, String userType,
                 LocalDate validity, LocalDateTime dateJoined, String gender) {
        this.userID = userID;
        this.accountID = account.getAccountID();
        this.dob = dob;
        this.addressID = billingAddress.getAddressID();
        this.userType = userType;
        this.validity = validity;
        this.dateJoined = dateJoined;
        this.gender = gender;
    }

    // Getters
    public String getUserID() {
        return userID;
    }

    public String getAccountID() {
        return accountID;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getAddressID() {
        return addressID;
    }

    public String getUserType() {
        return userType;
    }

    public LocalDate getValidity() {
        return validity;
    }

    public LocalDateTime getDateJoined() {
        return dateJoined;
    }

    public String getGender() {
        return gender;
    }

    // Setters
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setValidity(LocalDate validity) {
        this.validity = validity;
    }

    public void setDateJoined(LocalDateTime dateJoined) {
        this.dateJoined = dateJoined;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
