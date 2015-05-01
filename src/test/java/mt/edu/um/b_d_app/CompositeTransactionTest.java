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
    CompositeTransaction transaction1;
    CompositeTransaction transaction2;


    @Before
    public void SetUp() {
        database1 = new AccountDatabase();
        account1 = new Account(1, "myAccount1", 100);
        account2 = new Account(2, "myAccount2", 200);
        account3 = new Account(3, "myAccount3", 300);
        transaction1 = new CompositeTransaction("TransactionTest");
        transaction2 = new CompositeTransaction();
    }

    @Test
    public void addAtomicTransactionTest(){
        int arrayListSize = transaction1.getListSize();
        assertEquals(true,transaction1.addTransaction(database1, 2, 1, 50, "Atomic Transaction"));
        int arrayListSizeUpdated = transaction1.getListSize();

    }

    @Test
    public void addCompositeTransactionTest(){
        int arrayListSize = transaction1.getListSize();
        assertEquals(true,transaction1.addTransaction("Composite Transaction"));
        int arrayListSizeUpdated = transaction1.getListSize();

    }

    @Test
    public void getTransactionTest(){
        assertEquals(transaction2,transaction2.getTransaction("GenericTransaction"));
    }






    
    //test addAtomicTransaction
    
    //test addCompositeTransaction
    
    //test getTransaction
    
    //__________________________________________________________________________________________
    
    //test process
    @Test
    public void processCompositeTransactionPassTest()
    {
        long acc1Balance = account1.getAccountBalance();
        long acc2Balance = account2.getAccountBalance();
        long acc3Balance = account3.getAccountBalance();
       
        transaction1.addTransaction(database1, 1, 2, 50, "IncreaseAcc2By50");
        transaction1.addTransaction("2to3andBack");
        transaction1.getTransaction("2to3andBack").addTransaction(database1, 2, 3, 100, "IncreaseAcc3By100");
        transaction1.getTransaction("2to3andBack").addTransaction(database1, 3, 2, 50, "IncreaseAcc2By50");
        
        try
        {
            assertEquals(true,transaction1.process());
        }
        catch(TransactionFailureException e)
        {
            System.out.println(e.getMessage());
        }
        
        assertEquals(acc1Balance-50,account1.getAccountBalance());
        assertEquals(acc2Balance,account2.getAccountBalance());
        assertEquals(acc3Balance+50,account3.getAccountBalance());

    }
    
    @Test
    public void processCompositeTransactionFailTest()
    {
        long acc1Balance = account1.getAccountBalance();
        long acc2Balance = account2.getAccountBalance();
        long acc3Balance = account3.getAccountBalance();
       
        transaction1.addTransaction(database1, 1, 2, 150, "IncreaseAcc2By50");//should fail
        transaction1.addTransaction("2to3andBack");
        transaction1.getTransaction("2to3andBack").addTransaction(database1, 2, 3, 100, "IncreaseAcc3By100");
        transaction1.getTransaction("2to3andBack").addTransaction(database1, 3, 2, 50, "IncreaseAcc2By50");
        
        try
        {
            assertEquals(false,transaction1.process());
        }
        catch(TransactionFailureException e)
        {
            System.out.println(e.getMessage());
        }
        
        assertEquals(acc1Balance-50,account1.getAccountBalance());
        assertEquals(acc2Balance,account2.getAccountBalance());
        assertEquals(acc3Balance+50,account3.getAccountBalance());

    }
    
    @Test
    public void removeTransactionTestPass()
    {
        int sizeOfT1List = transaction1.getListSize();
        int sizeOf2to3BackList = transaction1.getTransaction("2to3andBack").getListSize();
        
        transaction1.addTransaction(database1, 1, 2, 50, "IncreaseAcc2By50");
        transaction1.addTransaction("2to3andBack");
        transaction1.getTransaction("2to3andBack").addTransaction(database1, 2, 3, 100, "IncreaseAcc3By100");
        transaction1.getTransaction("2to3andBack").addTransaction(database1, 3, 2, 50, "IncreaseAcc2By50"); 
        
        assertEquals(true,transaction1.removeTransaction("IncreaseAcc2By50"));
        assertEquals(sizeOfT1List,transaction1.getListSize());
        assertEquals(sizeOf2to3BackList-1,transaction1.getTransaction("2to3andBack").getListSize());
    }
    
    @Test
    public void removeTransactionFail()
    {
        int sizeOfT1List = transaction1.getListSize();
        int sizeOf2to3BackList = transaction1.getTransaction("2to3andBack").getListSize();
        
        transaction1.addTransaction(database1, 1, 2, 50, "IncreaseAcc2By50");
        transaction1.addTransaction("2to3andBack");
        transaction1.getTransaction("2to3andBack").addTransaction(database1, 2, 3, 100, "IncreaseAcc3By100");
        transaction1.getTransaction("2to3andBack").addTransaction(database1, 3, 2, 50, "IncreaseAcc2By50"); 
        
        assertEquals(true,transaction1.removeTransaction("GenericTransaction"));
        assertEquals(sizeOfT1List,transaction1.getListSize());
        assertEquals(sizeOf2to3BackList,transaction1.getTransaction("2to3andBack").getListSize()); 
    }
    
}
