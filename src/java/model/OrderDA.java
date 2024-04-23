package model;
import entity.Order;
import java.sql.*;

public class OrderDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "ORDERS";
    private Connection conn;
    private PreparedStatement stmt;
    private TransactionDA transactionDA;
    private ProductDA productDA;
    
    public OrderDA() throws SQLException{
        transactionDA = new TransactionDA();
        productDA = new ProductDA();
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
    public void createRecord(Order order) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, order.getTransactionID());
        stmt.setString(2, order.getProductID());
        stmt.setString(3, String.valueOf(order.getQuantity()));
        
        stmt.executeUpdate();
    }
    
    public Order retrieveRecord(String transactionID) throws SQLException{
        Order order = null;
        String query = "SELECT * FROM " + tableName + " WHERE TRANSACTION_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, transactionID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            order = new Order(transactionDA.retrieveRecord(rs.getString("TRANSACTION_ID")), productDA.retrieveRecord(rs.getString("PRODUCT_ID")),Integer.parseInt("QUANTITY"));
        }
        
        return order;
    }
    
    public void updateRecord(Order order) throws SQLException{
        Order orderExist = retrieveRecord(order.getTransactionID());
        if(orderExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET PRODUCT_ID = ?, QUANTITY = ? WHERE TRANSACTION_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, order.getProductID());
            stmt.setString(2, String.valueOf(order.getQuantity()));
            stmt.setString(3, order.getTransactionID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(Order order) throws SQLException{
        Order orderExist = retrieveRecord(order.getTransactionID());
        if(orderExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE TRANSACTION_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, order.getTransactionID());
            
            stmt.executeUpdate();
        }
    }
    
}
