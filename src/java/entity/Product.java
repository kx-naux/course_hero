/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Woo Yu Beng
 */

public class Product {
    private String productID;
    private String prodCatID;
    private String prodName;
    private double price;
    private int rateWeightage;
    private double avgRating;
    private double discount;
    private String imagePath;

    // Constructor - empty
    public Product() {
    }

    // Constructor - fully parameterized
    public Product(String productID, ProductCategory productCategory, String prodName, double price, int rateWeightage, double avgRating, double discount, String imagePath) {
        this.productID = productID;
        this.prodCatID = productCategory.getProdCatID();
        this.prodName = prodName;
        this.price = price;
        this.rateWeightage = rateWeightage;
        this.avgRating = avgRating;
        this.discount = discount;
        this.imagePath = imagePath;
    }

    // Getters
    public String getProductID() {
        return productID;
    }

    public String getProdCatID() {
        return prodCatID;
    }

    public String getProdName() {
        return prodName;
    }

    public double getPrice() {
        return price;
    }

    public int getRateWeightage() {
        return rateWeightage;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public double getDiscount() {
        return discount;
    }

    public String getImagePath() {
        return imagePath;
    }

    // Setters
    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProdCatID(String prodCatID) {
        this.prodCatID = prodCatID;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRateWeightage(int rateWeightage) {
        this.rateWeightage = rateWeightage;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // Other methods
    public void updateAvgRating(int newRating){
        double oldTotalRating = this.rateWeightage * this.avgRating;
        this.rateWeightage += 1;
        this.avgRating = (oldTotalRating + newRating)/this.rateWeightage;
    }
}