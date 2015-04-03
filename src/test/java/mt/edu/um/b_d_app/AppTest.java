package mt.edu.um.b_d_app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{   
    @Test
    public void myTest()
    {
        System.out.println("My test (also checking Jenkins)");
    }

    @Test
    public void testingMyCode() {
        assertEquals(5, (new App()).dummyTest());
    }
     
}
