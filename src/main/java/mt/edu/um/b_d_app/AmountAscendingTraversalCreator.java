package mt.edu.um.b_d_app;

import java.util.ArrayList;

/**
 * Created by Daniela on 08/05/2015.
 */
public class AmountAscendingTraversalCreator extends TraversalCreator {

    public ArrayList<AtomicTransaction> getTransactionsBy(Transaction transaction){
        
        //due to pass by reference, the array list returned will be of pointers to the original objects
        
        //fetch the iterator
        
        Iterator iterateTrans = transaction.createIterator();
        
        AtomicTransaction result [] = new ArrayList<AtomicTransaction>();
        
        
        //Extract the atomic transactions
        while(!iterateTrans.isDone())
        {
            result.add(iterateTrans.next());
        }
        
        
        //now sort by criteria
        boolean stop = false;
        while(!stop)
        {
            for(int i = 0; i<result.size()-1;i++)
            {
                if(result.get(i).getAmount()>result.get(i+1).getAmount())
                {
                    AtomicTransaction temp = result.get(i+1);
                    result.
                }
            }
            
        }
        
        return result;

    }
}
