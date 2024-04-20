/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.Product;
import java.sql.*;

/**
 *
 * @author Woo Yu Beng
 */

public class ProductDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "PRODUCT";
    private Connection conn;
    private PreparedStatement stmt;
    private ProductCategoryDA prodCatDA;
    
    public ProductDA() throws SQLException{
        prodCatDA = new ProductCategoryDA();
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
    public void createRecord(Product product) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, product.getProductID());
        stmt.setString(2, product.getProdCatID());
        stmt.setString(3, product.getProdName());
        stmt.setString(4, String.valueOf(product.getPrice()));
        stmt.setString(5, String.valueOf(product.getRateWeightage()));
        stmt.setString(6, String.valueOf(product.getAvgRating()));
        stmt.setString(7, String.valueOf(product.getDiscount()));
        stmt.setString(8, product.getImagePath());
        
        stmt.executeUpdate();
    }
    
    public Product retrieveRecord(String productID) throws SQLException{
        Product product = null;
        String query = "SELECT * FROM " + tableName + " WHERE PRODUCT_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, productID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            product = new Product(rs.getString("PRODUCT_ID"), prodCatDA.retrieveRecord(rs.getString("PRODCAT_ID")), rs.getString("PROD_NAME"), Double.parseDouble(rs.getString("PRICE")), Integer.parseInt(rs.getString("RATE_WEIGHTAGE")), Double.parseDouble(rs.getString("AVG_RATING")), Double.parseDouble(rs.getString("DISCOUNT")), rs.getString("IMAGE_PATH"));
        }
        
        return product;
    }
    
    public void updateRecord(Product product) throws SQLException{
        Product productExist = retrieveRecord(product.getProductID());
        if(productExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET PRODCAT_ID = ?, PROD_NAME = ?, PRICE = ?, RATE_WEIGHTAGE = ?, AVG_RATING = ?, DISCOUNT = ?, IMAGE_PATH = ? WHERE PRODUCT_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, product.getProdCatID());
            stmt.setString(2, product.getProdName());
            stmt.setString(3, String.valueOf(product.getPrice()));
            stmt.setString(4, String.valueOf(product.getRateWeightage()));
            stmt.setString(5, String.valueOf(product.getAvgRating()));
            stmt.setString(6, String.valueOf(product.getDiscount()));
            stmt.setString(7, product.getImagePath());
            stmt.setString(8, product.getProductID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(Product product) throws SQLException{
        Product productExist = retrieveRecord(product.getProductID());
        if(productExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE PRODUCT_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, product.getProductID());
            
            stmt.executeUpdate();
        }
    }
}
