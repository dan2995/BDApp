/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mt.edu.um.b_d_app;

/**
 *
 * @author Beatrix
 */

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

//all of the tests have a return type of atomic accounts

public class TraversalSortingTest {
    
    Transaction transaction;
    AccountDatabase database;
    ArrayList<AtomicTransaction> transactionsForAcc; 
    ArrayList<AtomicTransaction> transactionsDescendingAmount;
    ArrayList<AtomicTransaction> transactionsAscendingAmount;
    
    @Before
    public void Setup()
    {
        
    }
    
    @Test
    public void amountAscendingTest()
    {
        ArrayList<AtomicTransaction> result = transaction.getTransactionsAscendingAmount();
        
        for(int i = 0;i<transactionsAscendingAmount.size();i++)
        {
            assertEquals(transactionsAscendingAmount.get(i), result.get(i)); 
        }
    }
    
    @Test
    public void amountDescendingTest()
    {
        ArrayList<AtomicTransaction> result = transaction.getTransactionsDescendingAmount();
        
        for(int i = 0;i<transactionsDescendingAmount.size();i++)
        {
            assertEquals(transactionsDescendingAmount.get(i), result.get(i)); 
        }
        
    }
           
    @Test
    public void srcAccountExistsFilteringTest()
    {
        ArrayList<AtomicTransaction> result = transaction.getTransactionsUsingSource(int src);
       
        for(int i = 0;i<transactionsForAcc.size();i++)
        {
            assertEquals(transactionsForAcc.get(i), result.get(i)); 
        }
    }
    
    @Test
    public void srcAccountDoesNotExistFilteringTest()
    {
        assertEquals(null, transaction.getTransactionsUsingSource(int src));
    }
    
}
