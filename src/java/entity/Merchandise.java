/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Woo Yu Beng
 */

public class Merchandise {
    private String merchID;
    private String productID;
    private double dimensionL;
    private double dimensionW;
    private double dimensionH;
    private double weight;
    private int stockBalance;

    // Constructor - empty
    public Merchandise() {
    }

    // Constructor - fully parameterized
    public Merchandise(String merchID, Product product, double dimensionL, double dimensionW, double dimensionH, double weight, int stockBalance) {
        this.merchID = merchID;
        this.productID = product.getProductID();
        this.dimensionL = dimensionL;
        this.dimensionW = dimensionW;
        this.dimensionH = dimensionH;
        this.weight = weight;
        this.stockBalance = stockBalance;
    }

    // Getters
    public String getMerchID() {
        return merchID;
    }

    public String getProductID() {
        return productID;
    }

    public double getDimensionL() {
        return dimensionL;
    }

    public double getDimensionW() {
        return dimensionW;
    }

    public double getDimensionH() {
        return dimensionH;
    }

    public double getWeight() {
        return weight;
    }

    public int getStockBalance() {
        return stockBalance;
    }

    // Setters
    public void setMerchID(String merchID) {
        this.merchID = merchID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setDimensionL(double dimensionL) {
        this.dimensionL = dimensionL;
    }

    public void setDimensionW(double dimensionW) {
        this.dimensionW = dimensionW;
    }

    public void setDimensionH(double dimensionH) {
        this.dimensionH = dimensionH;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setStockBalance(int stockBalance) {
        this.stockBalance = stockBalance;
    }
}
