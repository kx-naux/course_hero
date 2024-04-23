package model;
import entity.Payment;
import java.sql.*;
import java.time.*;

public class PaymentDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "PAYMENTS";
    private Connection conn;
    private PreparedStatement stmt;
    private TransactionDA transactionDA;
    private PaymentMethodDA paymentMethodDA;
    
    public PaymentDA() throws SQLException{
        transactionDA = new TransactionDA();
        paymentMethodDA = new PaymentMethodDA();
        createConnection();
    }
      
    private void createConnection() throws SQLException{
        conn = DriverManager.getConnection(host, user, password);
    }
    
    public void shutDownConnection() throws SQLException{
        if (conn != null)
            conn.close();
    }
    
    //Create Retrieve Update Delete
    public void createRecord(Payment payment) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, payment.getPaymentID());
        stmt.setString(2, payment.getPaymentMethodID());
        stmt.setString(3, payment.getTransactionID());
        stmt.setString(4, payment.getAccountNumber());
        stmt.setString(5, payment.getPaymentGateway());
        stmt.setString(6, String.valueOf(payment.getAmount()));
        stmt.setString(7, payment.getStatus());
        stmt.setString(8, payment.getDateIssued().toString());
        
        stmt.executeUpdate();
    }
    
    public Payment retrieveRecord(String paymentID) throws SQLException{
        Payment payment = null;
        String query = "SELECT * FROM " + tableName + " WHERE PAYMENT_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, paymentID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            payment = new Payment(rs.getString("PAYMENT_ID"), paymentMethodDA.retrieveRecord(rs.getString("PAYMENT_METHOD_ID")),  transactionDA.retrieveRecord(rs.getString("TRANSACTION_ID")), rs.getString("ACCOUNT_NUMBER"), rs.getString("PAYMENT_GATEWAY"), Double.parseDouble(rs.getString("AMOUNT")), rs.getString("STATUS"), LocalDateTime.parse(rs.getString("DATE_ISSUED")));
        } 
        return payment;
    }
    
    public void updateRecord(Payment payment) throws SQLException{
        Payment paymentExist = retrieveRecord(payment.getPaymentID());
        if(paymentExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET PAYMENT_METHOD_ID = ?, TRANSACTION_ID = ?, ACCOUNT_NUMBER = ?, PAYMENT_GATEWAY = ?, AMOUNT = ?, STATUS = ?, DATE_ISSUED = ? WHERE PAYMENT_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, payment.getPaymentMethodID());
            stmt.setString(2, payment.getTransactionID());
            stmt.setString(3, payment.getAccountNumber());
            stmt.setString(4, payment.getPaymentGateway());
            stmt.setString(5, String.valueOf(payment.getAmount()));
            stmt.setString(6, payment.getStatus());
            stmt.setString(7, payment.getDateIssued().toString());
            stmt.setString(8, payment.getPaymentID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(Payment payment) throws SQLException{
        Payment paymentExist = retrieveRecord(payment.getPaymentID());
        if(paymentExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE PAYMENT_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, payment.getPaymentID());
            
            stmt.executeUpdate();
        }
    }
}
