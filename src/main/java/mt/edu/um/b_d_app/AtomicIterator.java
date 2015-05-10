package mt.edu.um.b_d_app;

/**
 * Created by Daniela on 08/05/2015.
 */
public class AtomicIterator extends Iterator {
    
    int index;
    AtomicTransaction transaction;//CHANGE TO ARRAY LIST AND MOVE TO THE SUPERCLASS
    
    AtomicIterator(AtomicTransaction transaction)
    {
        this.transaction = transaction;
        this.index = 0;
    }
    
    @Override
    public AtomicTransaction first()
    {
            return this.transaction;
    }        

    @Override
    public AtomicTransaction next()
    {
        if(index==0)
        {
            index++;
            return this.transaction;
        }
        return null;
    }

    @Override
    public boolean isDone()
    {
        if(index>0)
        {
            return true;
        }
        return false;
    }

    @Override
    public AtomicTransaction currentItem()
    {
        return this.transaction;
    }
}
