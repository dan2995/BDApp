package mt.edu.um.b_d_app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
        assertEquals(false,account.adjustBalance(-120));
    }
    
    @Test
    public void getNameTest()
    {
        assertEquals("myAccount",account.getAccountName());
    }
    
    /*@Test
    public void setNameTest()
    {
        String original = account.getAccountName();
        account.setAccountName("MyAccountRenamed");
        assertEquals("MyAccountRenamed",account.getAccountName());
    }*/

    @Test
    public void getAccountNumberTest()
    {
        assertEquals(1,account.getAccountNumber());
    }

    /*@Test
    public void setAccountNumberTest()
    {
        int original = account.getAccountNumber();
        account.setAccountNumber(6);
        assertEquals(6,account.getAccountNumber());
    }*/

    @Test
    public void getAccountBalanceTest()
    {
        assertEquals(100.0,account.getAccountBalance(),0.5);
    }

    /*@Test
    public void setAccountBalanceTest()
    {
        account.setAccountBalance(120);
        assertEquals(120,account.getAccountBalance());
    }*/
}
