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

public class CompositeIteratorTest {
    
    Transaction transaction;
    HighRiskTransactionCreator creator;
    AccountDatabase database;
    double hardCBalance = 100000;
    int DepositDstAccountNo = 1010;
    Account DepositDstAccount;
    double depositAmount = 50.75;
    Iterator iteratorForTransaction;
    
    
    @Before
    public void Setup()
    {    
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
        
        transaction = creator.createTransaction(this.DepositDstAccount, depositAmount, mainList, amountList, database);//creating a high risk transaction
        
        iteratorForTransaction = transaction.createIterator();
    }
    
    @Test
    public void firstItemInIteratorTest()
    {
        //the first item should be the deposit transaction
        AtomicTransaction temp = iteratorForTransaction.first();
        assertEquals("DepositTransaction", temp.getTransactionName());
        assertEquals(this.depositAmount, temp.getAmount(),0.05);
        assertEquals(this.DepositDstAccount.getAccountNumber(),temp.getDestinationAccountNumber());
        
    }
    
    @Test
    public void noFirstItemInIterator()
    {
        CompositeTransaction temp = new CompositeTransaction();//empty list inside
        Iterator iterator = temp.createIterator();
        
        assertEquals(null,iterator.first());
    }
    
    @Test
    public void currentTest()
    {
        
    }
    
    @Test
    public void NoCurrentAvailableTest()
    {
        CompositeTransaction temp = new CompositeTransaction();//empty list inside
        Iterator iterator = temp.createIterator();
        
        //starting index 0 is equal to size zero therefore null is expected
        assertEquals(null,iterator.currentItem());
    }
}
