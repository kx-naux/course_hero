/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.CourseCategory;
import java.sql.*;

/**
 *
 * @author Woo Yu Beng
 */

public class CourseCategoryDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "COURSE_CATEGORY";
    private Connection conn;
    private PreparedStatement stmt;
    
    public CourseCategoryDA() throws SQLException{
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
    public void createRecord(CourseCategory courseCategory) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, courseCategory.getCourseCatID());
        stmt.setString(2, courseCategory.getCategoryName());
        stmt.setString(3, courseCategory.getDescription());
        
        stmt.executeUpdate();
    }
    
    public CourseCategory retrieveRecord(String courseCatID) throws SQLException{
        CourseCategory courseCategory = null;
        String query = "SELECT * FROM " + tableName + " WHERE COURSECAT_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, courseCatID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            courseCategory = new CourseCategory(rs.getString("COURSECAT_ID"), rs.getString("CATEGORY_NAME"), rs.getString("DESCRIPTION"));
        }
        
        return courseCategory;
    }
    
    public void updateRecord(CourseCategory courseCategory) throws SQLException{
        CourseCategory courseCategoryExist = retrieveRecord(courseCategory.getCourseCatID());
        if(courseCategoryExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET CATEGORY_NAME = ?, DESCRIPTION = ? WHERE COURSECAT_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, courseCategory.getCategoryName());
            stmt.setString(2, courseCategory.getDescription());
            stmt.setString(3, courseCategory.getCourseCatID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(CourseCategory courseCategory) throws SQLException{
        CourseCategory courseCategoryExist = retrieveRecord(courseCategory.getCourseCatID());
        if(courseCategoryExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE COURSECAT_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, courseCategory.getCourseCatID());
            
            stmt.executeUpdate();
        }
    }
}