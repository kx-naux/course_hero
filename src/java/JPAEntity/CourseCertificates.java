/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPAEntity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "COURSE_CERTIFICATES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CourseCertificates.findAll", query = "SELECT c FROM CourseCertificates c"),
    @NamedQuery(name = "CourseCertificates.findByCertificateId", query = "SELECT c FROM CourseCertificates c WHERE c.certificateId = :certificateId"),
    @NamedQuery(name = "CourseCertificates.findByTimeIssued", query = "SELECT c FROM CourseCertificates c WHERE c.timeIssued = :timeIssued"),
    @NamedQuery(name = "CourseCertificates.findByCertificateNumber", query = "SELECT c FROM CourseCertificates c WHERE c.certificateNumber = :certificateNumber")})
public class CourseCertificates implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "TIME_ISSUED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeIssued;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "CERTIFICATE_NUMBER")
    private String certificateNumber;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "CERTIFICATE_ID")
    private String certificateId;
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "COURSE_ID")
    @ManyToOne(optional = false)
    private Courses courseId;
    @JoinColumn(name = "CERTTYPE_ID", referencedColumnName = "CERTTYPE_ID")
    @ManyToOne(optional = false)
    private CourseCertType certtypeId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private Users userId;

    public CourseCertificates() {
    }

    public CourseCertificates(String certificateId) {
        this.certificateId = certificateId;
    }

    public CourseCertificates(String certificateId, Date timeIssued, String certificateNumber) {
        this.certificateId = certificateId;
        this.timeIssued = timeIssued;
        this.certificateNumber = certificateNumber;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public Date getTimeIssued() {
        return timeIssued;
    }

    public void setTimeIssued(Date timeIssued) {
        this.timeIssued = timeIssued;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public Courses getCourseId() {
        return courseId;
    }

    public void setCourseId(Courses courseId) {
        this.courseId = courseId;
    }

    public CourseCertType getCerttypeId() {
        return certtypeId;
    }

    public void setCerttypeId(CourseCertType certtypeId) {
        this.certtypeId = certtypeId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (certificateId != null ? certificateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CourseCertificates)) {
            return false;
        }
        CourseCertificates other = (CourseCertificates) object;
        if ((this.certificateId == null && other.certificateId != null) || (this.certificateId != null && !this.certificateId.equals(other.certificateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.CourseCertificates[ certificateId=" + certificateId + " ]";
    }



}
