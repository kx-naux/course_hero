/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Woo Yu Beng
 */

public class ShippingMethod {
    private String shippingMethodID;
    private String shippingType;
    private String deliverySpeed;
    private String description;
    private String coverageArea;
    private double shippingRates;
    private String returnPolicy;

    // Constructor - empty
    public ShippingMethod() {
    }

    // Constructor - fully parameterized
    public ShippingMethod(String shippingMethodID, String shippingType, String deliverySpeed, String description,
                          String coverageArea, double shippingRates, String returnPolicy) {
        this.shippingMethodID = shippingMethodID;
        this.shippingType = shippingType;
        this.deliverySpeed = deliverySpeed;
        this.description = description;
        this.coverageArea = coverageArea;
        this.shippingRates = shippingRates;
        this.returnPolicy = returnPolicy;
    }

    // Getters
    public String getShippingMethodID() {
        return shippingMethodID;
    }

    public String getShippingType() {
        return shippingType;
    }

    public String getDeliverySpeed() {
        return deliverySpeed;
    }

    public String getDescription() {
        return description;
    }

    public String getCoverageArea() {
        return coverageArea;
    }

    public double getShippingRates() {
        return shippingRates;
    }

    public String getReturnPolicy() {
        return returnPolicy;
    }

    // Setters
    public void setShippingMethodID(String shippingMethodID) {
        this.shippingMethodID = shippingMethodID;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public void setDeliverySpeed(String deliverySpeed) {
        this.deliverySpeed = deliverySpeed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCoverageArea(String coverageArea) {
        this.coverageArea = coverageArea;
    }

    public void setShippingRates(double shippingRates) {
        this.shippingRates = shippingRates;
    }

    public void setReturnPolicy(String returnPolicy) {
        this.returnPolicy = returnPolicy;
    }
}