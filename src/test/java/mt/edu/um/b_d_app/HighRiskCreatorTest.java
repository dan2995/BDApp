
package mt.edu.um.b_d_app;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class HighRiskCreatorTest {
    
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
    
    HighRiskTransactionCreator creator;
    
    @Before
    public void SetUp()
    { 
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
    
    @Test
    public void createTransactionTest()
    {
        Transaction result = creator.createTransaction(depositDSTAccount, depositAmount, destinationList, amountList, database);
        
        //check that there are three level one transactions
        assertEquals(3,result.getListSize());
        
        //check the length of the two level one compound transactions
        assertEquals(10, result.getTransaction("MainTransaction").getListSize());
        assertEquals(10, result.getTransaction("CommissionTransaction").getListSize());
        
        //Check the atomic transaction
        CompositeTransaction x = null;
        
        assertEquals(0,result.getTransaction("DepositTransaction").getListSize());
        assertEquals(false,result.getTransaction("DepositTransaction").addTransaction(x));
    }
    
}
