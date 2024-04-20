/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.BankCardInfo;
import java.sql.*;

/**
 *
 * @author Woo Yu Beng
 */

public class BankCardInfoDA {
    private final String host = "jdbc:derby://localhost:1527/OnlineCourseAppDB";
    private final String user = "nbuser";
    private final String password = "nbuser";
    private final String tableName = "BANKCARDINFO";
    private Connection conn;
    private PreparedStatement stmt;
    private UserDA userDA;
    
    public BankCardInfoDA() throws SQLException{
        userDA = new UserDA();
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
    public void createRecord(BankCardInfo bankCardInfo) throws SQLException{
        String query = "INSERT INTO " + tableName + " VALUES(?,?,?,?,?,?,?)";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, bankCardInfo.getCardInfoID());
        stmt.setString(2, bankCardInfo.getUserID());
        stmt.setString(3, bankCardInfo.getCardType());
        stmt.setString(4, bankCardInfo.getCardHolderName());
        stmt.setString(5, bankCardInfo.getCardNo());
        stmt.setString(6, bankCardInfo.getExpiryDate());
        stmt.setString(7, bankCardInfo.getCvv());
        
        stmt.executeUpdate();
    }
    
    public BankCardInfo retrieveRecord(String cardInfoID) throws SQLException{
        BankCardInfo bankCardInfo = null;
        String query = "SELECT * FROM " + tableName + " WHERE CARDINFOID = ?";
        stmt = conn.prepareStatement(query);
        
        stmt.setString(1, cardInfoID);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            bankCardInfo = new BankCardInfo(rs.getString("CARDINFOID"), userDA.retrieveRecord(rs.getString("USER_ID")), rs.getString("CARDTYPE"), rs.getString("CARD_HOLDER_NAME"), rs.getString("CARD_NO"), rs.getString("EXPIRY_DATE"), rs.getString("CVV"));
        }
        
        return bankCardInfo;
    }
    
    public void updateRecord(BankCardInfo bankCardInfo) throws SQLException{
        BankCardInfo bankCardInfoExist = retrieveRecord(bankCardInfo.getCardInfoID());
        if(bankCardInfoExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "UPDATE " + tableName + " SET USER_ID = ?, CARDTYPE = ?, CARD_HOLDER_NAME = ?, CARD_NO = ?, EXPIRY_DATE = ?, CVV = ? WHERE CARDINFOID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, bankCardInfo.getUserID());
            stmt.setString(2, bankCardInfo.getCardType());
            stmt.setString(3, bankCardInfo.getCardHolderName());
            stmt.setString(4, bankCardInfo.getCardNo());
            stmt.setString(5, bankCardInfo.getExpiryDate());
            stmt.setString(6, bankCardInfo.getCvv());
            stmt.setString(7, bankCardInfo.getCardInfoID());
            
            stmt.executeUpdate();
        }
    }
    
    public void deleteRecord(BankCardInfo bankCardInfo) throws SQLException{
        BankCardInfo bankCardInfoExist = retrieveRecord(bankCardInfo.getCardInfoID());
        if(bankCardInfoExist==null){
            throw new SQLException("Record Does Not Exist");
        }else{
            String query = "DELETE FROM " + tableName + " WHERE CARDINFOID = ?";
            stmt = conn.prepareStatement(query);
            
            stmt.setString(1, bankCardInfo.getCardInfoID());
            
            stmt.executeUpdate();
        }
    }
}