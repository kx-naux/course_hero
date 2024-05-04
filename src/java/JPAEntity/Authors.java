/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPAEntity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "AUTHORS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Authors.findAll", query = "SELECT a FROM Authors a"),
    @NamedQuery(name = "Authors.findByAuthorId", query = "SELECT a FROM Authors a WHERE a.authorId = :authorId"),
    @NamedQuery(name = "Authors.findByAuthorName", query = "SELECT a FROM Authors a WHERE a.authorName = :authorName"),
    @NamedQuery(name = "Authors.findByNationality", query = "SELECT a FROM Authors a WHERE a.nationality = :nationality"),
    @NamedQuery(name = "Authors.findByInstitution", query = "SELECT a FROM Authors a WHERE a.institution = :institution"),
    @NamedQuery(name = "Authors.findByAwardsHonors", query = "SELECT a FROM Authors a WHERE a.awardsHonors = :awardsHonors"),
    @NamedQuery(name = "Authors.findByBiography", query = "SELECT a FROM Authors a WHERE a.biography = :biography"),
    @NamedQuery(name = "Authors.findByDateJoined", query = "SELECT a FROM Authors a WHERE a.dateJoined = :dateJoined"),
    @NamedQuery(name = "Authors.findByAuthorPosition", query = "SELECT a FROM Authors a WHERE a.authorPosition = :authorPosition"),
    @NamedQuery(name = "Authors.findByWebsite", query = "SELECT a FROM Authors a WHERE a.website = :website")})
public class Authors implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "AUTHOR_NAME")
    private String authorName;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 30)
    @Column(name = "NATIONALITY")
    private String nationality;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "INSTITUTION")
    private String institution;
    @Size(max = 255)
    @Column(name = "AWARDS_HONORS")
    private String awardsHonors;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "BIOGRAPHY")
    private String biography;
    @Basic(optional = false)
    @NotNull()
    @Column(name = "DATE_JOINED")
    
    @Temporal(TemporalType.DATE)
    private Date dateJoined;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "AUTHOR_POSITION")
    private String authorPosition;
    @Size(max = 255)
    @Column(name = "WEBSITE")
    private String website;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "AUTHOR_ID")
    private String authorId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private List<AuthorContribution> authorContributionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private List<SocialMediaLinks> socialMediaLinksList;

    public Authors() {
    }

    public Authors(String authorId) {
        this.authorId = authorId;
    }

    public Authors(String authorId, String authorName, String nationality, String institution, String biography, Date dateJoined, String authorPosition) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.nationality = nationality;
        this.institution = institution;
        this.biography = biography;
        this.dateJoined = dateJoined;
        this.authorPosition = authorPosition;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    public String getAwardsHonors() {
        return awardsHonors;
    }

    public void setAwardsHonors(String awardsHonors) {
        this.awardsHonors = awardsHonors;
    }


    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getAuthorPosition() {
        return authorPosition;
    }

    public void setAuthorPosition(String authorPosition) {
        this.authorPosition = authorPosition;
    }


    @XmlTransient
    public List<AuthorContribution> getAuthorContributionList() {
        return authorContributionList;
    }

    public void setAuthorContributionList(List<AuthorContribution> authorContributionList) {
        this.authorContributionList = authorContributionList;
    }

    @XmlTransient
    public List<SocialMediaLinks> getSocialMediaLinksList() {
        return socialMediaLinksList;
    }

    public void setSocialMediaLinksList(List<SocialMediaLinks> socialMediaLinksList) {
        this.socialMediaLinksList = socialMediaLinksList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (authorId != null ? authorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Authors)) {
            return false;
        }
        Authors other = (Authors) object;
        if ((this.authorId == null && other.authorId != null) || (this.authorId != null && !this.authorId.equals(other.authorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.Authors[ authorId=" + authorId + " ]";
    }
    
    
    
    

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
    
}
