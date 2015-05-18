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
    public void noFirstItemInIterator(){
        AtomicTransaction temp = new AtomicTransaction();//empty list inside
        Iterator iteratorA = temp.createIterator();

        assertEquals(null,iterator.first());

    }

    @Test
    public void currentTest(){
        assertEquals(creator, iterator.currentItem());
    }

    @Test
    public void NoCurrentAvailableTest(){
        AtomicTransaction temp = new AtomicTransaction();//empty list inside
        Iterator iteratorA = temp.createIterator();

        assertEquals(null,iteratorA.currentItem());
    }

}