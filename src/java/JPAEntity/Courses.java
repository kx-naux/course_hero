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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "COURSES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Courses.findAll", query = "SELECT c FROM Courses c"),
    @NamedQuery(name = "Courses.findByCourseId", query = "SELECT c FROM Courses c WHERE c.courseId = :courseId"),
    @NamedQuery(name = "Courses.findByToolsUsed", query = "SELECT c FROM Courses c WHERE c.toolsUsed = :toolsUsed"),
    @NamedQuery(name = "Courses.findByLearningObj", query = "SELECT c FROM Courses c WHERE c.learningObj = :learningObj")})
public class Courses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "COURSE_ID")
    private String courseId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TOOLS_USED")
    private String toolsUsed;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "LEARNING_OBJ")
    private String learningObj;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private List<CourseSubscriptions> courseSubscriptionsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private List<CourseCertificates> courseCertificatesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private List<CourseCertType> courseCertTypeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private List<AuthorContribution> authorContributionList;
    @JoinColumn(name = "COURSECAT_ID", referencedColumnName = "COURSECAT_ID")
    @ManyToOne(optional = false)
    private CourseCategory coursecatId;
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    @ManyToOne(optional = false)
    private Product productId;

    public Courses() {
    }

    public Courses(String courseId) {
        this.courseId = courseId;
    }

    public Courses(String courseId, String toolsUsed, String learningObj) {
        this.courseId = courseId;
        this.toolsUsed = toolsUsed;
        this.learningObj = learningObj;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getToolsUsed() {
        return toolsUsed;
    }

    public void setToolsUsed(String toolsUsed) {
        this.toolsUsed = toolsUsed;
    }

    public String getLearningObj() {
        return learningObj;
    }

    public void setLearningObj(String learningObj) {
        this.learningObj = learningObj;
    }

    @XmlTransient
    public List<CourseSubscriptions> getCourseSubscriptionsList() {
        return courseSubscriptionsList;
    }

    public void setCourseSubscriptionsList(List<CourseSubscriptions> courseSubscriptionsList) {
        this.courseSubscriptionsList = courseSubscriptionsList;
    }

    @XmlTransient
    public List<CourseCertificates> getCourseCertificatesList() {
        return courseCertificatesList;
    }

    public void setCourseCertificatesList(List<CourseCertificates> courseCertificatesList) {
        this.courseCertificatesList = courseCertificatesList;
    }

    @XmlTransient
    public List<CourseCertType> getCourseCertTypeList() {
        return courseCertTypeList;
    }

    public void setCourseCertTypeList(List<CourseCertType> courseCertTypeList) {
        this.courseCertTypeList = courseCertTypeList;
    }

    @XmlTransient
    public List<AuthorContribution> getAuthorContributionList() {
        return authorContributionList;
    }

    public void setAuthorContributionList(List<AuthorContribution> authorContributionList) {
        this.authorContributionList = authorContributionList;
    }

    public CourseCategory getCoursecatId() {
        return coursecatId;
    }

    public void setCoursecatId(CourseCategory coursecatId) {
        this.coursecatId = coursecatId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseId != null ? courseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Courses)) {
            return false;
        }
        Courses other = (Courses) object;
        if ((this.courseId == null && other.courseId != null) || (this.courseId != null && !this.courseId.equals(other.courseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.Courses[ courseId=" + courseId + " ]";
    }
    
}
