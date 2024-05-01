/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPAEntity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Embeddable
public class UserChatroomsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "USER_ID")
    private String userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "CHATROOM_ID")
    private String chatroomId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CHAT_ROLE")
    private String chatRole;

    public UserChatroomsPK() {
    }

    public UserChatroomsPK(String userId, String chatroomId, String chatRole) {
        this.userId = userId;
        this.chatroomId = chatroomId;
        this.chatRole = chatRole;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(String chatroomId) {
        this.chatroomId = chatroomId;
    }

    public String getChatRole() {
        return chatRole;
    }

    public void setChatRole(String chatRole) {
        this.chatRole = chatRole;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        hash += (chatroomId != null ? chatroomId.hashCode() : 0);
        hash += (chatRole != null ? chatRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserChatroomsPK)) {
            return false;
        }
        UserChatroomsPK other = (UserChatroomsPK) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        if ((this.chatroomId == null && other.chatroomId != null) || (this.chatroomId != null && !this.chatroomId.equals(other.chatroomId))) {
            return false;
        }
        if ((this.chatRole == null && other.chatRole != null) || (this.chatRole != null && !this.chatRole.equals(other.chatRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.UserChatroomsPK[ userId=" + userId + ", chatroomId=" + chatroomId + ", chatRole=" + chatRole + " ]";
    }
    
}
