package mt.edu.um.b_d_app;

import java.util.ArrayList;

/**
 * Created by Daniela on 08/05/2015.
 */
public class FilterSourceTraversalCreator extends TraversalCreator{

    public ArrayList<AtomicTransaction> getTransactionsBy(int accNo, Transaction transaction){

        //due to pass by reference, the array list returned will be of pointers to the original objects
        
        //fetch the iterator
        
        Iterator iterateTrans = transaction.createIterator();
        
        ArrayList<AtomicTransaction> result = new ArrayList<AtomicTransaction>();
        
        while(!iterateTrans.isDone())
        {
            AtomicTransaction temp = iterateTrans.next();//get the next transaction from the iterator
            if(temp.getSourceAccountNumber()==accNo)//if no account numbers mathc, then
            {
                result.add(temp);//if the source account number matches the required source account number, add to the result list
            }
        }
        
        return result;

    }
}
