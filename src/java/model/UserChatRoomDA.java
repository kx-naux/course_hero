package model;
import entity.UserChatRoom;
import java.sql.*;

public class UserChatRoomDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "USER_CHATROOMS";
    private Connection conn;
    private PreparedStatement stmt;
    private UserDA userDA;
    private ChatRoomDA chatRoomDA;
    
    public UserChatRoomDA() throws SQLException{
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
    public void createRecord(UserChatRoom userChatRoom) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, userChatRoom.getUserID());
        stmt.setString(2, userChatRoom.getChatRoomID());
        stmt.setString(3, userChatRoom.getChatRole());
        
        stmt.executeUpdate();
    }
    
    public UserChatRoom retrieveRecord(String userID) throws SQLException{
        UserChatRoom userChatRoom = null;
        String query = "SELECT * FROM " + tableName + " WHERE USER_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, userID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            userChatRoom = new UserChatRoom(userDA.retrieveRecord(rs.getString("USER_ID")), chatRoomDA.retrieveRecord(rs.getString("CHATROOM_ID")), rs.getString("CHAT_ROLE"));
        }
        return userChatRoom;
    }
    
    public void updateRecord(UserChatRoom userChatRoom) throws SQLException{
        UserChatRoom userChatRoomExist = retrieveRecord(userChatRoom.getUserID());
        if(userChatRoomExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET USER_ID = ?, CHATROOM_ID = ? WHERE USER_ID=?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, userChatRoom.getUserID());
            stmt.setString(2, userChatRoom.getChatRoomID());
            stmt.setString(3, userChatRoom.getChatRole());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(UserChatRoom userChatRoom) throws SQLException{
        UserChatRoom userChatRoomExist = retrieveRecord(userChatRoom.getUserID());
        if(userChatRoomExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE USER_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, userChatRoom.getUserID());
            
            stmt.executeUpdate();
        }
    }
    
}
