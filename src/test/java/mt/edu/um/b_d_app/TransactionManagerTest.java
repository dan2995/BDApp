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
        testAccount1 = new Account(1);
        testAccount2 = new Account(5);
        testTrans1 = new Transaction(1,5,200);
    }
    
}
