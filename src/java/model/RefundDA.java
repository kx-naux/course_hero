package model;
import entity.Refund;
import java.sql.*;

public class RefundDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "REFUNDS";
    private Connection conn;
    private PreparedStatement stmt;
    private TransactionDA transactionDA;
    
    public RefundDA() throws SQLException{
        transactionDA = new TransactionDA();
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
    public void createRecord(Refund refund) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, refund.getRefundID());
        stmt.setString(2, refund.getTransactionID());
        stmt.setString(3, refund.getRefundType());
        stmt.setString(4, refund.getReason());
        
        stmt.executeUpdate();
    }
    
    public Refund retrieveRecord(String refundID) throws SQLException{
        Refund refund = null;
        String query = "SELECT * FROM " + tableName + " WHERE REFUND_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, refundID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            refund = new Refund(rs.getString("REFUND_ID"), transactionDA.retrieveRecord(rs.getString("TRANSACTION_ID")), rs.getString("REFUND_TYPE"), rs.getString("REASON"));
        }
        
        return refund;
    }
    
    public void updateRecord(Refund refund) throws SQLException{
        Refund refundExist = retrieveRecord(refund.getRefundID());
        if(refundExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET TRANSACTION_ID = ?, REFUND_TYPE = ?, REASON = ? WHERE REFUND_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, refund.getTransactionID());
            stmt.setString(2, refund.getRefundType());
            stmt.setString(3, refund.getReason());
            stmt.setString(4, refund.getRefundID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(Refund refund) throws SQLException{
        Refund refundExist = retrieveRecord(refund.getRefundID());
        if(refundExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE REFUND_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, refund.getRefundID());
            
            stmt.executeUpdate();
        }
    }
}