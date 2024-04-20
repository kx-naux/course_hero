/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.Course;
import java.sql.*;

/**
 *
 * @author Woo Yu Beng
 */

public class CourseDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "COURSES";
    private Connection conn;
    private PreparedStatement stmt;
    private ProductDA prodDA;
    private CourseCategoryDA courseCatDA;
    
    public CourseDA() throws SQLException{
        prodDA = new ProductDA();
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
    public void createRecord(Course course) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, course.getCourseID());
        stmt.setString(2, course.getProductID());
        stmt.setString(3, course.getToolsUsed());
        stmt.setString(4, course.getCourseCatID());
        stmt.setString(5, course.getLearningObj());
        
        stmt.executeUpdate();
    }
    
    public Course retrieveRecord(String courseID) throws SQLException{
        Course course = null;
        String query = "SELECT * FROM " + tableName + " WHERE COURSE_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, courseID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            course = new Course(rs.getString("COURSE_ID"), prodDA.retrieveRecord(rs.getString("PRODUCT_ID")), rs.getString("TOOLS_USED"), courseCatDA.retrieveRecord(rs.getString("COURSECAT_ID")), rs.getString("LEARNING_OBJ"));
        }
        
        return course;
    }
    
    public void updateRecord(Course course) throws SQLException{
        Course courseExist = retrieveRecord(course.getCourseID());
        if(courseExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET PRODUCT_ID = ?, TOOLS_USED = ?, COURSECAT_ID = ?, LEARNING_OBJ = ? WHERE COURSE_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, course.getProductID());
            stmt.setString(2, course.getToolsUsed());
            stmt.setString(3, course.getCourseCatID());
            stmt.setString(4, course.getLearningObj());
            stmt.setString(5, course.getCourseID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(Course course) throws SQLException{
        Course courseExist = retrieveRecord(course.getCourseID());
        if(courseExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE COURSE_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, course.getCourseID());
            
            stmt.executeUpdate();
        }
    }
}
