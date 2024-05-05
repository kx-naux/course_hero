/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPAEntity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Embeddable
public class PreferredCoursePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "USER_ID")
    private String userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "COURSECAT_ID")
    private String coursecatId;

    public PreferredCoursePK() {
    }

    public PreferredCoursePK(String userId, String coursecatId) {
        this.userId = userId;
        this.coursecatId = coursecatId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCoursecatId() {
        return coursecatId;
    }

    public void setCoursecatId(String coursecatId) {
        this.coursecatId = coursecatId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        hash += (coursecatId != null ? coursecatId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreferredCoursePK)) {
            return false;
        }
        PreferredCoursePK other = (PreferredCoursePK) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        if ((this.coursecatId == null && other.coursecatId != null) || (this.coursecatId != null && !this.coursecatId.equals(other.coursecatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.PreferredCoursePK[ userId=" + userId + ", coursecatId=" + coursecatId + " ]";
    }
    
}
