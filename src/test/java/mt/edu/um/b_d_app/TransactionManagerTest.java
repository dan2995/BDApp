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
    
    //The test accounts declared for the AtomicTransaction classes tests
    //Account testAccount1;
    //Account testAccount2;
    
    //The test transaction instance
    AtomicTransaction testTrans1;
    AtomicTransaction testTrans2;
    
    //The test AtomicTransaction Manager
    TransactionManager testTManager;
    
    AccountDatabase database;
    
    @Before
    public void setUp()
    {
        //testAccount1 = new Account(1,"tAcc1",0);
        //testAccount2 = new Account(5,"tAcc2",0);
        
        database = new AccountDatabase();
        database.addAccount(1, "tAcc1", 500);
        database.addAccount(5, "tAcc2", 500);
        
        testTrans1 = new AtomicTransaction(1,5,200,database,"testTrans1");
        testTrans2 = new AtomicTransaction(5,1,100,database,"testTrans2");
        testTManager = new TransactionManager(database);
        
    }
    
    @Test
    public void numTransactionsProcessedTest() throws TransactionFailureException, InterruptedException
    {
        assertEquals(0,testTManager.getNumberTransactionsProcessed());
        //testTManager.processTransaction(1,5,200,"dummyTransaction");
        testTManager.processTransaction(testTrans1);
        assertEquals(1,testTManager.getNumberTransactionsProcessed());
    }
    
    @Test
    public void processTransactionTest()throws TransactionFailureException, InterruptedException//consider a version of the function which takes the object type
    {
        long source1 = database.getAccount(1).getAccountBalance();
        long dest1 = database.getAccount(5).getAccountBalance();
        assertEquals(true, testTManager.processTransaction(testTrans1));
        assertEquals(source1-200,database.getAccount(1).getAccountBalance());
        assertEquals(dest1+200,database.getAccount(5).getAccountBalance());
    }
    
    //made redundant by the revisons made to the transaction manager
    /*@Test
    public void processTransactionObjectTest()
    {
        long source1 = database.getAccount(5).getAccountBalance();
        long dest1 = database.getAccount(1).getAccountBalance();
        assertEquals(true, testTManager.processTransaction(testTrans2));
        assertEquals(source1-100,database.getAccount(5).getAccountBalance());
        assertEquals(dest1+100,database.getAccount(1).getAccountBalance());
    }*/
    
    //As set up is run before each test, teh above tests are immune to the 15 second rule
    
    //Testing for business logic rule 3 (minimum inter-transaction time of 15 seconds for all accounts)
    /*Proposed test
    Have two transactions involving at least one account in common
    The second transaction should return false whilst the first executes
    
    Then have another test that uses thread.sleep  to wait 16 seconds between transactions
    Both transactions shoudl return true*/
    
    //Both testTrans1 and testTrans2 use the same accounts
    @Test
    public void trans15SecondRuleFailTest () throws TransactionFailureException,InterruptedException
    {
        long start = System.nanoTime();
        assertEquals(true, testTManager.processTransaction(testTrans2));
        long now = System.nanoTime();
        assertEquals(true,(start/1000+15000000)>(now/1000));
        assertEquals(false, testTManager.processTransaction(testTrans1));
    }
    
    @Test
    public void trans15SecondRulePassTest () throws InterruptedException,TransactionFailureException
    {
        assertEquals(true, testTManager.processTransaction(testTrans2));
        Thread.sleep(16000);//in milliseconds therefore 15 seconds = 15000 milliseconds
        assertEquals(true, testTManager.processTransaction(testTrans1));
    }
    
}
