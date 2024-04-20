/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.SocialMediaLink;
import java.sql.*;

/**
 *
 * @author Woo Yu Beng
 */

public class SocialMediaLinkDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "SOCIAL_MEDIA_LINKS";
    private Connection conn;
    private PreparedStatement stmt;
    private AuthorDA authorDA;
    
    public SocialMediaLinkDA() throws SQLException{
        authorDA = new AuthorDA();
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
    public void createRecord(SocialMediaLink socialMediaLink) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, socialMediaLink.getSocialMediaLinkID());
        stmt.setString(2, socialMediaLink.getAuthorID());
        stmt.setString(3, socialMediaLink.getSocialMediaName());
        stmt.setString(4, socialMediaLink.getDestLink());
        
        stmt.executeUpdate();
    }
    
    public SocialMediaLink retrieveRecord(String socialMediaLinkID) throws SQLException{
        SocialMediaLink socialMediaLink = null;
        String query = "SELECT * FROM " + tableName + " WHERE SOCIALMEDIALINK_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, socialMediaLinkID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            socialMediaLink = new SocialMediaLink(rs.getString("SOCIALMEDIALINK_ID"), authorDA.retrieveRecord(rs.getString("AUTHOR_ID")), rs.getString("SOCIALMEDIA_NAME"), rs.getString("DEST_LINK"));
        }
        
        return socialMediaLink;
    }
    
    public void updateRecord(SocialMediaLink socialMediaLink) throws SQLException{
        SocialMediaLink socialMediaLinkExist = retrieveRecord(socialMediaLink.getSocialMediaLinkID());
        if(socialMediaLinkExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET AUTHOR_ID = ?, SOCIALMEDIA_NAME = ?, DEST_LINK = ? WHERE SOCIALMEDIALINK_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, socialMediaLink.getAuthorID());
            stmt.setString(2, socialMediaLink.getSocialMediaName());
            stmt.setString(3, socialMediaLink.getDestLink());
            stmt.setString(4, socialMediaLink.getSocialMediaLinkID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(SocialMediaLink socialMediaLink) throws SQLException{
        SocialMediaLink socialMediaLinkExist = retrieveRecord(socialMediaLink.getSocialMediaLinkID());
        if(socialMediaLinkExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE SOCIALMEDIALINK_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, socialMediaLink.getSocialMediaLinkID());
            
            stmt.executeUpdate();
        }
    }
}
