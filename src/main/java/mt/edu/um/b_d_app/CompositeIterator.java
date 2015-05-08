package mt.edu.um.b_d_app;

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
                Transaction current = transaction.getTransaction(i);
                
            }
        }
        this.index = 0;
    }

    public Transaction first()
    {
        
    }

    public Transaction next();

    public boolean isDone();

    public Transaction currentItem();
}
