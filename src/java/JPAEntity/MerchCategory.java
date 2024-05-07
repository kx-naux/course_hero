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
@Table(name = "MERCH_CATEGORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MerchCategory.findAll", query = "SELECT m FROM MerchCategory m"),
    @NamedQuery(name = "MerchCategory.findByMerchcatId", query = "SELECT m FROM MerchCategory m WHERE m.merchcatId = :merchcatId"),
    @NamedQuery(name = "MerchCategory.findByCategoryName", query = "SELECT m FROM MerchCategory m WHERE m.categoryName = :categoryName"),
    @NamedQuery(name = "MerchCategory.findByDescription", query = "SELECT m FROM MerchCategory m WHERE m.description = :description")})
public class MerchCategory implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CATEGORY_NAME")
    private String categoryName;
    @Size(max = 100)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 100)
    @Column(name = "IMG_URL")
    private String imgUrl;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "merchcatId")
    private List<Merchandise> merchandiseList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "MERCHCAT_ID")
    private String merchcatId;

    public MerchCategory() {
    }

    public MerchCategory(String merchcatId) {
        this.merchcatId = merchcatId;
    }

    public MerchCategory(String merchcatId, String categoryName) {
        this.merchcatId = merchcatId;
        this.categoryName = categoryName;
    }

    public String getMerchcatId() {
        return merchcatId;
    }

    public void setMerchcatId(String merchcatId) {
        this.merchcatId = merchcatId;
    }
    
    public void setMerchcatId(long count) {
        this.merchcatId = String.format("MC%07d", count);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (merchcatId != null ? merchcatId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MerchCategory)) {
            return false;
        }
        MerchCategory other = (MerchCategory) object;
        if ((this.merchcatId == null && other.merchcatId != null) || (this.merchcatId != null && !this.merchcatId.equals(other.merchcatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.MerchCategory[ merchcatId=" + merchcatId + " ]";
    }


    @XmlTransient
    public List<Merchandise> getMerchandiseList() {
        return merchandiseList;
    }

    public void setMerchandiseList(List<Merchandise> merchandiseList) {
        this.merchandiseList = merchandiseList;
    }



    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }




    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   
}
