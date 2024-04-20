/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.Merchandise;
import java.sql.*;

/**
 *
 * @author Woo Yu Beng
 */

public class MerchandiseDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "MERCHANDISE";
    private Connection conn;
    private PreparedStatement stmt;
    private ProductDA prodDA;
    
    public MerchandiseDA() throws SQLException{
        prodDA = new ProductDA();
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
    public void createRecord(Merchandise merchandise) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, merchandise.getMerchID());
        stmt.setString(2, merchandise.getProductID());
        stmt.setString(3, String.valueOf(merchandise.getDimensionH()));
        stmt.setString(4, String.valueOf(merchandise.getDimensionW()));
        stmt.setString(5, String.valueOf(merchandise.getDimensionL()));
        stmt.setString(6, String.valueOf(merchandise.getWeight()));
        stmt.setString(7, String.valueOf(merchandise.getStockBalance()));
        
        stmt.executeUpdate();
    }
    
    public Merchandise retrieveRecord(String merchID) throws SQLException{
        Merchandise merchandise = null;
        String query = "SELECT * FROM " + tableName + " WHERE MERCH_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, merchID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            merchandise = new Merchandise(rs.getString("MERCH_ID"), prodDA.retrieveRecord(rs.getString("PRODUCT_ID")), Double.parseDouble(rs.getString("DIMENSION_H_CM")), Double.parseDouble(rs.getString("DIMENSION_W_CM")), Double.parseDouble(rs.getString("DIMENSION_L_CM")),  Double.parseDouble(rs.getString("WEIGHT_KG")), Integer.parseInt(rs.getString("STOCK_BALANCE")));
        }
        
        return merchandise;
    }
    
    public void updateRecord(Merchandise merchandise) throws SQLException{
        Merchandise merchandiseExist = retrieveRecord(merchandise.getMerchID());
        if(merchandiseExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET PRODUCT_ID = ?, DIMENSION_H_CM = ?, DIMENSION_W_CM = ?, DIMENSION_L_CM = ?, WEIGHT_KG = ?, STOCK_BALANCE = ? WHERE MERCH_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, merchandise.getProductID());
            stmt.setString(2, String.valueOf(merchandise.getDimensionH()));
            stmt.setString(3, String.valueOf(merchandise.getDimensionW()));
            stmt.setString(4, String.valueOf(merchandise.getDimensionL()));
            stmt.setString(5, String.valueOf(merchandise.getWeight()));
            stmt.setString(6, String.valueOf(merchandise.getStockBalance()));
            stmt.setString(7, merchandise.getMerchID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(Merchandise merchandise) throws SQLException{
        Merchandise merchandiseExist = retrieveRecord(merchandise.getMerchID());
        if(merchandiseExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE MERCH_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, merchandise.getMerchID());
            
            stmt.executeUpdate();
        }
    }
}