package mt.edu.um.b_d_app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Daniela on 06/04/2015.
 */
public class AccountTest {

    Account account;

    @Before
    public void SetUp() throws Exception{
        account = new Account(1, "myAccount", 100);
    }

    @Test
    public void adjustBalanceTest1(){
        assertEquals(true,account.adjustBalance(30));
    }

    @Test
    public void adjustBalanceTest2(){
        assertEquals(true,account.adjustBalance(100));
    }

    @Test
    public void adjustBalanceTest3(){
        assertEquals(false,account.adjustBalance(120));
    }
}
