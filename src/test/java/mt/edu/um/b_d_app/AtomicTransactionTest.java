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
public class AtomicTransactionTest  {
 
    //The test accounts declared for the AtomicTransaction classes tests
    Account testAccount1;
    Account testAccount2;
    
    //The test transaction instance
    AtomicTransaction testTrans1;
    
    //The account database
    AccountDatabase database;
    
    AtomicTransaction failTrans1;
    AtomicTransaction failTrans2;
    AtomicTransaction failTrans3;
    
    AtomicTransaction failTrans4;
    
    //test getTransactionName() as an inherited method
    
    @Before
    public void setUp()
    {
        database = new AccountDatabase();
        
        database.addAccount(1, "tAcc1", 500);
        database.addAccount(5, "tAcc2", 500);
        
        testTrans1 = new AtomicTransaction(1,5,200,database,"testTrans1", RiskTypes.NOTYPE);
        
        failTrans1 = new AtomicTransaction(1,0,50,database,"failTrans1", RiskTypes.NOTYPE);//non-existent account
        failTrans2 = new AtomicTransaction(1,5,10,null,"failTrans2", RiskTypes.NOTYPE);//null database
        failTrans3 = new AtomicTransaction(1,5,1300,database,"failTrans3", RiskTypes.NOTYPE);//invalid amount
        failTrans4 = new AtomicTransaction();
    }
    
    
    
    //Testing the AtomicTransaction Class members as per the provided class diagram
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
        assertEquals(200, testTrans1.getAmount(),0.5);
    }
    

    @Test
    public void processTest()throws InterruptedException
    {
        double source_balance = database.getAccount(1).getAccountBalance();
        double destination_balance = database.getAccount(5).getAccountBalance();
        assertEquals(true, testTrans1.process());
        assertEquals(source_balance-testTrans1.getAmount(),database.getAccount(1).getAccountBalance(),0.5);
        assertEquals(destination_balance+testTrans1.getAmount(),database.getAccount(5).getAccountBalance(),0.5);
    }
    
    @Test
    public void processFailAccountTest ()throws InterruptedException
    {
        assertEquals(false,failTrans1.process());
    }
    
    @Test
    public void processFailDatabaseTest ()throws InterruptedException
    {
        assertEquals(false,failTrans2.process());
    }
    
    @Test
    public void processFailAmountTest ()throws InterruptedException
    {
        assertEquals(false,failTrans3.process());
    }
    
    @Test
    public void processFailAllTest ()throws InterruptedException
    {
        assertEquals(false,failTrans4.process());
    }
    
    @Test
    public void getTransactionTest ()
    {
        assertEquals(null,testTrans1.getTransaction("AnyTransaction"));
    }
    
    @Test
    public void removeTransactionTest ()
    {
        assertEquals(false,testTrans1.removeTransaction("AnyTransaction"));
    }
    
    @Test
    public void addCompositeTransaction()
    {
        assertEquals(false,testTrans1.addTransaction("AnyTransaction", RiskTypes.NOTYPE));
    }
    
    @Test
    public void addAtomicTransaction()
    {
        assertEquals(false,testTrans1.addTransaction(database,1,5,100,"AnyName", RiskTypes.NOTYPE));
    }

    @Test
    public void addTransactionFail(){
        assertEquals(false, testTrans1.addTransaction(database, 1,2,100,"Name", RiskTypes.NOTYPE));
    }

    @Test
    public void getListSizeTest(){
        assertEquals(0, testTrans1.getListSize());
    }

    @Test
    public void addTransactionTest(){
        CompositeTransaction fail = new CompositeTransaction("fail", RiskTypes.NOTYPE);
        assertEquals(false, testTrans1.addTransaction(fail));
    }
}
