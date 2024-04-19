/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Woo Yu Beng
 */

public class BillingAddress {
    private String addressID;
    private String line1;
    private String line2;
    private String city;
    private String stateResides;
    private String postalCode;
    private String country;

    // Constructor - empty
    public BillingAddress() {
    }

    // Constructor - fully parameterized
    public BillingAddress(String addressID, String line1, String line2, String city, String stateResides, String postalCode, String country) {
        this.addressID = addressID;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.stateResides = stateResides;
        this.postalCode = postalCode;
        this.country = country;
    }

    // Getters
    public String getAddressID() {
        return addressID;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getCity() {
        return city;
    }

    public String getStateResides() {
        return stateResides;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    // Setters
    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStateResides(String stateResides) {
        this.stateResides = stateResides;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

