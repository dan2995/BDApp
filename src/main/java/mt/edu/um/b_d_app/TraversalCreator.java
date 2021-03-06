/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mt.edu.um.b_d_app;

import java.util.ArrayList;

/**
 *
 * @author Beatrix
 */
public class TraversalCreator {
    
    public ArrayList<AtomicTransaction> getTransactionsBy(TraversalTypes type, Transaction transaction)
    {
        TraversalCreator creator = findCreatorForType(type);
        
        return creator.getTransactionsBy(transaction);
    }
    
    public ArrayList<AtomicTransaction> getTransactionsBy(TraversalTypes type, int accNo, Transaction transaction)
    {
        TraversalCreator creator = findCreatorForType(type);
        
        return creator.getTransactionsBy(accNo, transaction);
    }
    
    private TraversalCreator findCreatorForType(TraversalTypes type)
    {
        switch(type)
        {
            case AMOUNTASCENDING:
                return new AmountAscendingTraversalCreator();
            case AMOUNTDESCENDING:
                return new AmountDescendingTraversalCreator();
            case FILTERSOURCEACCOUNT:
                return new FilterSourceTraversalCreator();
            default:
                return null;
        }
    }
    
    public ArrayList<AtomicTransaction> getTransactionsBy(Transaction transaction)
    {
        return null;
    }
    
    public ArrayList<AtomicTransaction> getTransactionsBy(int accNo, Transaction transaction)
    {
        return null;
    }
    
}
