/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.Rating;
import java.sql.*;
import java.time.*;

/**
 *
 * @author Woo Yu Beng
 */

public class RatingDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "RATINGS";
    private Connection conn;
    private PreparedStatement stmt;
    private ProductDA prodDA;
    private UserDA userDA;
    
    public RatingDA() throws SQLException{
        prodDA = new ProductDA();
        userDA = new UserDA();
        createConnection();
    }
      
    private void createConnection() throws SQLException{
        conn = DriverManager.getConnection(host, user, password);
    }
    
    public void shutDownConnection() throws SQLException{
        if (conn != null)
            conn.close();
    }
    
    //Create Retrieve Update Delete
    public void createRecord(Rating rating) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, rating.getRatingID());
        stmt.setString(2, rating.getProductID());
        stmt.setString(3, rating.getUserID());
        stmt.setString(4, String.valueOf(rating.getScore()));
        stmt.setString(5, rating.getComment());
        stmt.setString(6, rating.getTimeRated().toString());
        stmt.setString(7, String.valueOf(rating.getTotalVote()));
        
        stmt.executeUpdate();
    }
    
    public Rating retrieveRecord(String ratingID) throws SQLException{
        Rating rating = null;
        String query = "SELECT * FROM " + tableName + " WHERE RATING_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, ratingID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            rating = new Rating(rs.getString("RATING_ID"), prodDA.retrieveRecord(rs.getString("PRODUCT_ID")), userDA.retrieveRecord( rs.getString("USER_ID")), Integer.parseInt(rs.getString("SCORE")), rs.getString("COMMENT"), LocalDateTime.parse(rs.getString("TIME_RATED")), Integer.parseInt(rs.getString("TOTAL_VOTE")));
        }
        
        return rating;
    }
    
    public void updateRecord(Rating rating) throws SQLException{
        Rating ratingExist = retrieveRecord(rating.getRatingID());
        if(ratingExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET PRODUCT_ID = ?, USER_ID = ?, SCORE = ?, COMMENT = ?, TIME_RATED = ?, TOTAL_VOTE = ? WHERE RATING_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, rating.getProductID());
            stmt.setString(2, rating.getUserID());
            stmt.setString(3, String.valueOf(rating.getScore()));
            stmt.setString(4, rating.getComment());
            stmt.setString(5, rating.getTimeRated().toString());
            stmt.setString(6, String.valueOf(rating.getTotalVote()));
            stmt.setString(7, rating.getRatingID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(Rating rating) throws SQLException{
        Rating ratingExist = retrieveRecord(rating.getRatingID());
        if(ratingExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE RATING_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, rating.getRatingID());
            
            stmt.executeUpdate();
        }
    }
}