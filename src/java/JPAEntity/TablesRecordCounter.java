/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JPAEntity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "TABLES_RECORD_COUNTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TablesRecordCounter.findAll", query = "SELECT t FROM TablesRecordCounter t"),
    @NamedQuery(name = "TablesRecordCounter.findByTbNbame", query = "SELECT t FROM TablesRecordCounter t WHERE t.tbNbame = :tbNbame"),
    @NamedQuery(name = "TablesRecordCounter.findByCounter", query = "SELECT t FROM TablesRecordCounter t WHERE t.counter = :counter")})
public class TablesRecordCounter implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "COUNTER")
    private long counter;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TB_NBAME")
    private String tbNbame;

    public TablesRecordCounter() {
    }

    public TablesRecordCounter(String tbNbame) {
        this.tbNbame = tbNbame;
    }

    public TablesRecordCounter(String tbNbame, long counter) {
        this.tbNbame = tbNbame;
        this.counter = counter;
    }

    public String getTbNbame() {
        return tbNbame;
    }

    public void setTbNbame(String tbNbame) {
        this.tbNbame = tbNbame;
    }

    
    public void counterIncrement(long increment){
        this.counter = this.counter + increment;
    }
    
    public void counterIncrementByOne(){
        this.counter = this.counter + 1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tbNbame != null ? tbNbame.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TablesRecordCounter)) {
            return false;
        }
        TablesRecordCounter other = (TablesRecordCounter) object;
        if ((this.tbNbame == null && other.tbNbame != null) || (this.tbNbame != null && !this.tbNbame.equals(other.tbNbame))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JPAEntity.TablesRecordCounter[ tbNbame=" + tbNbame + " ]";
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }
    
}
