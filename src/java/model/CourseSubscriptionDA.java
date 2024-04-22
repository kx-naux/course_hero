package model;
import entity.CourseSubscriptions;
import java.sql.*;
import java.time.*;

public class CourseSubscriptionDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "COURSE_SUBSCRIPTIONS";
    private Connection conn;
    private PreparedStatement stmt;
    private CourseDA courseDA;
    private UserDA userDA;
    
    public CourseSubscriptionDA() throws SQLException{
        courseDA = new CourseDA();
        userDA = new UserDA();
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
    public void createRecord(CourseSubscriptions courseSubscriptions) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, courseSubscriptions.getSubscriptionsID());
        stmt.setString(2, courseSubscriptions.getCourseID());
        stmt.setString(3, courseSubscriptions.getUserID());
        stmt.setString(4, courseSubscriptions.getSubscribeTime().toString());
        stmt.setString(5, courseSubscriptions.getStartTime().toString());
        stmt.setString(6, courseSubscriptions.getProgress());
        stmt.setString(7, courseSubscriptions.getFinishTime().toString());
        
        stmt.executeUpdate();
    }
    
    public CourseSubscriptions retrieveRecord(String subscriptionsID) throws SQLException{
        CourseSubscriptions courseSubscriptions = null;
        String query = "SELECT * FROM " + tableName + " WHERE SUBSCRIPTIONS_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, subscriptionsID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            courseSubscriptions = new CourseSubscriptions(rs.getString("SUBSCRIPTIONS_ID"), courseDA.retrieveRecord(rs.getString("COURSE_ID")), userDA.retrieveRecord(rs.getString("USER_ID")), LocalDateTime.parse(rs.getString("SUBSCRIBE_DATE")), LocalDateTime.parse(rs.getString("START_TIME")), rs.getString("PROGRESS"), LocalDateTime.parse(rs.getString("FINISH_TIME")));
        }
        
        return courseSubscriptions;
    }
    
    public void updateRecord(CourseSubscriptions courseSubscriptions) throws SQLException{
        CourseSubscriptions courseSubscriptionsExist = retrieveRecord(courseSubscriptions.getSubscriptionsID());
        if(courseSubscriptionsExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET COURSE_ID = ?, USER_ID = ?, SUBSCRIBE_TIME = ?, START_TIME = ?, PROGRESS = ?, FINISH_TIME = ? WHERE SUBSCRIPTIONS_ID = ?";
            stmt = conn.prepareStatement(query);
            
        stmt.setString(1, courseSubscriptions.getCourseID());
        stmt.setString(2, courseSubscriptions.getUserID());
        stmt.setString(3, courseSubscriptions.getSubscribeTime().toString());
        stmt.setString(4, courseSubscriptions.getStartTime().toString());
        stmt.setString(5, courseSubscriptions.getProgress());
        stmt.setString(6, courseSubscriptions.getFinishTime().toString());
        stmt.setString(7, courseSubscriptions.getSubscriptionsID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(CourseSubscriptions courseSubscriptions) throws SQLException{
        CourseSubscriptions courseSubscriptionsExist = retrieveRecord(courseSubscriptions.getSubscriptionsID());
        if(courseSubscriptionsExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE SUBSCRIPTIONS_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, courseSubscriptions.getSubscriptionsID());
            
            stmt.executeUpdate();
        }
    }
}
