package mt.edu.um.b_d_app;

import java.util.ArrayList;
import java.util.Collections;

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
        
        //now sort by criteria using Bubble sort
        boolean stop = false;
        while(!stop)
        {
            stop = true;
            for(int i = 0; i<result.size()-1;i++)
            {
                if(result.get(i).getAmount()<result.get(i+1).getAmount())//if the first is less than the second
                {
                    Collections.swap(result, i, i+1);
                    stop = false;
                }
            }
            
        }
        
        return result;

    }
}
