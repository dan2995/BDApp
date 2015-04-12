package mt.edu.um.b_d_app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountDatabaseTest {

    Account account1;
    Account account2;
    Account account3;
    Account account4;
    Account account5;
    
    Account objAccount;

    AccountDatabase accountDatabase1;

    @Before
    public void SetUp() throws Exception{
        account1 = new Account(1, "myAccount1", 100);
        account2 = new Account(2, "myAccount2", 50);
        account3 = new Account(3, "myAccount3", 75);
        account4 = new Account(4, "myAccount4", 90);
        account5 = new Account(5, "myAccount5", 32);
        
        accountDatabase1 = new AccountDatabase();

        accountDatabase1.accountList.add(account1);
        accountDatabase1.accountList.add(account2);
        accountDatabase1.accountList.add(account3);
        accountDatabase1.accountList.add(account4);
        accountDatabase1.accountList.add(account5);
        
        objAccount = new Account(10,"objectAccount",62);
    }

    @Test
    public void addAccountTest1(){
        assertEquals(true,accountDatabase1.addAccount(7,"My Acc",300));
    }

    @Test
    public void addAccountTest2(){
        assertEquals(false,accountDatabase1.addAccount(2,"My Acc",300));
    }

    @Test
    public void getAccountTest1(){
        assertEquals(account3,accountDatabase1.getAccount(3));
    }

    @Test
    public void getAccountTest2(){
        assertEquals(null,accountDatabase1.getAccount(6));
    }

    @Test
    public void getSizeTest(){
        assertEquals(5,accountDatabase1.getSize());
    }
    
    @Test
    public void addAccountObjectTest()
    {
        int original_size = accountDatabase1.getSize();
        assertEquals(true, accountDatabase1.addAccount(objAccount));
        assertEquals(++original_size, accountDatabase1.getSize());
    }
}
