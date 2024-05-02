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
@Table(name = "AUTHOR_CONTRIBUTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuthorContribution.findAll", query = "SELECT a FROM AuthorContribution a"),
    @NamedQuery(name = "AuthorContribution.findByContributionId", query = "SELECT a FROM AuthorContribution a WHERE a.contributionId = :contributionId"),
    @NamedQuery(name = "AuthorContribution.findByContribution", query = "SELECT a FROM AuthorContribution a WHERE a.contribution = :contribution"),
    @NamedQuery(name = "AuthorContribution.findByDescription", query = "SELECT a FROM AuthorContribution a WHERE a.description = :description"),
    @NamedQuery(name = "AuthorContribution.findByAuthorIdWhereCourseIsAvailable", query = "SELECT a FROM AuthorContribution a WHERE a.authorId = :authorId AND a.courseId.productId.status = 'Active' "),
    @NamedQuery(name = "AuthorContribution.countAllAvailabeCoursesByAuthor", query = "SELECT COUNT(a) FROM AuthorContribution a WHERE a.authorId = :authorId AND a.courseId.productId.status = 'Active'")})
public class AuthorContribution implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "CONTRIBUTION")
    private double contribution;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 355)
    @Column(name = "DESCRIPTION")
    private String description;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "CONTRIBUTION_ID")
    private String contributionId;
    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "AUTHOR_ID")
    @ManyToOne(optional = false)
    private Authors authorId;
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "COURSE_ID")
    @ManyToOne(optional = false)
    private Courses courseId;

    public AuthorContribution() {
    }

    public AuthorContribution(String contributionId) {
        this.contributionId = contributionId;
    }

    public AuthorContribution(String contributionId, double contribution, String description) {
        this.contributionId = contributionId;
        this.contribution = contribution;
        this.description = description;
    }

    public String getContributionId() {
        return contributionId;
    }

    public void setContributionId(String contributionId) {
        this.contributionId = contributionId;
    }


    public Authors getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Authors authorId) {
        this.authorId = authorId;
    }

    public Courses getCourseId() {
        return courseId;
    }

    public void setCourseId(Courses courseId) {
        this.courseId = courseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contributionId != null ? contributionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthorContribution)) {
            return false;
        }
        AuthorContribution other = (AuthorContribution) object;
        if ((this.contributionId == null && other.contributionId != null) || (this.contributionId != null && !this.contributionId.equals(other.contributionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.AuthorContribution[ contributionId=" + contributionId + " ]";
    }

    public double getContribution() {
        return contribution;
    }

    public void setContribution(double contribution) {
        this.contribution = contribution;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
