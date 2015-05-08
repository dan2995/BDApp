package mt.edu.um.b_d_app;

import java.util.ArrayList;

/**
 * Created by Daniela on 08/05/2015.
 */
public class CompositeIterator extends Iterator{
    
    int index;
    ArrayList<AtomicTransaction> transactions;
    
    CompositeIterator(CompositeTransaction transaction)
    {
        if(transaction.getListSize()==0)
        {
            transactions = null;
        }
        else
        {
            for(int i = 0; i<transaction.getListSize();i++)
            {
                //use iterators
                
            }
        }
        this.index = 0;
    }

    @Override
    public Transaction first()
    {
        if(transactions==null)
        {
            return null;
        }
        return transactions.get(0);
    }

    @Override
    public Transaction next()
    {
        if(this.index<this.transactions.size()-1)
        {
            index++;
            return this.transactions.get(index);
        }
        return null;
    }

    @Override
    public boolean isDone()
    {
        if(index<this.transactions.size())
        {
            return true;
        }
        return false;
    }

    @Override
    public Transaction currentItem()//a sort of peek. do not move past
    {
        if(this.index<this.transactions.size())
        {
            return this.transactions.get(index);
        }
        return null;
    }
}
