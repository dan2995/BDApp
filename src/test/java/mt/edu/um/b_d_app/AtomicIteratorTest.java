package mt.edu.um.b_d_app;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Daniela on 14/05/2015.
 */
public class AtomicIteratorTest {

    /*Transaction transaction;
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

        transaction = creator.createTransaction(this.DepositDstAccount, depositAmount, mainList, amountList, database);

        iteratorForTransaction = transaction.createIterator();
    }*/

    AtomicTransaction creator;
    AtomicIterator iterator;

    @Before
    public void Setup(){
        iterator = new AtomicIterator(creator);
    }

    @Test
    public void firstItemInIteratorTest(){
        assertEquals(creator, iterator.first());
    }

    @Test
    public void noFirstItemInIterator(){
        AtomicTransaction temp = new AtomicTransaction();//empty list inside
        Iterator iteratorA = temp.createIterator();

        assertEquals(null,iterator.first());

    }

    @Test
    public void currentTest(){
        assertEquals(creator, iterator.currentItem());
    }

    @Test
    public void NoCurrentAvailableTest(){
        AtomicTransaction temp = new AtomicTransaction();//empty list inside
        Iterator iteratorA = temp.createIterator();

        assertEquals(null,iterator.currentItem());
    }

}
