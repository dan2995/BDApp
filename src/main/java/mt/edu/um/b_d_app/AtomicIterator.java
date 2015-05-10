package mt.edu.um.b_d_app;

/**
 * Created by Daniela on 08/05/2015.
 */
public class AtomicIterator extends Iterator {
    
    int index;
    AtomicTransaction transaction;
    
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
        switch(index)
        {
            case 0:
                return false;
            default:
                return true;
        }
    }

    @Override
    public AtomicTransaction currentItem()
    {
        return this.transaction;
    }
}
