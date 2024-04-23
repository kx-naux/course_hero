package model;
import entity.ChatRoom;
import java.sql.*;

public class ChatRoomDA  {
    private String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "CHATROOMS";
    private Connection conn;
    private PreparedStatement stmt;
    
    
    public ChatRoomDA()throws SQLException {
        createConnection();
    }
    
    private void createConnection() throws SQLException{
        conn = DriverManager.getConnection(host, user, password);
        System.out.println("***TRACE: Connection established.");
    }  
    
    public void shutDownConnection() throws SQLException{
        if (conn != null)
            conn.close();
    }
    
    public ChatRoom retrieveRecord(String chatRoomID) throws SQLException {
        String queryStr = "SELECT * FROM " + tableName + " WHERE CHATROOM_ID = ?";
        ChatRoom chatRoom = null;

            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, chatRoomID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                chatRoom= new ChatRoom(chatRoomID, rs.getString("CHATROOM_TYPE"), rs.getString("STATUS"), rs.getString("REQ_TITLE"), rs.getString("REQ_DESCRIPTION"));
            }
        return chatRoom;
    }
    
    public void createRecord(ChatRoom chatRoom) throws SQLException{
        String insertStr = "Insert Into " + tableName + " values (?,?,?,?,?)";
   
                stmt = conn.prepareStatement(insertStr);
                stmt.setString(1,chatRoom.getChatRoomID());
                stmt.setString(2,chatRoom.getChatRoomType());
                stmt.setString(3,chatRoom.getStatus());
                stmt.setString(4,chatRoom.getReqTitle());
                stmt.setString(5,chatRoom.getReqDescription());
                stmt.executeUpdate();         
    }
    
    public void updateRecord(ChatRoom chatRoom) throws SQLException{
            ChatRoom chatRoomExist = retrieveRecord(chatRoom.getChatRoomID());
            if(chatRoomExist==null){
                throw new SQLException("Record Does Not Exist");
            }else{
                String updateStr = " UPDATE " + tableName + " SET CHATROOM_TYPE=?,STATUS=?,REQ_TITLE=?,REQ_DESCRIPTION=? WHERE CHATROOM_ID=? ";

                stmt = conn.prepareStatement(updateStr);
                stmt.setString(1,chatRoom.getChatRoomType());
                stmt.setString(2,chatRoom.getStatus());
                stmt.setString(3,chatRoom.getReqTitle());
                stmt.setString(4,chatRoom.getReqDescription());
                stmt.setString(5,chatRoom.getChatRoomID());
                stmt.executeUpdate(); 
            }
    }
        
    public void deleteRecord(ChatRoom chatRoom)throws SQLException{
        ChatRoom chatRoomExist = retrieveRecord(chatRoom.getChatRoomID());
        if(chatRoomExist == null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String deleteStr = " DELETE FROM " + tableName + " WHERE CHATROOM_ID=? ";
        
            stmt = conn.prepareStatement(deleteStr);
            stmt.setString(1,chatRoom.getChatRoomID());
            stmt.executeUpdate();
        }
    }
}
