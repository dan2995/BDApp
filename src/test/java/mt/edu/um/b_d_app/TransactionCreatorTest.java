package mt.edu.um.b_d_app;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Daniela on 07/05/2015.
 */
public class TransactionCreatorTest {

    RiskTypes typeL = RiskTypes.LOW;
    RiskTypes typeH = RiskTypes.HIGH;
    Account depositDSTAccount;
    float depositAmount;
    ArrayList<Account> destinationList;
    ArrayList<Double> amountList;
    AccountDatabase database;
    
    TransactionCreator creator;
    
    @Before
    public void SetUp(){
        
        creator = new TransactionCreator();
        
    }

    @Test
    public void createTransactionHighTest(){

    }

    @Test
    public void createTransactionLowTest(){

    }

    @Test
    public void createTransactionTest(){
        assertEquals(null,creator.createTransaction());
    }

    @Test
    public void createTransactionNoTypeTest(){
        
    }
}
