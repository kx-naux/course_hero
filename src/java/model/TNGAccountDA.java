/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.TNGAccount;
import java.sql.*;

/**
 *
 * @author Woo Yu Beng
 */

public class TNGAccountDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "TNG_ACCOUNTS";
    private Connection conn;
    private PreparedStatement stmt;
    private UserDA userDA;
    
    public TNGAccountDA() throws SQLException{
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
    public void createRecord(TNGAccount tngAccount) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, tngAccount.getTngID());
        stmt.setString(2, tngAccount.getUserID());
        stmt.setString(3, tngAccount.getPhoneNo());
        
        stmt.executeUpdate();
    }
    
    public TNGAccount retrieveRecord(String tngID) throws SQLException{
        TNGAccount tngAccount = null;
        String query = "SELECT * FROM " + tableName + " WHERE TNG_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, tngID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            tngAccount = new TNGAccount(rs.getString("TNG_ID"), userDA.retrieveRecord(rs.getString("USER_ID")), rs.getString("PHONENO"));
        }
        
        return tngAccount;
    }
    
    public void updateRecord(TNGAccount tngAccount) throws SQLException{
        TNGAccount tngAccountExist = retrieveRecord(tngAccount.getTngID());
        if(tngAccountExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET USER_ID = ?, PHONENO = ? WHERE TNG_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, tngAccount.getUserID());
            stmt.setString(2, tngAccount.getPhoneNo());
            stmt.setString(3, tngAccount.getTngID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(TNGAccount tngAccount) throws SQLException{
        TNGAccount tngAccountExist = retrieveRecord(tngAccount.getTngID());
        if(tngAccountExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE TNG_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, tngAccount.getTngID());
            
            stmt.executeUpdate();
        }
    }
}