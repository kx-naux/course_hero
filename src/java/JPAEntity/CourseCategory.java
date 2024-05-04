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
import javax.persistence.ManyToMany;
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
@Table(name = "COURSE_CATEGORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CourseCategory.findAll", query = "SELECT c FROM CourseCategory c"),
    @NamedQuery(name = "CourseCategory.findByCoursecatId", query = "SELECT c FROM CourseCategory c WHERE c.coursecatId = :coursecatId"),
    @NamedQuery(name = "CourseCategory.findByCategoryName", query = "SELECT c FROM CourseCategory c WHERE c.categoryName = :categoryName"),
    @NamedQuery(name = "CourseCategory.findByDescription", query = "SELECT c FROM CourseCategory c WHERE c.description = :description")})
public class CourseCategory implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CATEGORY_NAME")
    private String categoryName;
    @Size(max = 100)
    @Column(name = "DESCRIPTION")
    private String description;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "COURSECAT_ID")
    private String coursecatId;
    @ManyToMany(mappedBy = "courseCategoryList")
    private List<Users> usersList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coursecatId")
    private List<Courses> coursesList;

    public CourseCategory() {
    }

    public CourseCategory(String coursecatId) {
        this.coursecatId = coursecatId;
    }

    public CourseCategory(String coursecatId, String categoryName) {
        this.coursecatId = coursecatId;
        this.categoryName = categoryName;
    }

    public String getCoursecatId() {
        return coursecatId;
    }

    public void setCoursecatId(String coursecatId) {
        this.coursecatId = coursecatId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    @XmlTransient
    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    @XmlTransient
    public List<Courses> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Courses> coursesList) {
        this.coursesList = coursesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coursecatId != null ? coursecatId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CourseCategory)) {
            return false;
        }
        CourseCategory other = (CourseCategory) object;
        if ((this.coursecatId == null && other.coursecatId != null) || (this.coursecatId != null && !this.coursecatId.equals(other.coursecatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.CourseCategory[ coursecatId=" + coursecatId + " ]";
    }

  

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
