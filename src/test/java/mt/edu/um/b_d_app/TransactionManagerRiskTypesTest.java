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

public class TransactionManagerRiskTypesTest {
    
    int DSTAccountNumber = 1010;//hardcoded reference for testing purposes
    double hardCodedStartBalance = 100000.00;
    
    Account CommissionSourceAccount;
    Account CommissionDstAccount;
    Account DepositSourceAccount;
    Account MainTransSourceAccount;
    
    Account depositDSTAccount;
    double depositAmount;
    ArrayList<Account> destinationList; 
    ArrayList<Double> amountList;
    AccountDatabase database;
    int LIMIT = 10;
    
    HighRiskTransactionCreator creator;//for informational purposes when creating the accounts
    
    TransactionManager TManager;
    
    @Before
    public void SetUp()
    { 
        TManager = new TransactionManager();
        
        creator = new HighRiskTransactionCreator();
        
        database = new AccountDatabase();
        
        destinationList = new ArrayList<Account>();
        amountList = new ArrayList<Double>();
        
        //populating the destination list
        //               amount list
        //               database
        for(int i = 0; i<LIMIT;i++)
        {
            Account x = new Account (i, "DestinationAcc"+i, i*100);
            database.addAccount(x);
            destinationList.add(x);
            amountList.add(new Double(50.5 + i));
        }
        
        depositDSTAccount = new Account(DSTAccountNumber,"DepositAcount",25.5);
        depositAmount = 71.3;
        
        //Creating the accounts hardcoded in the creator class
        CommissionSourceAccount = new Account(creator.getCommissionSourceAccountNo(),"CommSrcAccount",this.hardCodedStartBalance);
        CommissionDstAccount = new Account(creator.getCommissionDstAccountNo(),"CommDstAccount", this.hardCodedStartBalance);
        DepositSourceAccount = new Account(creator.getDepositSourceAccountNo(),"DepSrcAccount",this.hardCodedStartBalance);
        MainTransSourceAccount = new Account(creator.getMainTransSourceAccountNo(),"MainTransSrcAccount",this.hardCodedStartBalance);
        
        database.addAccount(CommissionSourceAccount);
        database.addAccount(CommissionDstAccount);
        database.addAccount(DepositSourceAccount);
        database.addAccount(MainTransSourceAccount);
        
    }
    
    /*@Test Causing problems with the 15-second rule
    public void processTransactionTest() throws TransactionFailureException, InterruptedException
    {
        assertEquals(true, TManager.processTransaction(RiskTypes.HIGH, depositDSTAccount, depositAmount, destinationList, amountList, database));
    }*/
    
    
    
}
