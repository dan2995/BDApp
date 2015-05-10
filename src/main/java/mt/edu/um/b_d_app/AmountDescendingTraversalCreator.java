package mt.edu.um.b_d_app;

import java.util.ArrayList;

/**
 * Created by Daniela on 08/05/2015.
 */
public class AmountDescendingTraversalCreator extends TraversalCreator {

    public ArrayList<AtomicTransaction> getTransactionsBy(Transaction transaction){

        //due to pass by reference, the array list returned will be of pointers to the original objects
        
        //fetch the iterator
        
        Iterator iterateTrans = transaction.createIterator();
        
        ArrayList<AtomicTransaction> result = new ArrayList<AtomicTransaction>();
        
        
        //Extract the atomic transactions
        while(!iterateTrans.isDone())
        {
            result.add(iterateTrans.next());
        }
        
        
        //now sort by criteria
        
        
        return result;

    }
}
