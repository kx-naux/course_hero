package entity;



public class UserChatRoom { 
    
    private String userID;
    private String chatRoomID;
    private String chatRole;


    public UserChatRoom() {
    }

    public UserChatRoom(User user, ChatRoom chatRoom, String chatRole) {
        this.userID = user.getUserID();
        this.chatRoomID = chatRoom.getChatRoomID();
        this.chatRole = chatRole;
    }

    public String getUserID() {
        return userID;
    }
    
    public String getChatRoomID() {
        return chatRoomID;
    }
    
    public String getChatRole() {
        return chatRole;
    }
    
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setChatRoomID(String chatRoomID) {
        this.chatRoomID = chatRoomID;
    }

    public void setChatRole(String chatRole) {
        this.chatRole = chatRole;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", userID, chatRoomID, chatRole);
    }
    
}

    
    
    
    