package mt.edu.um.b_d_app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AtomicIteratorTest {

    AtomicTransaction creator;
    AtomicIterator iterator;

    @Before
    public void Setup(){
        creator = new AtomicTransaction();
        iterator = new AtomicIterator(creator);
    }

    @Test
    public void firstItemInIteratorTest(){
        assertEquals(creator, iterator.first());
    }

    @Test
    public void currentTest(){
        assertEquals(creator, iterator.currentItem());
    }

}