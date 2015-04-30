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
public class CompositeTransactionTest {

    AccountDatabase database1;
    Account account1;
    Account account2;
    Account account3;
    TransactionManager manager;
    CompositeTransaction transaction1;
    CompositeTransaction transaction2;


    @Before
    public void SetUp() {
        database1 = new AccountDatabase();
        account1 = new Account(1, "myAccount1", 100);
        account2 = new Account(2, "myAccount2", 200);
        account3 = new Account(3, "myAccount3", 300);
        manager = new TransactionManager(database1);
        transaction1 = new CompositeTransaction("TransactionTest");
        transaction2 = new CompositeTransaction();
    }

    @Test
    public void addAtomicTransactionTest(){
        int arrayListSize = transaction1.getListSize();
        assertEquals(true,transaction1.addAtomicTransaction(database1, 2, 1, 50, "Transaction"));
        int arrayListSizeUpdated = transaction1.getListSize();

    }

    @Test
    public void addAtomicTransactionTest(){
        assertEquals(true,transaction1.addAtomicTransaction(database1, 2, 1, 50, "Transaction"));
    }






    
    //test addAtomicTransaction
    
    //test addCompositeTransaction
    
    //test getTransaction
    
    //__________________________________________________________________________________________
    
    //test process
    @Test
    public void processCompositeTransactionTest()
    {
        transaction1.addAtomicTransaction(database1, 1, 2, 50, "IncreaseAcc2By50");
        transaction1.addCompositeTransaction("2to3andBack");
        (transaction1.getTransaction("2to3andBack")).;
    }
    
    //test removeTransaction
    
}
