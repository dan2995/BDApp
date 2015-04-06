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
public class TransactionManagerTest {
    
    //The test accounts declared for the Transaction classes tests
    Account testAccount1;
    Account testAccount2;
    
    //The test transaction instance
    Transaction testTrans1;
    Transaction testTrans2;
    
    //The test Transaction Manager
    TransactionManager testTManager;
    
    @Before
    public void setUp()
    {
        testAccount1 = new Account(1,"tAcc1",0);
        testAccount2 = new Account(5,"tAcc2",0);
        testTrans1 = new Transaction(1,5,200);
        testTrans2 = new Transaction(5,1,100);
        testTManager = new TransactionManager();
        
    }
    
    @Test
    public void numTransactionsProcessedTest()
    {
        assertEquals(0,testTManager.getNumberTransactionsProcessed());
        testTManager.processTransaction(testTrans1);
        assertEquals(1,testTManager.getNumberTransactionsProcessed());
    }
    
    @Test
    public void processTransactionTest()
    {
        long originalBalance = testAccount2.getAccountBalance();
        assertEquals(true, testTManager.processTransaction(testTrans1));
        assertEquals(originalBalance+200,testAccount2.getAccountBalance());
    }
    
    
}
