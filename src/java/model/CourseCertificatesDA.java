package model;
import entity.CourseCertificates;
import java.sql.*;
import java.time.*;

public class CourseCertificatesDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "COURSE_CERTIFICATES";
    private Connection conn;
    private PreparedStatement stmt;
    private UserDA userDA;
    private CourseDA courseDA;
    private CourseCertTypeDA courseCertTypeDA;
    
    public CourseCertificatesDA() throws SQLException{
        userDA = new UserDA();
        courseDA = new CourseDA();
        courseCertTypeDA = new CourseCertTypeDA();
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
    public void createRecord(CourseCertificates courseCertificates) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, courseCertificates.getCertificatesID());
        stmt.setString(2, courseCertificates.getCertTypeID());
        stmt.setString(3, courseCertificates.getUserID());
        stmt.setString(4, courseCertificates.getCourseID());
        stmt.setString(5, courseCertificates.getTimeIssued().toString());
        stmt.setString(6, courseCertificates.getCertificateNumber());
        
        stmt.executeUpdate();
    }
    
    public CourseCertificates retrieveRecord(String certificatesID) throws SQLException{
        CourseCertificates courseCertificates = null;
        String query = "SELECT * FROM " + tableName + " WHERE CERTIFICATE_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, certificatesID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            courseCertificates = new CourseCertificates(rs.getString("CERTIFICATE_ID"), courseCertTypeDA.retrieveRecord(rs.getString("CERTTYPE_ID")),userDA.retrieveRecord(rs.getString("USER_ID")), courseDA.retrieveRecord(rs.getString("COURSE_ID")), LocalDateTime.parse(rs.getString("TIME_ISSUED")), rs.getString("CERTIFICATE_NUMBER"));
        }
        
        return courseCertificates;
    }
    
    public void updateRecord(CourseCertificates courseCertificates) throws SQLException{
        CourseCertificates courseCertificatesExists = retrieveRecord(courseCertificates.getCertificatesID());
        if(courseCertificatesExists ==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET CERTTYPE_ID = ?, USER_ID = ?, COURSE_ID= ?, TIME_ISSUED = ?, CERTIFICATE_NUMBER= ? WHERE CERTIFICATE_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, courseCertificates.getCertTypeID());
            stmt.setString(2, courseCertificates.getUserID());
            stmt.setString(3, courseCertificates.getCourseID());
            stmt.setString(4, courseCertificates.getTimeIssued().toString());
            stmt.setString(5, courseCertificates.getCertificateNumber());
            stmt.setString(6, courseCertificates.getCertificatesID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(CourseCertificates courseCertificates) throws SQLException{
        CourseCertificates courseCertificatesExist = retrieveRecord(courseCertificates.getCertificatesID());
        if(courseCertificatesExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE CERTIFICATE_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, courseCertificates.getCertificatesID());
            
            stmt.executeUpdate();
        }
    }
}
