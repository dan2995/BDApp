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
        this.index = 0;
        if(transaction.getListSize()==0)
        {
            transactions = null;
        }
        else
        {
            for(int i = 0; i<transaction.getListSize();i++)
            {
                //use iterators
                Transaction current = transaction.getTransaction(i);
                Iterator iterator = current.createIterator();
                while(!iterator.isDone())
                {
                    AtomicTransaction nextATrans = iterator.next();
                    this.transactions.add(nextATrans);//THROWING A NULL POINTER EXCEPTION SOMEHWERE HERE
                }
            }
        }
    }

    @Override
    public AtomicTransaction first()
    {
        if(transactions==null)
        {
            return null;
        }
        return transactions.get(0);
    }

    @Override
    public AtomicTransaction next()
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
        if(index>this.transactions.size())
        {
            return false;
        }
        return true;
    }

    @Override
    public AtomicTransaction currentItem()//a sort of peek. do not move past
    {
        if(this.index<this.transactions.size())
        {
            return this.transactions.get(index);
        }
        return null;
    }
}
