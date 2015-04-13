/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mt.edu.um.b_d_app;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Beatrix
 */
public class LastUsedTest {
    
    LastUsed testObject;
    long startUp;
    
    @Before
    public void setUp ()
    {
        startUp = System.nanoTime();
        testObject = new LastUsed (1,startUp);
    }
    
    @Test
    public void getAccountNumberTest()
    {
        assertEquals(1,testObject.getAccountNumber());
    }
    
    @Test 
    public void getLastUsedTest ()
    {
        assertEquals(startUp,testObject.getLastUsed());
    }
    
    @Test
    public void setLastUsedTest ()
    {
        long now = System.nanoTime();
        testObject.setLastUsed(now);
        assertEquals(now, testObject.getLastUsed());
    }    
    
}
