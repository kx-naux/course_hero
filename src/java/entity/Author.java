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

public class Author {
    private String authorID;
    private String authorName;
    private String nationality;
    private String institution;
    private String awardsHonors;
    private String biography;
    private LocalDate dob;

    // Constructor - empty
    public Author() {
    }

    // Constructor - fully parameterized
    public Author(String authorID, String authorName, String nationality, String institution,
                  String awardsHonors, String biography, LocalDate dob) {
        this.authorID = authorID;
        this.authorName = authorName;
        this.nationality = nationality;
        this.institution = institution;
        this.awardsHonors = awardsHonors;
        this.biography = biography;
        this.dob = dob;
    }

    // Getters
    public String getAuthorID() {
        return authorID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getNationality() {
        return nationality;
    }

    public String getInstitution() {
        return institution;
    }

    public String getAwardsHonors() {
        return awardsHonors;
    }

    public String getBiography() {
        return biography;
    }

    public LocalDate getDob() {
        return dob;
    }

    // Setters
    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setAwardsHonors(String awardsHonors) {
        this.awardsHonors = awardsHonors;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}