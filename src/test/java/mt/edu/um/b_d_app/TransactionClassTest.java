/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mt.edu.um.b_d_app;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Beatrix
 */
public class TransactionClassTest {
 
    //The test accounts declared for the Transaction classes tests
    Account testAccount1;
    Account testAccount2;
    
    //The test transaction instance
    Transaction testTrans1;
    
    //The account database
    AccountDatabase database;
    
    @Before
    public void setUp()
    {
        database = new AccountDatabase();
        
        database.addAccount(1, "tAcc1", 500);
        database.addAccount(5, "tAcc2", 500);
        
        testTrans1 = new Transaction(1,5,200,database);
    }
    
    
    
    //Testing the Transaction Class members as per the provided class diagram
    @Test
    public void sourceAccountNumberTest()
    {
        assertEquals(1,testTrans1.getSourceAccountNumber());
    }
    
    @Test
    public void destinationAccountNumberTest()
    {
        assertEquals(5, testTrans1.getDestinationAccountNumber());
    }
    
    @Test
    public void amountTest()
    {
        assertEquals(200, testTrans1.getAmount());
    }
    

    @Test
    public void processTest()
    {
        long source_balance = database.getAccount(1).getAccountBalance();
        long destination_balance = database.getAccount(5).getAccountBalance();
        assertEquals(true, testTrans1.process());
        assertEquals(source_balance-testTrans1.getAmount(),database.getAccount(1).getAccountBalance());
        assertEquals(destination_balance+testTrans1.getAmount(),database.getAccount(5).getAccountBalance());
    }
    
}
