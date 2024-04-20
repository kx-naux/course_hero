/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.Author;
import java.sql.*;
import java.time.*;

/**
 *
 * @author Woo Yu Beng
 */

public class AuthorDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "AUTHORS";
    private Connection conn;
    private PreparedStatement stmt;
    
    public AuthorDA() throws SQLException{
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
    public void createRecord(Author author) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, author.getAuthorID());
        stmt.setString(2, author.getAuthorName());
        stmt.setString(3, author.getNationality());
        stmt.setString(4, author.getInstitution());
        stmt.setString(5, author.getAwardsHonors());
        stmt.setString(6, author.getBiography());
        stmt.setString(7, author.getDob().toString());
        stmt.setString(8, author.getWebsite());
        
        stmt.executeUpdate();
    }
    
    public Author retrieveRecord(String authorID) throws SQLException{
        Author author = null;
        String query = "SELECT * FROM " + tableName + " WHERE AUTHOR_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, authorID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            author = new Author(rs.getString("AUTHOR_ID"), rs.getString("AUTHOR_NAME"), rs.getString("NATIONALITY"), rs.getString("INSTITUTION"), rs.getString("AWARDS_HONORS"), rs.getString("BIOGRAPHY"), LocalDate.parse(rs.getString("DOB")), rs.getString("WEBSITE"));
        }
        
        return author;
    }
    
    public void updateRecord(Author author) throws SQLException{
        Author authorExist = retrieveRecord(author.getAuthorID());
        if(authorExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET AUTHOR_NAME = ?, NATIONALITY = ?, INSTITUTION = ?, AWARDS_HONORS = ?, BIOGRAPHY = ?, DOB = ?, WEBSITE = ? WHERE AUTHOR_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, author.getAuthorName());
            stmt.setString(2, author.getNationality());
            stmt.setString(3, author.getInstitution());
            stmt.setString(4, author.getAwardsHonors());
            stmt.setString(5, author.getBiography());
            stmt.setString(6, author.getDob().toString());
            stmt.setString(7, author.getWebsite());
            stmt.setString(8, author.getAuthorID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(Author author) throws SQLException{
        Author authorExist = retrieveRecord(author.getAuthorID());
        if(authorExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE AUTHOR_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, author.getAuthorID());
            
            stmt.executeUpdate();
        }
    }
}
