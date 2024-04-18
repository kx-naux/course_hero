/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.time.LocalDateTime;

/**
 *
 * @author Woo Yu Beng
 */

public class Rating {
    private String ratingID;
    private String productID;
    private String userID;
    private int score;
    private String comment;
    private LocalDateTime timeRated;
    private int totalVote;

    // Constructor - empty
    public Rating() {
    }

    // Constructor - fully parameterized
    public Rating(String ratingID, Product product, User user, int score, String comment,
                  LocalDateTime timeRated, int totalVote) {
        this.ratingID = ratingID;
        this.productID = product.getProductID();
        this.userID = user.getUserID();
        this.score = score;
        this.comment = comment;
        this.timeRated = timeRated;
        this.totalVote = totalVote;
    }

    // Getters
    public String getRatingID() {
        return ratingID;
    }

    public String getProductID() {
        return productID;
    }

    public String getUserID() {
        return userID;
    }

    public int getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getTimeRated() {
        return timeRated;
    }

    public int getTotalVote() {
        return totalVote;
    }

    // Setters
    public void setRatingID(String ratingID) {
        this.ratingID = ratingID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTimeRated(LocalDateTime timeRated) {
        this.timeRated = timeRated;
    }

    public void setTotalVote(int totalVote) {
        this.totalVote = totalVote;
    }
}