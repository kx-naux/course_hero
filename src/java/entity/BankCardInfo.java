/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Woo Yu Beng
 */

public class BankCardInfo {
    private String cardInfoID;
    private String userID;
    private String cardType;
    private String cardHolderName;
    private String cardNo;
    private String expiryDate;
    private String cvv;

    // Constructor - empty
    public BankCardInfo() {
    }

    // Constructor - fully parameterized
    public BankCardInfo(String cardInfoID, User user, String cardType, String cardHolderName, String cardNo, String expiryDate, String cvv) {
        this.cardInfoID = cardInfoID;
        this.userID = user.getUserID();
        this.cardType = cardType;
        this.cardHolderName = cardHolderName;
        this.cardNo = cardNo;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    // Getters
    public String getCardInfoID() {
        return cardInfoID;
    }

    public String getUserID() {
        return userID;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    // Setters
    public void setCardInfoID(String cardInfoID) {
        this.cardInfoID = cardInfoID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}