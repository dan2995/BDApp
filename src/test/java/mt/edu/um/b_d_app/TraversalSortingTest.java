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

//all of the tests need addition assertEquals to ensure that the length of result is correct

public class TraversalSortingTest {
    
    Transaction transaction;
    AccountDatabase database;
    ArrayList<AtomicTransaction> transactionsForAcc; 
    
    @Before
    public void Setup()
    {
       
    }
    
    @Test
    public void amountAscendingTest()
    {
        ArrayList<AtomicTransaction> result = transaction.getTransactionsByAmount(SortType.Ascending);
        
        for(int i = 0;i<result.size()-1;i++)
        {
            assertEquals(true,result.get(i).getAmount()<=result.get(i+1).getAmount()); 
        }
    }
    
    @Test
    public void amountDescendingTest()
    {
        ArrayList<AtomicTransaction> result = transaction.getTransactionsByAmount(SortType.Ascending);
        
        for(int i = 0;i<result.size()-1;i++)
        {
            assertEquals(true,result.get(i).getAmount()>=result.get(i+1).getAmount()); 
        }
        
    }
           
    @Test
    public void srcAccountExistsFilteringTest()
    {
        int src;//need to set this to the right account number
        
        ArrayList<AtomicTransaction> result = transaction.getTransactionsByAccount(FilterType.Source,src);
       
        for(int i = 0;i<transactionsForAcc.size();i++)
        {
            assertEquals(src, result.get(i).getSourceAccountNumber()); 
        }
    }
    
    @Test
    public void srcAccountDoesNotExistFilteringTest()
    {
        int src;
        assertEquals(null, transaction.getTransactionsByAccount(FilterType.Source,src));
    }
    
}
