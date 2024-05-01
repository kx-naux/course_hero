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
@Table(name = "COURSE_CERT_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CourseCertType.findAll", query = "SELECT c FROM CourseCertType c"),
    @NamedQuery(name = "CourseCertType.findByCerttypeId", query = "SELECT c FROM CourseCertType c WHERE c.certtypeId = :certtypeId"),
    @NamedQuery(name = "CourseCertType.findByDescription", query = "SELECT c FROM CourseCertType c WHERE c.description = :description"),
    @NamedQuery(name = "CourseCertType.findByIssuingAuthority", query = "SELECT c FROM CourseCertType c WHERE c.issuingAuthority = :issuingAuthority"),
    @NamedQuery(name = "CourseCertType.findByCertLevel", query = "SELECT c FROM CourseCertType c WHERE c.certLevel = :certLevel")})
public class CourseCertType implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "ISSUING_AUTHORITY")
    private String issuingAuthority;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CERT_LEVEL")
    private String certLevel;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "CERTTYPE_ID")
    private String certtypeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "certtypeId")
    private List<CourseCertificates> courseCertificatesList;
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "COURSE_ID")
    @ManyToOne(optional = false)
    private Courses courseId;

    public CourseCertType() {
    }

    public CourseCertType(String certtypeId) {
        this.certtypeId = certtypeId;
    }

    public CourseCertType(String certtypeId, String description, String issuingAuthority, String certLevel) {
        this.certtypeId = certtypeId;
        this.description = description;
        this.issuingAuthority = issuingAuthority;
        this.certLevel = certLevel;
    }

    public String getCerttypeId() {
        return certtypeId;
    }

    public void setCerttypeId(String certtypeId) {
        this.certtypeId = certtypeId;
    }


    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }

    public String getCertLevel() {
        return certLevel;
    }

    public void setCertLevel(String certLevel) {
        this.certLevel = certLevel;
    }

    @XmlTransient
    public List<CourseCertificates> getCourseCertificatesList() {
        return courseCertificatesList;
    }

    public void setCourseCertificatesList(List<CourseCertificates> courseCertificatesList) {
        this.courseCertificatesList = courseCertificatesList;
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
        hash += (certtypeId != null ? certtypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CourseCertType)) {
            return false;
        }
        CourseCertType other = (CourseCertType) object;
        if ((this.certtypeId == null && other.certtypeId != null) || (this.certtypeId != null && !this.certtypeId.equals(other.certtypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.CourseCertType[ certtypeId=" + certtypeId + " ]";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

   
    
}
