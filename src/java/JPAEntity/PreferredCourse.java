/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPAEntity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "PREFERRED_COURSE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreferredCourse.findAll", query = "SELECT p FROM PreferredCourse p"),
    @NamedQuery(name = "PreferredCourse.findByUserId", query = "SELECT p FROM PreferredCourse p WHERE p.preferredCoursePK.userId = :userId"),
    @NamedQuery(name = "PreferredCourse.findByCoursecatId", query = "SELECT p FROM PreferredCourse p WHERE p.preferredCoursePK.coursecatId = :coursecatId"),
    @NamedQuery(name = "PreferredCourse.findByTimeAdded", query = "SELECT p FROM PreferredCourse p WHERE p.timeAdded = :timeAdded")})
public class PreferredCourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PreferredCoursePK preferredCoursePK;
    @Column(name = "TIME_ADDED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeAdded;
    @JoinColumn(name = "COURSECAT_ID", referencedColumnName = "COURSECAT_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CourseCategory courseCategory;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public PreferredCourse() {
    }

    public PreferredCourse(PreferredCoursePK preferredCoursePK) {
        this.preferredCoursePK = preferredCoursePK;
    }

    public PreferredCourse(String userId, String coursecatId) {
        this.preferredCoursePK = new PreferredCoursePK(userId, coursecatId);
    }

    public PreferredCoursePK getPreferredCoursePK() {
        return preferredCoursePK;
    }

    public void setPreferredCoursePK(PreferredCoursePK preferredCoursePK) {
        this.preferredCoursePK = preferredCoursePK;
    }

    public Date getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Date timeAdded) {
        this.timeAdded = timeAdded;
    }

    public CourseCategory getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(CourseCategory courseCategory) {
        this.courseCategory = courseCategory;
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
        hash += (preferredCoursePK != null ? preferredCoursePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreferredCourse)) {
            return false;
        }
        PreferredCourse other = (PreferredCourse) object;
        if ((this.preferredCoursePK == null && other.preferredCoursePK != null) || (this.preferredCoursePK != null && !this.preferredCoursePK.equals(other.preferredCoursePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.PreferredCourse[ preferredCoursePK=" + preferredCoursePK + " ]";
    }
    
}
