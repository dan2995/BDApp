package mt.edu.um.b_d_app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{ 
    //The test accounts declared for the Transaction classes tests
    
    
    @Before
    public void setUp()
    {
        
    }
    
    @Test
    public void myTest()
    {
        System.out.println("My test (also checking Jenkins)");
    }
    
    @Test
    public void myTest2()
    {
        System.out.println("My test (also checking Jenkins) a second time.");
    }
    
    @Test
    public void testingMyCode() {
        assertEquals(5, (new App()).dummyTest());
    }
    
    
     
}
