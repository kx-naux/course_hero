package model;
import entity.CourseCertType;
import java.sql.*;

public class CourseCertTypeDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "COURSE_CERT_TYPE";
    private Connection conn;
    private PreparedStatement stmt;
    private CourseDA courseDA;
    
    public CourseCertTypeDA() throws SQLException{
        courseDA = new CourseDA();
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
    public void createRecord(CourseCertType courseCertType) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, courseCertType.getCertTypeID());
        stmt.setString(2, courseCertType.getCourseID());
        stmt.setString(3, courseCertType.getDescription());
        stmt.setString(4, courseCertType.getIssuingAuthority());
        stmt.setString(5, courseCertType.getCertLevel());
        
        stmt.executeUpdate();
    }
    
    public CourseCertType retrieveRecord(String courseCertTypeID) throws SQLException{
        CourseCertType courseCertType = null;
        String query = "SELECT * FROM " + tableName + " WHERE CERTTYPE_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, courseCertTypeID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            courseCertType = new CourseCertType(rs.getString("CERTTYPE_ID"), courseDA.retrieveRecord(rs.getString("COURSE_ID")), rs.getString("DESCRIPTION"), rs.getString("ISSUING_AUTHORITY"), rs.getString("CERT_LEVEL"));
        }
        
        return courseCertType;
    }
    
    public void updateRecord(CourseCertType courseCertType) throws SQLException{
        CourseCertType courseCertTypeExist = retrieveRecord(courseCertType.getCertTypeID());
        if(courseCertTypeExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET COURSE_ID = ?, DESCRIPTION = ?, ISSUING_AUTHORITY = ?, CERT_LEVEL = ? WHERE CERTTYPE_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, courseCertType.getCourseID());
            stmt.setString(2, courseCertType.getDescription());
            stmt.setString(3, courseCertType.getIssuingAuthority());
            stmt.setString(4, courseCertType.getCertLevel());
            stmt.setString(5, courseCertType.getCertTypeID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(CourseCertType courseCertType) throws SQLException{
        CourseCertType courseCertTypeExist = retrieveRecord(courseCertType.getCertTypeID());
        if(courseCertTypeExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE CERTTYPE_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, courseCertType.getCertTypeID());
            
            stmt.executeUpdate();
        }
    }
}
