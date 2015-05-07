package mt.edu.um.b_d_app;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

//Add a type member to the transaction calsses and include it in the super constructor etc to verify that the right type is being used

/**
 * Created by Daniela on 07/05/2015.
 */
public class TransactionCreatorTest {

    RiskTypes typeL = RiskTypes.LOW;
    RiskTypes typeH = RiskTypes.HIGH;
    Account depositDSTAccount;
    ArrayList<Account> destinationList;
    ArrayList<Double> amountList;
    AccountDatabase database;
    
    Account CommissionSourceAccount;
    Account CommissionDstAccount;
    Account DepositSourceAccount;
    Account MainTransSourceAccount;
    
    TransactionCreator creatorMain;
    
    int LIMIT = 10;
    int DSTAccountNumber = 1010;//hardcoded reference for testing purposes
    double hardCodedStartBalance = 100000.00;
    double depositAmount = 71.3;
    
    @Before
    public void SetUp(){
        
        creatorMain = new TransactionCreator();
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
    }

    @Test
    public void createTransactionHighTest(){
        
        //Specific test set up
        
        HighRiskTransactionCreator creatorH = new HighRiskTransactionCreator();//to access the specifics of the concrete constructor
        
         //Creating the accounts hardcoded in the creator class
        CommissionSourceAccount = new Account(creatorH.getCommissionSourceAccountNo(),"CommSrcAccount",this.hardCodedStartBalance);
        CommissionDstAccount = new Account(creatorH.getCommissionDstAccountNo(),"CommDstAccount", this.hardCodedStartBalance);
        DepositSourceAccount = new Account(creatorH.getDepositSourceAccountNo(),"DepSrcAccount",this.hardCodedStartBalance);
        MainTransSourceAccount = new Account(creatorH.getMainTransSourceAccountNo(),"MainTransSrcAccount",this.hardCodedStartBalance);
        
        database.addAccount(CommissionSourceAccount);
        database.addAccount(CommissionDstAccount);
        database.addAccount(DepositSourceAccount);
        database.addAccount(MainTransSourceAccount);
        
        Transaction result = creatorMain.createTransaction(typeH, depositDSTAccount, depositAmount, destinationList, amountList, database);
        
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

    @Test
    public void createTransactionLowTest(){

        //Specific test set up
        
        LowRiskTransactionCreator creatorL = new LowRiskTransactionCreator();//to access the specifics of the concrete constructor
        
         //Creating the accounts hardcoded in the creator class
        CommissionSourceAccount = new Account(creatorL.getCommissionSourceAccountNo(),"CommSrcAccount",this.hardCodedStartBalance);
        CommissionDstAccount = new Account(creatorL.getCommissionDstAccountNo(),"CommDstAccount", this.hardCodedStartBalance);
        DepositSourceAccount = new Account(creatorL.getDepositSourceAccountNo(),"DepSrcAccount",this.hardCodedStartBalance);
        MainTransSourceAccount = new Account(creatorL.getMainTransSourceAccountNo(),"MainTransSrcAccount",this.hardCodedStartBalance);
        
        database.addAccount(CommissionSourceAccount);
        database.addAccount(CommissionDstAccount);
        database.addAccount(DepositSourceAccount);
        database.addAccount(MainTransSourceAccount);
        
        Transaction result = creatorMain.createTransaction(typeL, depositDSTAccount, depositAmount, destinationList, amountList, database);
        
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

    @Test
    public void createTransactionTest(){
        assertEquals(null,creatorMain.createTransaction());
    }

    @Test
    public void createTransactionNoTypeTest(){
        
    }
}
