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
    
    @Before
    public void setUp()
    {
        testAccount1 = new Account(1);
        testAccount2 = new Account(5);
        testTrans1 = new Transaction(1,5,200);
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
    
    /*@Test
    public void processTest()
    {
        int originalBalance = testAccount2.getAccountBalance();
        assertEquals(true, testTrans1.process());
        assertEquals(originalBalance+200,testAccount2.getAccountBalance());
    }*/
    
}
