/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.time.LocalDate;

/**
 *
 * @author Woo Yu Beng
 */

public class Shipping {
    private String shippingID;
    private String addressID;
    private LocalDate shippingDate;
    private LocalDate expectedDeliveryDate;
    private double shippingCost;
    private double weight;
    private String dimensionCM;
    private String shippingNotes;

    // Constructor - empty
    public Shipping() {
    }

    // Constructor - fully parameterized
    public Shipping(String shippingID, BillingAddress billingAddress, LocalDate shippingDate, LocalDate expectedDeliveryDate,
                    double shippingCost, double weight, String dimensionCM, String shippingNotes) {
        this.shippingID = shippingID;
        this.addressID = billingAddress.getAddressID();
        this.shippingDate = shippingDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.shippingCost = shippingCost;
        this.weight = weight;
        this.dimensionCM = dimensionCM;
        this.shippingNotes = shippingNotes;
    }

    // Getters
    public String getShippingID() {
        return shippingID;
    }

    public String getAddressID() {
        return addressID;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public double getWeight() {
        return weight;
    }

    public String getDimensionCM() {
        return dimensionCM;
    }

    public String getShippingNotes() {
        return shippingNotes;
    }

    // Setters
    public void setShippingID(String shippingID) {
        this.shippingID = shippingID;
    }

    public void setAddressID(String addressID) {
        this.addressID = addressID;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setDimensionCM(String dimensionCM) {
        this.dimensionCM = dimensionCM;
    }

    public void setShippingNotes(String shippingNotes) {
        this.shippingNotes = shippingNotes;
    }
}