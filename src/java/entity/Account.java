/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Woo Yu Beng
 */

public class Account {
    private String accountID;
    private String email;
    private String saltedPassword;
    private String salt;

    // Constructor - empty
    public Account() {
    }

    // Constructor - fully parameterized
    public Account(String accountID, String email, String saltedPassword, String salt) {
        this.accountID = accountID;
        this.email = email;
        this.saltedPassword = saltedPassword;
        this.salt = salt;
    }

    // Getters
    public String getAccountID() {
        return accountID;
    }

    public String getEmail() {
        return email;
    }

    public String getSaltedPassword() {
        return saltedPassword;
    }

    public String getSalt() {
        return salt;
    }

    // Setters
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSaltedPassword(String saltedPassword) {
        this.saltedPassword = saltedPassword;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}