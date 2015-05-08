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
    
    public ArrayList<AtomicTransaction> getTransactionsBy(TraversalTypes type)
    {
        TraversalCreator creator = findCreatorForType(type);
        
        return creator.getTransactionBy();
    }
    
    public ArrayList<AtomicTransaction> getTransactionsBy(TraversalTypes type, int accNo)
    {
        TraversalCreator creator = findCreatorForType(type);
        
        return creator.getTransactionBy(accNo);
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
    
}
