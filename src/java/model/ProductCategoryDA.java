/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.ProductCategory;
import java.sql.*;

/**
 *
 * @author Woo Yu Beng
 */

public class ProductCategoryDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "PRODUCT_CATEGORY";
    private Connection conn;
    private PreparedStatement stmt;
    
    public ProductCategoryDA() throws SQLException{
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
    public void createRecord(ProductCategory productCategory) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, productCategory.getProdCatID());
        stmt.setString(2, productCategory.getCategoryName());
        stmt.setString(3, productCategory.getDescription());
        
        stmt.executeUpdate();
    }
    
    public ProductCategory retrieveRecord(String productCatID) throws SQLException{
        ProductCategory productCategory = null;
        String query = "SELECT * FROM " + tableName + " WHERE PRODCAT_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, productCatID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            productCategory = new ProductCategory(rs.getString("PRODCAT_ID"), rs.getString("CATEGORY_NAME"), rs.getString("DESCRIPTION"));
        }
        
        return productCategory;
    }
    
    public void updateRecord(ProductCategory productCategory) throws SQLException{
        ProductCategory productCategoryExist = retrieveRecord(productCategory.getProdCatID());
        if(productCategoryExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET CATEGORY_NAME = ?, DESCRIPTION = ? WHERE PRODCAT_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, productCategory.getCategoryName());
            stmt.setString(2, productCategory.getDescription());
            stmt.setString(3, productCategory.getProdCatID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(ProductCategory productCategory) throws SQLException{
        ProductCategory productCategoryExist = retrieveRecord(productCategory.getProdCatID());
        if(productCategoryExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE PRODCAT_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, productCategory.getProdCatID());
            
            stmt.executeUpdate();
        }
    }
}