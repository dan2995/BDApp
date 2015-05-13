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
    //ArrayList<AtomicTransaction> transactionsForAcc; 
    double hardCBalance = 100000;
    int DepositDstAccountNo = 1010;
    Account DepositDstAccount;
    double depositAmount = 50.75;
    
    @Before
    public void Setup()
    {
        //transactionsForAcc = new ArrayList<AtomicTransaction>();
        
        //Setting uo the database
        
        database = new AccountDatabase();
        creator = new HighRiskTransactionCreator();
        
        //The hardcoded accounts in the creator class
        database.addAccount(creator.getCommissionSourceAccountNo(), "HRiskTComSrcAccount", hardCBalance);
        database.addAccount(creator.getCommissionDstAccountNo(), "HRiskTComDstAccount", hardCBalance);
        database.addAccount(creator.getDepositSourceAccountNo(), "HRiskDepSrcAccount", hardCBalance);
        database.addAccount(creator.getMainTransSourceAccountNo(), "HRiskMainTransSrcAccount", hardCBalance);
        
        DepositDstAccount = new Account(DepositDstAccountNo, "DepositDstAccount", hardCBalance);
        database.addAccount(DepositDstAccount);
        
        ArrayList<Account> mainList = new ArrayList<Account>();
        ArrayList<Double> amountList = new ArrayList<Double>();
        for(int i = 0; i<5; i++)
        {
            Account temp = new Account(i, "Account"+i, hardCBalance);
            database.addAccount(temp);
            mainList.add(temp);
            amountList.add((i+0.5)*2);
        }
        
        transaction = creator.createTransaction(this.DepositDstAccount, depositAmount, mainList, amountList, database);
        
    }
    
    //ADD LENGTH CHECKS FOR THE LOOPS TO CHECK THAT TEHRE ARE AS MANY ATOMIC TRANSACTIONS IN RESULT AS EXPECTED
    
    @Test
    public void amountAscendingTest()
    {
        assertEquals(3,transaction.getListSize());
        
        ArrayList<AtomicTransaction> result = transaction.getTransactionsBy(TraversalTypes.AMOUNTASCENDING);
        
        for(int i = 0;i<11;i++)
        {
            assertEquals(true,result.get(i).getAmount()<=result.get(i+1).getAmount()); 
        }
    }
    
    @Test
    public void amountDescendingTest()
    {
        ArrayList<AtomicTransaction> result = transaction.getTransactionsBy(TraversalTypes.AMOUNTDESCENDING);
        
        for(int i = 0;i<11;i++)
        {
            assertEquals(true,result.get(i).getAmount()>=result.get(i+1).getAmount()); 
        }
        
    }
     
    //still causing problems
    @Test
    public void srcAccountExistsFilteringTest()
    {
        int src= creator.getCommissionSourceAccountNo();//need to set this to the right account number
        
        ArrayList<AtomicTransaction> result = transaction.getTransactionsBy(TraversalTypes.FILTERSOURCEACCOUNT,src);
       
        //5 accounts in the main transaction list therefore five commision transactions
        for(int i = 0;i<5;i++)
        {
            assertEquals(src, result.get(i).getSourceAccountNumber()); 
        }
    }
    
    @Test
    public void srcAccountDoesNotExistFilteringTest()
    {
        int src = 70707;
        //the filter by source account traversal creator returns an empty list if no source accounts are found matching the requested account number
        ArrayList<AtomicTransaction> result = transaction.getTransactionsBy(TraversalTypes.FILTERSOURCEACCOUNT,src);
        
        assertEquals(true, result.isEmpty());
    }
    
}
