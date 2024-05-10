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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "COURSES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Courses.findAll", query = "SELECT c FROM Courses c"),
    @NamedQuery(name = "Courses.countByTwoLengthHour",query = "SELECT COUNT(c) FROM Courses c WHERE c.lengthHour >= :minLengthHour AND c.lengthHour <= :maxLengthHour"),
    @NamedQuery(name = "Courses.countByCourseLevel",query = "SELECT COUNT(c) FROM Courses c WHERE c.courseLevel = :courseLevel"),
    @NamedQuery(name = "Courses.countByCategoryId",query = "SELECT COUNT(c) FROM Courses c WHERE c.coursecatId = :coursecatId"),
    @NamedQuery(name = "Courses.findByCourseId", query = "SELECT c FROM Courses c WHERE c.courseId = :courseId"),
    @NamedQuery(name = "Courses.findBySyllabus", query = "SELECT c FROM Courses c WHERE c.syllabus = :syllabus"),
    @NamedQuery(name = "Courses.findByLearningObj", query = "SELECT c FROM Courses c WHERE c.learningObj = :learningObj"),
    @NamedQuery(name = "Courses.findByLengthHour", query = "SELECT c FROM Courses c WHERE c.lengthHour = :lengthHour"),
    @NamedQuery(name = "Courses.findByCourseLevel", query = "SELECT c FROM Courses c WHERE c.courseLevel = :courseLevel"),
    @NamedQuery(name = "Courses.findByDateAdded", query = "SELECT c FROM Courses c WHERE c.dateAdded = :dateAdded"),
    @NamedQuery(name = "Courses.findByCategoryId", query = "SELECT c FROM Courses c WHERE c.coursecatId = :coursecatId"),
    @NamedQuery(name = "Courses.findByProductId", query = "SELECT c FROM Courses c WHERE c.productId = :productId")
})
public class Courses implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SYLLABUS")
    private String syllabus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "LEARNING_OBJ")
    private String learningObj;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LENGTH_HOUR")
    private double lengthHour;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "COURSE_LEVEL")
    private String courseLevel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "REQUIREMENTS")
    private String requirements;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "DETAILED_DESC")
    private String detailedDesc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_ADDED")
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    @Lob
    @Column(name = "VIDEO_DATA")
    private Serializable videoData;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private List<CourseSubscriptions> courseSubscriptionsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private List<CourseCertificates> courseCertificatesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private List<CourseCertType> courseCertTypeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private List<AuthorContribution> authorContributionList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "COURSE_ID")
    private String courseId;
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

    public Courses(String courseId, String syllabus, String learningObj, double lengthHour, String courseLevel, Date dateAdded) {
        this.courseId = courseId;
        this.syllabus = syllabus;
        this.learningObj = learningObj;
        this.lengthHour = lengthHour;
        this.courseLevel = courseLevel;
        this.dateAdded = dateAdded;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCourseId(long count) {
        this.courseId = String.format("CR%07d", count);
    }

    public String getLearningObj() {
        return learningObj;
    }

    public void setLearningObj(String learningObj) {
        this.learningObj = learningObj;
    }

    public double getLengthHour() {
        return lengthHour;
    }

    public void setLengthHour(double lengthHour) {
        this.lengthHour = lengthHour;
    }

    public String getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
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


    public String getDetailedDesc() {
        return detailedDesc;
    }

    public void setDetailedDesc(String detailedDesc) {
        this.detailedDesc = detailedDesc;
    }

    public Serializable getVideoData() {
        return videoData;
    }

    public void setVideoData(Serializable videoData) {
        this.videoData = videoData;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

   

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }


}
