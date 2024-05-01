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
@Table(name = "COURSE_SUBSCRIPTIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CourseSubscriptions.findAll", query = "SELECT c FROM CourseSubscriptions c"),
    @NamedQuery(name = "CourseSubscriptions.findBySubscriptionsId", query = "SELECT c FROM CourseSubscriptions c WHERE c.subscriptionsId = :subscriptionsId"),
    @NamedQuery(name = "CourseSubscriptions.findBySubscribeTime", query = "SELECT c FROM CourseSubscriptions c WHERE c.subscribeTime = :subscribeTime"),
    @NamedQuery(name = "CourseSubscriptions.findByStartTime", query = "SELECT c FROM CourseSubscriptions c WHERE c.startTime = :startTime"),
    @NamedQuery(name = "CourseSubscriptions.findByProgress", query = "SELECT c FROM CourseSubscriptions c WHERE c.progress = :progress"),
    @NamedQuery(name = "CourseSubscriptions.findByFinishTime", query = "SELECT c FROM CourseSubscriptions c WHERE c.finishTime = :finishTime")})
public class CourseSubscriptions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "SUBSCRIPTIONS_ID")
    private String subscriptionsId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUBSCRIBE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date subscribeTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "START_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "PROGRESS")
    private String progress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FINISH_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishTime;
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "COURSE_ID")
    @ManyToOne(optional = false)
    private Courses courseId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private Users userId;

    public CourseSubscriptions() {
    }

    public CourseSubscriptions(String subscriptionsId) {
        this.subscriptionsId = subscriptionsId;
    }

    public CourseSubscriptions(String subscriptionsId, Date subscribeTime, Date startTime, String progress, Date finishTime) {
        this.subscriptionsId = subscriptionsId;
        this.subscribeTime = subscribeTime;
        this.startTime = startTime;
        this.progress = progress;
        this.finishTime = finishTime;
    }

    public String getSubscriptionsId() {
        return subscriptionsId;
    }

    public void setSubscriptionsId(String subscriptionsId) {
        this.subscriptionsId = subscriptionsId;
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Courses getCourseId() {
        return courseId;
    }

    public void setCourseId(Courses courseId) {
        this.courseId = courseId;
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
        hash += (subscriptionsId != null ? subscriptionsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CourseSubscriptions)) {
            return false;
        }
        CourseSubscriptions other = (CourseSubscriptions) object;
        if ((this.subscriptionsId == null && other.subscriptionsId != null) || (this.subscriptionsId != null && !this.subscriptionsId.equals(other.subscriptionsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.CourseSubscriptions[ subscriptionsId=" + subscriptionsId + " ]";
    }
    
}
