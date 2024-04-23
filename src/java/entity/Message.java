package entity;
import java.time.LocalDateTime;


public class Message{ 
    
    private String messageID;
    private String userID;
    private String chatRoomID;
    private String messageType;
    private String content;
    private String destination;
    private LocalDateTime timeCreated;


    public Message() {
    }

    public Message(String messageID, User user, ChatRoom chatRoom, String messageType, String content,
                    String destination, LocalDateTime timeCreated) {
        this.messageID= messageID;
        this.userID= user.getUserID();
        this.chatRoomID = chatRoom.getChatRoomID();
        this.messageType = messageType;
        this.content= content;
        this.destination = destination;
        this.timeCreated = timeCreated;
    }

    public String getMessageID() {
        return messageID;
    }
    
    public String getChatRoomID() {
        return chatRoomID;
    }
    
    public String getMessageType() {
        return messageType;
    }
    
    public String getContent() {
        return content;
    }
     
    public String getUserID() {
        return userID;
    }
    
    public String getDestination() {
        return destination;
    }
    
    public LocalDateTime getTimeCreated(){
        return timeCreated;
    }
    
    public void setMessageID(String messageID){
        this.messageID = messageID;
    }
           
    public void setChatRoomID(String chatRoomID) {
        this.chatRoomID = chatRoomID;
    }
    
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public void setUserID(String userID) {
        this.userID = userID;
    }
     
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated= timeCreated;
    }

 
    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s %s ", messageID, userID, chatRoomID, messageType, content, destination, timeCreated);
    }
    
}

    
    
    
    