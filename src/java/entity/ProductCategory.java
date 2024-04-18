/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Woo Yu Beng
 */

public class ProductCategory {
    private String prodCatID;
    private String categoryName;
    private String description;

    // Constructor - empty
    public ProductCategory() {
    }

    // Constructor - fully parameterized
    public ProductCategory(String prodCatID, String categoryName, String description) {
        this.prodCatID = prodCatID;
        this.categoryName = categoryName;
        this.description = description;
    }

    // Getters
    public String getProdCatID() {
        return prodCatID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setProdCatID(String prodCatID) {
        this.prodCatID = prodCatID;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}