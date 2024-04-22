package model;
import entity.Message;
import java.sql.*;
import java.time.*;


public class MessageDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "MESSAGES";
    private Connection conn;
    private PreparedStatement stmt;
    private UserDA userDA;
    private ChatRoomDA chatRoomDA;
    
    public MessageDA() throws SQLException{
        userDA = new UserDA();
        chatRoomDA = new ChatRoomDA();
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
    public void createRecord(Message message) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, message.getMessageID());
        stmt.setString(2, message.getUserID());
        stmt.setString(3, message.getChatRoomID());
        stmt.setString(4, message.getMessageType());
        stmt.setString(5, message.getContent());
        stmt.setString(6, message.getDestination());
        stmt.setString(7, message.getTimeCreated().toString());
        
        stmt.executeUpdate();
    }
    
    public Message retrieveRecord(String messageID) throws SQLException{
        Message message = null;
        String query = "SELECT * FROM " + tableName + " WHERE MESSAGE_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, messageID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            message = new Message(rs.getString("MESSAGE_ID"), userDA.retrieveRecord(rs.getString("USER_ID")), chatRoomDA.retrieveRecord(rs.getString("CHATROOM_ID")), rs.getString("MESSAGE_TYPE"), rs.getString("CONTENT"), rs.getString("DESTINATION"), LocalDateTime.parse(rs.getString("TIME_CREATED")));
        }
        
        return message;
    }
    
    public void updateRecord(Message message) throws SQLException{
        Message messageExist = retrieveRecord(message.getMessageID());
        if(messageExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET USER_ID = ?, CHATROOM_ID = ?, MESSAGE_TYPE = ?, CONTENT = ?, DESTINATION = ?, TIME_CREATED = ? WHERE MESSAGE_ID = ?";
            stmt = conn.prepareStatement(query);
            
        
            stmt.setString(1, message.getUserID());
            stmt.setString(2, message.getChatRoomID());
            stmt.setString(3, message.getMessageType());
            stmt.setString(4, message.getContent());
            stmt.setString(5, message.getDestination());
            stmt.setString(6, message.getTimeCreated().toString());
            stmt.setString(7, message.getMessageID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(Message message) throws SQLException{
        Message messageExist = retrieveRecord(message.getMessageID());
        if(messageExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE MESSAGE_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, message.getMessageID());
            
            stmt.executeUpdate();
        }
    }
}
