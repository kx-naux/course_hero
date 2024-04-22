package model;
import entity.PaymentMethod;
import java.sql.*;

public class PaymentMethodDA  {
    private String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "PAYMENT_METHOD";
    private Connection conn;
    private PreparedStatement stmt;
    
    
    public PaymentMethodDA() throws SQLException{
        createConnection();
    }
    
    private void createConnection() throws SQLException{
            conn = DriverManager.getConnection(host, user, password);
            System.out.println("***TRACE: Connection established.");
    }  
        
    public void shutDownConnection() throws SQLException{
        if (conn != null)
            conn.close();
    }
    
    public PaymentMethod retrieveRecord(String paymentMethodID) throws SQLException {
        String queryStr = "SELECT * FROM " + tableName + " WHERE PAYMENT_METHOD_ID = ?";
        PaymentMethod paymentMethod = null;
    
        try {
            stmt = conn.prepareStatement(queryStr);
            stmt.setString(1, paymentMethodID);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                paymentMethod= new PaymentMethod(paymentMethodID, rs.getString("PAYMENT_NAME"), rs.getString("ACCEPTED_CURRENCIES"));
            
            }
        } catch (SQLException ex) {
            System.out.println("Error: PaymentMethod GetRecord");
        }
        return paymentMethod;
    }
    
    public void createRecord(PaymentMethod paymentMethod) throws SQLException{
        String insertStr = "Insert Into " + tableName + " values (?,?,?)";
                stmt = conn.prepareStatement(insertStr);
                stmt.setString(1,paymentMethod.getPaymentMethodID());
                stmt.setString(2,paymentMethod.getPaymentName());
                stmt.setString(3,paymentMethod.getAcceptedCurrencies());
                stmt.executeUpdate();                 
    }
    
    public void updateRecord(PaymentMethod paymentMethod) throws SQLException{
        PaymentMethod paymentMethodExist = retrieveRecord(paymentMethod.getPaymentMethodID());
            if(paymentMethodExist==null){
                throw new SQLException("Record Does Not Exist");
            }else{
        String updateStr = " UPDATE " + tableName + " SET PAYMENT_NAME=?,ACCEPTED_CURRENCIES=? WHERE PAYMENT_METHOD_ID=? ";
            stmt = conn.prepareStatement(updateStr);
            stmt.setString(1,paymentMethod.getPaymentName());
            stmt.setString(2,paymentMethod.getAcceptedCurrencies());
            stmt.setString(3,paymentMethod.getPaymentMethodID());
            stmt.executeUpdate();
            }   
    }
        
    public void deleteRecord(PaymentMethod paymentMethod) throws SQLException{
        PaymentMethod paymentMethodExist = retrieveRecord(paymentMethod.getPaymentMethodID());
        if(paymentMethodExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String deleteStr = " DELETE FROM " + tableName + " WHERE PAYMENT_METHOD_ID=? ";
            stmt = conn.prepareStatement(deleteStr);
            stmt.setString(1,paymentMethod.getPaymentMethodID());
            stmt.executeUpdate();   
        }
    } 
}
