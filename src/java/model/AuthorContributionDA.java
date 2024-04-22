package model;
import entity.AuthorContribution;
import java.sql.*;

public class AuthorContributionDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "AUTHOR_CONTRIBUTION";
    private Connection conn;
    private PreparedStatement stmt;
    private AuthorDA authorDA;
    private CourseDA courseDA;
    
    public AuthorContributionDA() throws SQLException{
        authorDA = new AuthorDA();
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
    public void createRecord(AuthorContribution authorContribution) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, authorContribution.getContributionID());
        stmt.setString(2, authorContribution.getAuthorID());
        stmt.setString(3, authorContribution.getCourseID());
        stmt.setString(4, String.valueOf(authorContribution.getContribution()));
        stmt.setString(5, authorContribution.getDescription());
        
        stmt.executeUpdate();
    }
    
    public AuthorContribution retrieveRecord(String contributionID) throws SQLException{
        AuthorContribution authorContribution = null;
        String query = "SELECT * FROM " + tableName + " WHERE CONTRIBUTION_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, contributionID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            authorContribution = new AuthorContribution(rs.getString("CONTRIBUTION_ID"), authorDA.retrieveRecord(rs.getString("AUTHOR_ID")),courseDA.retrieveRecord(rs.getString("COURSE_ID")), Double.parseDouble(rs.getString("CONTRIBUTION")), rs.getString("DESCRIPTION"));
        }
        
        return authorContribution;
    }
    
    public void updateRecord(AuthorContribution authorContribution) throws SQLException{
        AuthorContribution authorContributionExist = retrieveRecord(authorContribution.getContributionID());
        if(authorContributionExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET AUTHOR_ID = ?, COURSE_ID = ?, CONTRIBUTION = ?, DESCRIPTION = ? WHERE CONTRIBUTION_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, authorContribution.getAuthorID());
            stmt.setString(2, authorContribution.getCourseID());
            stmt.setString(3, String.valueOf(authorContribution.getContribution()));
            stmt.setString(4, authorContribution.getDescription());
            stmt.setString(5, authorContribution.getContributionID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(AuthorContribution authorContribution) throws SQLException{
        AuthorContribution authorContributionExist = retrieveRecord(authorContribution.getContributionID());
        if(authorContributionExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE CONTRIBUTION_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, authorContribution.getContributionID());
            
            stmt.executeUpdate();
        }
    }
}
