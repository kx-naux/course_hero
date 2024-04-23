package model;
import entity.Transaction;
import java.sql.*;
import java.time.*;

public class TransactionDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "TRANSACTIONS";
    private Connection conn;
    private PreparedStatement stmt;
    private UserDA userDA;
    private PromotionDA promotionDA;
    private ShippingDA shippingDA;
    
    public TransactionDA() throws SQLException{
        userDA = new UserDA();
        promotionDA = new PromotionDA();
        shippingDA = new ShippingDA();
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
    public void createRecord(Transaction transaction) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
            stmt.setString(1, transaction.getTransactionID());
            stmt.setString(2, transaction.getUserID());
            stmt.setString(3, String.valueOf(transaction.getSubTotal()));
            stmt.setString(4, transaction.getPromotionID());
            stmt.setString(5, String.valueOf(transaction.getPromoAmount()));
            stmt.setString(6, String.valueOf(transaction.getTax()));
            stmt.setString(7, transaction.getTransactionType());
            stmt.setString(8, String.valueOf(transaction.getTransactionFee()));
            stmt.setString(9, String.valueOf(transaction.getTotal()));
            stmt.setString(10, transaction.getStatus());
            stmt.setString(11, transaction.getShippingID());
            stmt.setString(12, transaction.getTimeAdded().toString());
        
        stmt.executeUpdate();
    }
    
    public Transaction retrieveRecord(String transactionID) throws SQLException{
        Transaction transaction = null;
        String query = "SELECT * FROM " + tableName + " WHERE TRANSACTION_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, transactionID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            transaction = new Transaction(rs.getString("TRANSACTION_ID"), userDA.retrieveRecord(rs.getString("USER_ID")), Double.parseDouble(rs.getString("SUBTOTAL")), promotionDA.retrieveRecord(rs.getString("PROMOTION_ID")), Double.parseDouble(rs.getString("PROMO_AMOUNT")), Double.parseDouble(rs.getString("TAX")), rs.getString("TRANSACTION_TYPE"), Double.parseDouble(rs.getString("TRANSACTION_FEE")), Double.parseDouble(rs.getString("TOTAL")), rs.getString("STATUS"), shippingDA.retrieveRecord(rs.getString("SHIPPING_ID")), LocalDateTime.parse(rs.getString("TIME_ADDED")));
        }
        
        return transaction;
    }
    
    public void updateRecord(Transaction transaction) throws SQLException{
        Transaction transactionExist = retrieveRecord(transaction.getTransactionID());
        if(transactionExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET USER_ID = ?, SUBTOTAL = ?, PROMOTION_ID = ?, PROMO_AMOUNT = ?, TAX= ?, TRANSACTION_TYPE = ?, TRANSACTION_FEE = ?, TOTAL = ?, STATUS = ?, SHIPPING_ID = ?, TIME_ADDED = ? WHERE TRANSACTION_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, transaction.getUserID());
            stmt.setString(2, String.valueOf(transaction.getSubTotal()));
            stmt.setString(3, transaction.getPromotionID());
            stmt.setString(4, String.valueOf(transaction.getPromoAmount()));
            stmt.setString(5, String.valueOf(transaction.getTax()));
            stmt.setString(6, transaction.getTransactionType());
            stmt.setString(7, String.valueOf(transaction.getTransactionFee()));
            stmt.setString(8, String.valueOf(transaction.getTotal()));
            stmt.setString(9, transaction.getStatus());
            stmt.setString(10, transaction.getShippingID());
            stmt.setString(11, transaction.getTimeAdded().toString());
            stmt.setString(12, transaction.getTransactionID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(Transaction transaction) throws SQLException{
        Transaction transactionExist = retrieveRecord(transaction.getTransactionID());
        if(transactionExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE TRANSACTION_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, transaction.getTransactionID());
            
            stmt.executeUpdate();
        }
    }

}
