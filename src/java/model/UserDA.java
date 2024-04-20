/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.User;
import java.sql.*;
import java.time.*;

/**
 *
 * @author Woo Yu Beng
 */

public class UserDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "USERS";
    private Connection conn;
    private PreparedStatement stmt;
    private AccountDA accDA;
    private BillingAddressDA billingDA;
    
    public UserDA() throws SQLException{
        accDA = new AccountDA();
        billingDA = new BillingAddressDA();
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
    public void createRecord(User user) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, user.getUserID());
        stmt.setString(2, user.getAccountID());
        stmt.setString(3, user.getDob().toString());
        stmt.setString(4, user.getAddressID());
        stmt.setString(5, user.getUserType());
        stmt.setString(6, user.getValidity().toString());
        stmt.setString(7, user.getDateJoined().toString());
        stmt.setString(8, user.getGender());
        
        stmt.executeUpdate();
    }
    
    public User retrieveRecord(String userID) throws SQLException{
        User user = null;
        String query = "SELECT * FROM " + tableName + " WHERE USER_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, userID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            user = new User(rs.getString("USER_ID"), accDA.retrieveRecord(rs.getString("ACCOUNT_ID")), LocalDate.parse(rs.getString("DOB")), billingDA.retrieveRecord(rs.getString("ADDRESS_ID")), rs.getString("USERTYPE"), LocalDate.parse(rs.getString("VALIDITY")), LocalDateTime.parse(rs.getString("DATE_JOINED")), rs.getString("GENDER"));
        }
        
        return user;
    }
    
    public void updateRecord(User user) throws SQLException{
        User userExist = retrieveRecord(user.getUserID());
        if(userExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET ACCOUNT_ID = ?, DOB = ?, ADDRESS_ID = ?, USERTYPE = ?, VALIDITY = ?, DATE_JOINED = ?, GENDER = ? WHERE USER_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, user.getAccountID());
            stmt.setString(2, user.getDob().toString());
            stmt.setString(3, user.getAddressID());
            stmt.setString(4, user.getUserType());
            stmt.setString(5, user.getValidity().toString());
            stmt.setString(6, user.getDateJoined().toString());
            stmt.setString(7, user.getGender());
            stmt.setString(8, user.getUserID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(User user) throws SQLException{
        User userExist = retrieveRecord(user.getUserID());
        if(userExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE USER_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, user.getUserID());
            
            stmt.executeUpdate();
        }
    }
}
