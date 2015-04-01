package mt.edu.um.b_d_app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    Account a_test;
    
    @Before
    public void setUp()
    {
        a_test = new Account();
    }
    
    @Test
    public void myTest()
    {
        System.out.println("My test (also checking Jenkins)");
    }
    
    @Test
    public void AccountNumberTest()
    {
        assertEquals("testing_account",a_test.getName());
    }
}
