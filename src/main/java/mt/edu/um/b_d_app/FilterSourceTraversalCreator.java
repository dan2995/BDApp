package mt.edu.um.b_d_app;

import java.util.ArrayList;

/**
 * Created by Daniela on 08/05/2015.
 */
public class FilterSourceTraversalCreator extends TraversalCreator{
    
    @Override
    public ArrayList<AtomicTransaction> getTransactionsBy(int accNo, Transaction transaction){

        //due to pass by reference, the array list returned will be of pointers to the original objects
        
        //fetch the iterator
        
        Iterator iterateTrans = transaction.createIterator();
        
        System.out.println("Have created the iterator");
        
        ArrayList<AtomicTransaction> result = new ArrayList<AtomicTransaction>();
        System.out.println("Have initialised the result array list, now of size "+result.size());
        
        
        while(!iterateTrans.isDone())
        {
            AtomicTransaction temp = iterateTrans.next();//get the next transaction from the iterator
            
            System.out.println("The transaction just extracted is "+temp+ " with src account number "+temp.getSourceAccountNumber());
            
            if(temp.getSourceAccountNumber()==accNo)//if no account numbers mathc, then
            {
                result.add(temp);//if the source account number matches the required source account number, add to the result list
                System.out.println("adding transaction "+result.get(result.size()-1));
            }
        }
        
        System.out.println("Exiting the filter traversal creator method");
        
        return result;

    }
}
