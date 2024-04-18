/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Woo Yu Beng
 */

public class MerchCategory {
    private String merchCatID;
    private String categoryName;
    private String description;

    // Constructor - empty
    public MerchCategory() {
    }

    // Constructor - fully parameterized
    public MerchCategory(String merchCatID, String categoryName, String description) {
        this.merchCatID = merchCatID;
        this.categoryName = categoryName;
        this.description = description;
    }

    // Getters
    public String getMerchCatID() {
        return merchCatID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setMerchCatID(String merchCatID) {
        this.merchCatID = merchCatID;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}