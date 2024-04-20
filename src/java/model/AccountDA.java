/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.Account;
import java.sql.*;

/**
 *
 * @author Woo Yu Beng
 */

public class AccountDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "ACCOUNTS";
    private Connection conn;
    private PreparedStatement stmt;
    
    public AccountDA() throws SQLException{
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
    public void createRecord(Account account) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, account.getAccountID());
        stmt.setString(2, account.getEmail());
        stmt.setString(3, account.getSaltedPassword());
        stmt.setString(4, account.getSalt());
        
        stmt.executeUpdate();
    }
    
    public Account retrieveRecord(String accountID) throws SQLException{
        Account account = null;
        String query = "SELECT * FROM " + tableName + " WHERE ACCOUNT_ID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, accountID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            account = new Account(rs.getString("ACCOUNT_ID"), rs.getString("EMAIL"), rs.getString("SALTEDPASSWORD"), rs.getString("SALT"));
        }
                
        return account;
    }
    
    public void updateRecord(Account account) throws SQLException{
        Account accountExist = retrieveRecord(account.getAccountID());
        if(accountExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET EMAIL = ?, SALTEDPASSWORD = ?, SALT = ? WHERE ACCOUNT_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, account.getEmail());
            stmt.setString(2, account.getSaltedPassword());
            stmt.setString(3, account.getSalt());
            stmt.setString(4, account.getAccountID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(Account account) throws SQLException{
        Account accountExist = retrieveRecord(account.getAccountID());
        if(accountExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE ACCOUNT_ID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, account.getAccountID());
            
            stmt.executeUpdate();
        }
    }   
}
