/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.BillingAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Woo Yu Beng
 */

public class BillingAddressDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "BILLING_ADDRESS";
    private Connection conn;
    private PreparedStatement stmt;
    
    public BillingAddressDA() throws SQLException{
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
    public void createRecord(BillingAddress billingAddress) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, billingAddress.getAddressID());
        stmt.setString(2, billingAddress.getLine1());
        stmt.setString(3, billingAddress.getLine2());
        stmt.setString(4, billingAddress.getCity());
        stmt.setString(5, billingAddress.getStateResides());
        stmt.setString(6, billingAddress.getPostalCode());
        stmt.setString(7, billingAddress.getCountry());
        
        stmt.executeUpdate();
    }
    
    public BillingAddress retrieveRecord(String addressID) throws SQLException{
        BillingAddress billingAddress = null;
        String query = "SELECT * FROM " + tableName + " WHERE ADDRESS_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, addressID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            billingAddress = new BillingAddress(rs.getString("ADDRESS_ID"), rs.getString("LINE_1"), rs.getString("LINE_2"), rs.getString("CITY"), rs.getString("STATE_RESIDES"), rs.getString("POSTALCODE"), rs.getString("COUNTRY"));
        }
                
        return billingAddress;
    }
    
    public void updateRecord(BillingAddress billingAddress) throws SQLException{
        BillingAddress billingAddressExist = retrieveRecord(billingAddress.getAddressID());
        if(billingAddressExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET LINE_1 = ?, LINE_2 = ?, CITY = ?, STATE_RESIDES = ?, POSTALCODE = ?, COUNTRY = ? WHERE ADDRESS_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, billingAddress.getLine1());
            stmt.setString(2, billingAddress.getLine2());
            stmt.setString(3, billingAddress.getCity());
            stmt.setString(4, billingAddress.getStateResides());
            stmt.setString(5, billingAddress.getPostalCode());
            stmt.setString(6, billingAddress.getCountry());
            stmt.setString(7, billingAddress.getAddressID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(BillingAddress billingAddress) throws SQLException{
        BillingAddress billingAddressExist = retrieveRecord(billingAddress.getAddressID());
        if(billingAddressExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE ADDRESS_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, billingAddress.getAddressID());
            
            stmt.executeUpdate();
        }
    }
}
