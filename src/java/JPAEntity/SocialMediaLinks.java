/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPAEntity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "SOCIAL_MEDIA_LINKS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SocialMediaLinks.findAll", query = "SELECT s FROM SocialMediaLinks s"),
    @NamedQuery(name = "SocialMediaLinks.findBySocialmedialinkId", query = "SELECT s FROM SocialMediaLinks s WHERE s.socialmedialinkId = :socialmedialinkId"),
    @NamedQuery(name = "SocialMediaLinks.findBySocialmediaName", query = "SELECT s FROM SocialMediaLinks s WHERE s.socialmediaName = :socialmediaName"),
    @NamedQuery(name = "SocialMediaLinks.findByDestLink", query = "SELECT s FROM SocialMediaLinks s WHERE s.destLink = :destLink")})
public class SocialMediaLinks implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "SOCIALMEDIA_NAME")
    private String socialmediaName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DEST_LINK")
    private String destLink;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "SOCIALMEDIALINK_ID")
    private String socialmedialinkId;
    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "AUTHOR_ID")
    @ManyToOne(optional = false)
    private Authors authorId;

    public SocialMediaLinks() {
    }

    public SocialMediaLinks(String socialmedialinkId) {
        this.socialmedialinkId = socialmedialinkId;
    }

    public SocialMediaLinks(String socialmedialinkId, String socialmediaName, String destLink) {
        this.socialmedialinkId = socialmedialinkId;
        this.socialmediaName = socialmediaName;
        this.destLink = destLink;
    }

    public String getSocialmedialinkId() {
        return socialmedialinkId;
    }

    public void setSocialmedialinkId(String socialmedialinkId) {
        this.socialmedialinkId = socialmedialinkId;
    }

    public String getSocialmediaName() {
        return socialmediaName;
    }

    public void setSocialmediaName(String socialmediaName) {
        this.socialmediaName = socialmediaName;
    }

    public String getDestLink() {
        return destLink;
    }

    public void setDestLink(String destLink) {
        this.destLink = destLink;
    }

    public Authors getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Authors authorId) {
        this.authorId = authorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (socialmedialinkId != null ? socialmedialinkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SocialMediaLinks)) {
            return false;
        }
        SocialMediaLinks other = (SocialMediaLinks) object;
        if ((this.socialmedialinkId == null && other.socialmedialinkId != null) || (this.socialmedialinkId != null && !this.socialmedialinkId.equals(other.socialmedialinkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.SocialMediaLinks[ socialmedialinkId=" + socialmedialinkId + " ]";
    }
    
    
}
