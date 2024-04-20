/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.ShippingMethod;
import java.sql.*;

/**
 *
 * @author Woo Yu Beng
 */

public class ShippingMethodDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "SHIPPING_METHOD";
    private Connection conn;
    private PreparedStatement stmt;
    
    public ShippingMethodDA() throws SQLException{
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
    public void createRecord(ShippingMethod shippingMethod) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, shippingMethod.getShippingMethodID());
        stmt.setString(2, shippingMethod.getShippingType());
        stmt.setString(3, shippingMethod.getDeliverySpeed());
        stmt.setString(4, shippingMethod.getDescription());
        stmt.setString(5, shippingMethod.getCoverageArea());
        stmt.setString(6, String.valueOf(shippingMethod.getShippingRates()));
        stmt.setString(7, shippingMethod.getReturnPolicy());
        
        stmt.executeUpdate();
    }
    
    public ShippingMethod retrieveRecord(String shippingMethodID) throws SQLException{
        ShippingMethod shippingMethod = null;
        String query = "SELECT * FROM " + tableName + " WHERE SHIPPING_METHOD_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, shippingMethodID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            shippingMethod = new ShippingMethod(rs.getString("SHIPPING_METHOD_ID"), rs.getString("SHIPPING_TYPE"), rs.getString("DELIVERY_SPEED"), rs.getString("DESCRIPTION"), rs.getString("COVERAGE_AREA"), Double.parseDouble(rs.getString("SHIPPING_RATES")), rs.getString("RETURN_POLICY"));
        }
        
        return shippingMethod;
    }
    
    public void updateRecord(ShippingMethod shippingMethod) throws SQLException{
        ShippingMethod shippingMethodExist = retrieveRecord(shippingMethod.getShippingMethodID());
        if(shippingMethodExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET SHIPPING_TYPE = ?, DELIVERY_SPEED = ?, DESCRIPTION = ?, COVERAGE_AREA = ?, SHIPPING_RATES = ?, RETURN_POLICY = ? WHERE SHIPPING_METHOD_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, shippingMethod.getShippingType());
            stmt.setString(2, shippingMethod.getDeliverySpeed());
            stmt.setString(3, shippingMethod.getDescription());
            stmt.setString(4, shippingMethod.getCoverageArea());
            stmt.setString(5, String.valueOf(shippingMethod.getShippingRates()));
            stmt.setString(6, shippingMethod.getReturnPolicy());
            stmt.setString(7, shippingMethod.getShippingMethodID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(ShippingMethod shippingMethod) throws SQLException{
        ShippingMethod shippingMethodExist = retrieveRecord(shippingMethod.getShippingMethodID());
        if(shippingMethodExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE SHIPPING_METHOD_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, shippingMethod.getShippingMethodID());
            
            stmt.executeUpdate();
        }
    }
}
