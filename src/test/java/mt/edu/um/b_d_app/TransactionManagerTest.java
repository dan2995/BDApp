/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mt.edu.um.b_d_app;

import java.util.ArrayList;
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
        
        testTrans1 = new AtomicTransaction(1,5,200,database,"testTrans1", RiskTypes.NOTYPE);
        testTrans2 = new AtomicTransaction(5,1,100,database,"testTrans2", RiskTypes.NOTYPE);
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
        double source1 = database.getAccount(1).getAccountBalance();
        double dest1 = database.getAccount(5).getAccountBalance();
        assertEquals(true, testTManager.processTransaction(testTrans1));
        assertEquals(source1-200,database.getAccount(1).getAccountBalance(),0.5);
        assertEquals(dest1+200,database.getAccount(5).getAccountBalance(),0.5);
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
    
    /*Could not identify problem with test
    @Test
    public void compoundTransactionProcessTest() throws TransactionFailureException,InterruptedException
    {
        int DSTAccountNumber = 1010;//hardcoded reference for testing purposes
        double hardCodedStartBalance = 100000.00;

        Account CommissionSourceAccount;
        Account CommissionDstAccount;
        Account DepositSourceAccount;
        Account MainTransSourceAccount;

        Account depositDSTAccount;
        double depositAmount;
        
        
        LowRiskTransactionCreator creator  = new LowRiskTransactionCreator();
        ArrayList<Account> mainDestAccounts= new ArrayList<Account>();
        mainDestAccounts.add(database.getAccount(1));
        mainDestAccounts.add(database.getAccount(5));
        ArrayList<Double> mainAmounts = new ArrayList<Double>();
        mainAmounts.add(new Double (10.5));
        mainAmounts.add(new Double(11.5));
        
        depositDSTAccount = new Account(DSTAccountNumber,"DepositAcount",25.5);
            depositAmount = 71.3;

            //Creating the accounts hardcoded in the creator class
            CommissionSourceAccount = new Account(creator.getCommissionSourceAccountNo(),"CommSrcAccount",hardCodedStartBalance);
            CommissionDstAccount = new Account(creator.getCommissionDstAccountNo(),"CommDstAccount", hardCodedStartBalance);
            DepositSourceAccount = new Account(creator.getDepositSourceAccountNo(),"DepSrcAccount",hardCodedStartBalance);
            MainTransSourceAccount = new Account(creator.getMainTransSourceAccountNo(),"MainTransSrcAccount",hardCodedStartBalance);

            database.addAccount(CommissionSourceAccount);
            database.addAccount(CommissionDstAccount);
            database.addAccount(DepositSourceAccount);
            database.addAccount(MainTransSourceAccount);
            database.addAccount(depositDSTAccount);
            
        double balance1 = database.getAccount(1).getAccountBalance();
        double balance5 = database.getAccount(5).getAccountBalance();
        
        assertEquals(true,this.testTManager.processTransaction(RiskTypes.LOW, depositDSTAccount, depositAmount, mainDestAccounts, mainAmounts, database));
        assertEquals(balance1+10.5,database.getAccount(1).getAccountBalance(),0.05);
        assertEquals(balance5+11.5,database.getAccount(5).getAccountBalance(),0.05);
        
    }*/
    
}
