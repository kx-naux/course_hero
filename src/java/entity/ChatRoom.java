package entity;



public class ChatRoom { 
    
    private String chatRoomID;
    private String chatRoomType;
    private String status;
    private String reqTitle;
    private String reqDescription;


    public ChatRoom() {
    }

    public ChatRoom(String chatRoomID) {
        this.chatRoomID= chatRoomID;
    }

    public ChatRoom(String chatRoomID, String chatRoomType, String status, String reqTitle, String reqDescription) {
        this.chatRoomID = chatRoomID;
        this.chatRoomType = chatRoomType;
        this.status = status;
        this.reqTitle = reqTitle;
        this.reqDescription = reqDescription;
    }

    public String getChatRoomID() {
        return chatRoomID;
    }
    
    public String getChatRoomType() {
        return chatRoomType;
    }
    
    public String getStatus() {
        return status;
    }

    public String getReqTitle(){
        return reqTitle;
    }
    
    public String getReqDescription(){
        return reqDescription;
    }
    
    
    public void setChatRoomID(String chatRoomID) {
        this.chatRoomID = chatRoomID;
    }

    public void setChatRoomType(String chatRoomType) {
        this.chatRoomType = chatRoomType;
    }

    public void setStatus(String status) {
        this.status = status;
    }
 
    public void setReqTitle(String reqTitle) {
        this.reqTitle = reqTitle;
    }
        
    public void setReqDescription(String reqDescription) {
        this.reqDescription = reqDescription;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s %s %s %s", chatRoomID, chatRoomType, status, reqTitle, reqDescription);
    }
    
}

    
    
    
    