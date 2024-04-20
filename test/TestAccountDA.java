/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Woo Yu Beng
 */

import java.sql.SQLException;
import model.AccountDA;
import entity.Account;
import java.time.LocalDateTime;

public class TestAccountDA {
    private AccountDA accDa;
    private final Account acc1 = new Account("123456789", "test@gmail.com", "1234567890123456789012345678901234567890123456789012345678901234", "Salt");
    private final Account acc2 = new Account("123456788", "test1@gmail.com", "1234567890123456789012345678901234567890123456789012345678901234", "Salt");
    private final Account acc3 = new Account("123456789", "test12345@gmail.com", "1234567890123456789012345678901234567890123456789012345678901234", "Salt");
    
    public TestAccountDA() throws SQLException{
        accDa = new AccountDA();
        
        //accDa.createRecord(acc1);
        
        //Account retrievedAccount = accDa.retrieveRecord(acc1.getAccountID());
        //System.out.println(retrievedAccount.getAccountID());
        
        //accDa.deleteRecord(acc1);
        
        //accDa.updateRecord(acc3);
        String dateTime = java.time.LocalDateTime.now().toString();
        System.out.println(dateTime);
        System.out.println(LocalDateTime.parse(dateTime));
    }

    public static void main(String[] args) throws SQLException{
        new TestAccountDA();
    }
    
}
