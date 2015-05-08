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
    HighRiskTransactionCreator creator;
    AccountDatabase database;
    ArrayList<AtomicTransaction> transactionsForAcc; 
    double hardCBalance = 100000;
    int DepositDstAccountNo = 1010;
    
    @Before
    public void Setup()
    {
        //Setting uo the database
        
        database = new AccountDatabase();
        
        //The hardcoded accounts in the creator class
        database.addAccount(creator.getCommissionSourceAccountNo(), "HRiskTComSrcAccount", hardCBalance);
        database.addAccount(creator.getCommissionDstAccountNo(), "HRiskTComDstAccount", hardCBalance);
        database.addAccount(creator.getDepositSourceAccountNo(), "HRiskDepSrcAccount", hardCBalance);
        database.addAccount(creator.getMainTransSourceAccountNo(), "HRiskMainTransSrcAccount", hardCBalance);
        
        database.addAccount(DepositDstAccountNo, "DepositDstAccount", hardCBalance);
        
        
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
