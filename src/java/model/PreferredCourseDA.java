/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.PreferredCourse;
import java.sql.*;

/**
 *
 * @author Woo Yu Beng
 */

public class PreferredCourseDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "PREFERRED_COURSE";
    private Connection conn;
    private PreparedStatement stmt;
    private UserDA userDA;
    private CourseCategoryDA courseCatDA;
    
    public PreferredCourseDA() throws SQLException{
        userDA = new UserDA();
        courseCatDA = new CourseCategoryDA();
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
    public void createRecord(PreferredCourse preferredCourse) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, preferredCourse.getUserID());
        stmt.setString(2, preferredCourse.getCourseCatID());
        
        stmt.executeUpdate();
    }
    
    public PreferredCourse retrieveRecord(String userID) throws SQLException{
        PreferredCourse preferredCourse = null;
        String query = "SELECT * FROM " + tableName + " WHERE USER_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, userID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            preferredCourse = new PreferredCourse(userDA.retrieveRecord(rs.getString("USER_ID")), courseCatDA.retrieveRecord(rs.getString("COURSECAT_ID")));
        }
        
        return preferredCourse;
    }
    
    public void deleteRecord(PreferredCourse preferredCourse) throws SQLException{
        PreferredCourse preferredCourseExist = retrieveRecord(preferredCourse.getUserID());
        if(preferredCourseExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE USER_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, preferredCourse.getUserID());
            
            stmt.executeUpdate();
        }
    }
}