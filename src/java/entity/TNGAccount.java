/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Woo Yu Beng
 */

public class TNGAccount {
    private String tngID;
    private String userID;
    private String phoneNo;

    // Constructor - empty
    public TNGAccount() {
    }

    // Constructor - fully parameterized
    public TNGAccount(String tngID, User user, String phoneNo) {
        this.tngID = tngID;
        this.userID = user.getUserID();
        this.phoneNo = phoneNo;
    }

    // Getters
    public String getTngID() {
        return tngID;
    }

    public String getUserID() {
        return userID;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    // Setters
    public void setTngID(String tngID) {
        this.tngID = tngID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
