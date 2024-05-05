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
@Table(name = "RATINGS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ratings.findAll", query = "SELECT r FROM Ratings r"),
    @NamedQuery(name = "Ratings.findByRatingId", query = "SELECT r FROM Ratings r WHERE r.ratingId = :ratingId"),
    @NamedQuery(name = "Ratings.findByScore", query = "SELECT r FROM Ratings r WHERE r.score = :score"),
    @NamedQuery(name = "Ratings.findByComment", query = "SELECT r FROM Ratings r WHERE r.comment = :comment"),
    @NamedQuery(name = "Ratings.findByTimeRated", query = "SELECT r FROM Ratings r WHERE r.timeRated = :timeRated"),
    @NamedQuery(name = "Ratings.findByTotalVote", query = "SELECT r FROM Ratings r WHERE r.totalVote = :totalVote"),
    @NamedQuery(name = "Ratings.findRatingCountByProdId", query = "SELECT COUNT(r) FROM Ratings r WHERE r.productId = :productId"),
    @NamedQuery(name = "Ratings.findRatingCountByProdIdAndScore", query = "SELECT COUNT(r) FROM Ratings r WHERE r.productId = :productId AND r.score = :score "),
    @NamedQuery(name = "Ratings.findRatingByProdIdSortDescPriKey", query = "SELECT r FROM Ratings r WHERE r.productId = :productId ORDER BY r.ratingId DESC")})
public class Ratings implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "SCORE")
    private int score;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 555)
    @Column(name = "COMMENT")
    private String comment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIME_RATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeRated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL_VOTE")
    private int totalVote;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "RATING_ID")
    private String ratingId;
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    @ManyToOne(optional = false)
    private Product productId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private Users userId;

    public Ratings() {
    }

    public Ratings(String ratingId) {
        this.ratingId = ratingId;
    }

    public Ratings(String ratingId, int score, String comment, Date timeRated, int totalVote) {
        this.ratingId = ratingId;
        this.score = score;
        this.comment = comment;
        this.timeRated = timeRated;
        this.totalVote = totalVote;
    }

    public String getRatingId() {
        return ratingId;
    }

    public void setRatingId(String ratingId) {
        this.ratingId = ratingId;
    }


    public Date getTimeRated() {
        return timeRated;
    }

    public void setTimeRated(Date timeRated) {
        this.timeRated = timeRated;
    }

    public int getTotalVote() {
        return totalVote;
    }

    public void setTotalVote(int totalVote) {
        this.totalVote = totalVote;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
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
        hash += (ratingId != null ? ratingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ratings)) {
            return false;
        }
        Ratings other = (Ratings) object;
        if ((this.ratingId == null && other.ratingId != null) || (this.ratingId != null && !this.ratingId.equals(other.ratingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.Ratings[ ratingId=" + ratingId + " ]";
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

   


}
