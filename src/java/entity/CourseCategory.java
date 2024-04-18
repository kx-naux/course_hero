/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Woo Yu Beng
 */

public class CourseCategory {
    private String courseCatID;
    private String categoryName;
    private String description;

    // Constructor - empty
    public CourseCategory() {
    }

    // Constructor - fully parameterized
    public CourseCategory(String courseCatID, String categoryName, String description) {
        this.courseCatID = courseCatID;
        this.categoryName = categoryName;
        this.description = description;
    }

    // Getters
    public String getCourseCatID() {
        return courseCatID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setCourseCatID(String courseCatID) {
        this.courseCatID = courseCatID;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
