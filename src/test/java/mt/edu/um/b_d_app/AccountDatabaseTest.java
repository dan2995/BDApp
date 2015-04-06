package mt.edu.um.b_d_app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Daniela on 06/04/2015.
 */
public class AccountDatabaseTest {

    Account account1;
    Account account2;
    Account account3;
    Account account4;
    Account account5;

    AccountDatabase accountDatabase1;

    @Before
    public void SetUp() throws Exception{
        account1 = new Account(1, "myAccount1", 100);
        account2 = new Account(2, "myAccount2", 50);
        account3 = new Account(3, "myAccount3", 75);
        account4 = new Account(4, "myAccount4", 90);
        account5 = new Account(5, "myAccount5", 32);

        accountList.add(account1);
        accountList.add(account2);
        accountList.add(account3);
        accountList.add(account4);
        accountList.add(account5);
    }

    @Test
         public void getAccountTest1(){
        assertEquals(true,accountDatabase1.getAccount(3));
    }

    @Test
    public void getAccountTest2(){
        assertEquals(false,accountDatabase1.getAccount(6));
    }

    @Test
    public void getSizeTest(){
        assertEquals(5,accountDatabase1.getSize());
    }
}
