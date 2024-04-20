/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Woo Yu Beng
 */

public class PreferredCourse {
    private String userID;
    private String courseCatID;

    // Constructor - empty
    public PreferredCourse() {
    }

    // Constructor - fully parameterized
    public PreferredCourse(User user, CourseCategory courseCategory) {
        this.userID = user.getUserID();
        this.courseCatID = courseCategory.getCourseCatID();
    }

    // Getters
    public String getUserID() {
        return userID;
    }

    public String getCourseCatID() {
        return courseCatID;
    }

    // Setters
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setCourseCatID(String courseCatID) {
        this.courseCatID = courseCatID;
    }
}
