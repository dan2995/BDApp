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
        //System.out.println("Composite transaction "+transaction.getTransactionName()+" "+transaction+" of size "+transaction.getListSize()+" at level 1 is creating its iterator...");
        if(transaction.getListSize()==0)
        {
            transactions = null;
        }
        else
        {
            transactions = new ArrayList<AtomicTransaction>();
            
            for(int i = 0; i<transaction.getListSize();i++)
            {
                //use iterators
                Transaction current = transaction.getTransaction(i);
                
                //System.out.println("Fetching transaction "+current.getTransactionName()+" "+current +" from inside "+transaction.getTransactionName()+" "+transaction);
                
                Iterator iterator = current.createIterator();
                while(!iterator.isDone())
                {
                    AtomicTransaction nextATrans = iterator.next();
                    //System.out.println("Extracting transaction from inside "+current.getTransactionName()+": "+nextATrans.getTransactionName());
                    boolean flag = this.transactions.add(nextATrans);//THROWING A NULL POINTER EXCEPTION SOMEHWERE HERE
                    /*if(flag)
                    {System.out.println("Have added "+nextATrans+ " to the array list of atomic transactions");}
                    else
                    {
                        System.out.println("Failed to add to the transaction list of atomics");
                    }*/
                }
                
                //System.out.println("Moving on to extract the next transaction for "+transaction);
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
        if(this.index<this.transactions.size())
        {
            return this.transactions.get(index++);
        }
        index++;
        return null;
    }

    @Override
    public boolean isDone()
    {
        if(index>=this.transactions.size())
        {
            return true;
        }
        return false;
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
