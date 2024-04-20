/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.Shipping;
import java.sql.*;
import java.time.*;

/**
 *
 * @author Woo Yu Beng
 */

public class ShippingDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "SHIPPING";
    private Connection conn;
    private PreparedStatement stmt;
    private BillingAddressDA billingDA;
    
    public ShippingDA() throws SQLException{
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
    public void createRecord(Shipping shipping) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, shipping.getShippingID());
        stmt.setString(2, shipping.getAddressID());
        stmt.setString(3, shipping.getShippingDate().toString());
        stmt.setString(4, shipping.getExpectedDeliveryDate().toString());
        stmt.setString(5, String.valueOf(shipping.getShippingCost()));
        stmt.setString(6, String.valueOf(shipping.getWeight()));
        stmt.setString(7, shipping.getDimensionCM());
        stmt.setString(8, shipping.getShippingNotes());
        
        stmt.executeUpdate();
    }
    
    public Shipping retrieveRecord(String shippingID) throws SQLException{
        Shipping shipping = null;
        String query = "SELECT * FROM " + tableName + " WHERE SHIPPING_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, shippingID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            shipping = new Shipping(rs.getString("SHIPPING_ID"), billingDA.retrieveRecord(rs.getString("ADDRESS_ID")), LocalDate.parse(rs.getString("SHIPPING_DATE")), LocalDate.parse(rs.getString("EXPECTED_DELIVERY_DATE")), Double.parseDouble(rs.getString("SHIPPING_COST")), Double.parseDouble(rs.getString("TTL_WEIGHT_KG")), rs.getString("DIMENSTION_CM_HxWxL"), rs.getString("SHIPPING_NOTES"));
        }
        
        return shipping;
    }
    
    public void updateRecord(Shipping shipping) throws SQLException{
        Shipping shippingExist = retrieveRecord(shipping.getShippingID());
        if(shippingExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET ADDRESS_ID = ?, SHIPPING_DATE = ?, EXPECTED_DELIVERY_DATE = ?, SHIPPING_COST = ?, TTL_WEIGHT_KG = ?, DIMENSTION_CM_HxWxL = ?, SHIPPING_NOTES = ? WHERE SHIPPING_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, shipping.getAddressID());
            stmt.setString(2, shipping.getShippingDate().toString());
            stmt.setString(3, shipping.getExpectedDeliveryDate().toString());
            stmt.setString(4, String.valueOf(shipping.getShippingCost()));
            stmt.setString(5, String.valueOf(shipping.getWeight()));
            stmt.setString(6, shipping.getDimensionCM());
            stmt.setString(7, shipping.getShippingNotes());
            stmt.setString(8, shipping.getShippingID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(Shipping shipping) throws SQLException{
        Shipping shippingExist = retrieveRecord(shipping.getShippingID());
        if(shippingExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE SHIPPING_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, shipping.getShippingID());
            
            stmt.executeUpdate();
        }
    }
}
