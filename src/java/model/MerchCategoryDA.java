/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.MerchCategory;
import java.sql.*;

/**
 *
 * @author Woo Yu Beng
 */

public class MerchCategoryDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "MERCH_CATEGORY";
    private Connection conn;
    private PreparedStatement stmt;
    
    public MerchCategoryDA() throws SQLException{
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
    public void createRecord(MerchCategory merchCategory) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, merchCategory.getMerchCatID());
        stmt.setString(2, merchCategory.getCategoryName());
        stmt.setString(3, merchCategory.getDescription());
        
        stmt.executeUpdate();
    }
    
    public MerchCategory retrieveRecord(String merchCatID) throws SQLException{
        MerchCategory merchCategory = null;
        String query = "SELECT * FROM " + tableName + " WHERE MERCHCAT_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, merchCatID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            merchCategory = new MerchCategory(rs.getString("MERCHCAT_ID"), rs.getString("CATEGORY_NAME"), rs.getString("DESCRIPTION"));
        }
        
        return merchCategory;
    }
    
    public void updateRecord(MerchCategory merchCategory) throws SQLException{
        MerchCategory merchCategoryExist = retrieveRecord(merchCategory.getMerchCatID());
        if(merchCategoryExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET CATEGORY_NAME = ?, DESCRIPTION = ? WHERE MERCHCAT_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, merchCategory.getCategoryName());
            stmt.setString(2, merchCategory.getDescription());
            stmt.setString(3, merchCategory.getMerchCatID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(MerchCategory merchCategory) throws SQLException{
        MerchCategory merchCategoryExist = retrieveRecord(merchCategory.getMerchCatID());
        if(merchCategoryExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE MERCHCAT_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, merchCategory.getMerchCatID());
            
            stmt.executeUpdate();
        }
    }
}