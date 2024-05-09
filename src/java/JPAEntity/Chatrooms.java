/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPAEntity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "CHATROOMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chatrooms.findAll", query = "SELECT c FROM Chatrooms c"),
    @NamedQuery(name = "Chatrooms.findByChatroomId", query = "SELECT c FROM Chatrooms c WHERE c.chatroomId = :chatroomId"),
    @NamedQuery(name = "Chatrooms.findByChatroomType", query = "SELECT c FROM Chatrooms c WHERE c.chatroomType = :chatroomType"),
    @NamedQuery(name = "Chatrooms.findByStatus", query = "SELECT c FROM Chatrooms c WHERE c.status = :status"),
    @NamedQuery(name = "Chatrooms.findByReqTitle", query = "SELECT c FROM Chatrooms c WHERE c.reqTitle = :reqTitle"),
    @NamedQuery(name = "Chatrooms.findByReqDescription", query = "SELECT c FROM Chatrooms c WHERE c.reqDescription = :reqDescription")})
public class Chatrooms implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CHATROOM_TYPE")
    private String chatroomType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "REQ_TITLE")
    private String reqTitle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "REQ_DESCRIPTION")
    private String reqDescription;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "CHATROOM_ID")
    private String chatroomId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chatroomId")
    private List<Messages> messagesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chatrooms")
    private List<UserChatrooms> userChatroomsList;

    public Chatrooms() {
    }

    public Chatrooms(String chatroomId) {
        this.chatroomId = chatroomId;
    }

    public Chatrooms(String chatroomId, String chatroomType, String status, String reqTitle, String reqDescription) {
        this.chatroomId = chatroomId;
        this.chatroomType = chatroomType;
        this.status = status;
        this.reqTitle = reqTitle;
        this.reqDescription = reqDescription;
    }

    public String getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(String chatroomId) {
        this.chatroomId = chatroomId;
    }
    
    public void setChatroomId(long count) {
        this.chatroomId = String.format("CH%07d", count);
    }   

    public String getChatroomType() {
        return chatroomType;
    }

    public void setChatroomType(String chatroomType) {
        this.chatroomType = chatroomType;
    }


    public String getReqTitle() {
        return reqTitle;
    }

    public void setReqTitle(String reqTitle) {
        this.reqTitle = reqTitle;
    }

    public String getReqDescription() {
        return reqDescription;
    }

    public void setReqDescription(String reqDescription) {
        this.reqDescription = reqDescription;
    }

    @XmlTransient
    public List<Messages> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(List<Messages> messagesList) {
        this.messagesList = messagesList;
    }

    @XmlTransient
    public List<UserChatrooms> getUserChatroomsList() {
        return userChatroomsList;
    }

    public void setUserChatroomsList(List<UserChatrooms> userChatroomsList) {
        this.userChatroomsList = userChatroomsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chatroomId != null ? chatroomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chatrooms)) {
            return false;
        }
        Chatrooms other = (Chatrooms) object;
        if ((this.chatroomId == null && other.chatroomId != null) || (this.chatroomId != null && !this.chatroomId.equals(other.chatroomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.Chatrooms[ chatroomId=" + chatroomId + " ]";
    }


    


 


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    } 


    
}
