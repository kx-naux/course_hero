package JPAEntity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NamedQueries({
    @NamedQuery(name = "SalesReport.findByDate",
                query = "SELECT t.transactionId, u.userId, p.prodName, " +
                        "o.quantity * p.price AS totalPrice, t.timeAdded " +
                        "FROM Transaction t " +
                        "JOIN Order o ON t.transactionId = o.transactionId " +
                        "JOIN Product p ON o.productId = p.productId " +
                        "JOIN User u ON t.userId = u.userId " +
                        "WHERE YEAR(t.timeAdded) = :year " +
                        "AND MOTNH(t.timeAdded) = :month " +
                        "AND DAY(t.timeAdded) = :day " +
                        "UNION ALL " +
                        "SELECT '', '', '', SUM(o.quantity * p.price) AS totalSales, " +
                        "'9999-12-31 23:59:59' AS timeAdded " +
                        "FROM Transaction t " +
                        "JOIN Order o ON t.transactionId = o.transactionId " +
                        "JOIN Product p ON o.productId = p.productId " +
                        "WHERE YEAR(t.timeAdded) = :year " +
                        "AND MONTH(t.timeAdded) = :month " +
                        "AND DAY(t.timeAdded) = :day")
})

public class SalesReport implements Serializable {
    @Id
    private String transactionId;
    private String userId;
    private String prodName;
    private double totalPrice;
    private LocalDateTime timeAdded;

    public SalesReport() {
    }

    public SalesReport(String transactionId, String userId, String prodName, double totalPrice, LocalDateTime timeAdded) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.prodName = prodName;
        this.totalPrice = totalPrice;
        this.timeAdded = timeAdded;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(LocalDateTime timeAdded) {
        this.timeAdded = timeAdded;
    }
}