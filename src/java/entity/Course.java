/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Woo Yu Beng
 */

public class Course {
    private String courseID;
    private String productID;
    private String toolsUsed;
    private String courseCatID;
    private String learningObj;

    // Constructor - empty
    public Course() {
    }

    // Constructor - fully parameterized
    public Course(String courseID, Product product, String toolsUsed, CourseCategory courseCategory, String learningObj) {
        this.courseID = courseID;
        this.productID = product.getProductID();
        this.toolsUsed = toolsUsed;
        this.courseCatID = courseCategory.getCourseCatID();
        this.learningObj = learningObj;
    }

    // Getters
    public String getCourseID() {
        return courseID;
    }

    public String getProductID() {
        return productID;
    }

    public String getToolsUsed() {
        return toolsUsed;
    }

    public String getCourseCatID() {
        return courseCatID;
    }

    public String getLearningObj() {
        return learningObj;
    }

    // Setters
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setToolsUsed(String toolsUsed) {
        this.toolsUsed = toolsUsed;
    }

    public void setCourseCatID(String courseCatID) {
        this.courseCatID = courseCatID;
    }

    public void setLearningObj(String learningObj) {
        this.learningObj = learningObj;
    }
}