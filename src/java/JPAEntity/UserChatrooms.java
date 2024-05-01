/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPAEntity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "USER_CHATROOMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserChatrooms.findAll", query = "SELECT u FROM UserChatrooms u"),
    @NamedQuery(name = "UserChatrooms.findByUserId", query = "SELECT u FROM UserChatrooms u WHERE u.userChatroomsPK.userId = :userId"),
    @NamedQuery(name = "UserChatrooms.findByChatroomId", query = "SELECT u FROM UserChatrooms u WHERE u.userChatroomsPK.chatroomId = :chatroomId"),
    @NamedQuery(name = "UserChatrooms.findByChatRole", query = "SELECT u FROM UserChatrooms u WHERE u.userChatroomsPK.chatRole = :chatRole")})
public class UserChatrooms implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserChatroomsPK userChatroomsPK;
    @JoinColumn(name = "CHATROOM_ID", referencedColumnName = "CHATROOM_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Chatrooms chatrooms;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public UserChatrooms() {
    }

    public UserChatrooms(UserChatroomsPK userChatroomsPK) {
        this.userChatroomsPK = userChatroomsPK;
    }

    public UserChatrooms(String userId, String chatroomId, String chatRole) {
        this.userChatroomsPK = new UserChatroomsPK(userId, chatroomId, chatRole);
    }

    public UserChatroomsPK getUserChatroomsPK() {
        return userChatroomsPK;
    }

    public void setUserChatroomsPK(UserChatroomsPK userChatroomsPK) {
        this.userChatroomsPK = userChatroomsPK;
    }

    public Chatrooms getChatrooms() {
        return chatrooms;
    }

    public void setChatrooms(Chatrooms chatrooms) {
        this.chatrooms = chatrooms;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userChatroomsPK != null ? userChatroomsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserChatrooms)) {
            return false;
        }
        UserChatrooms other = (UserChatrooms) object;
        if ((this.userChatroomsPK == null && other.userChatroomsPK != null) || (this.userChatroomsPK != null && !this.userChatroomsPK.equals(other.userChatroomsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.UserChatrooms[ userChatroomsPK=" + userChatroomsPK + " ]";
    }
    
}
