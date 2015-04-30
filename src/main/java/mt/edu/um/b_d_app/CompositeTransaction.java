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
public class CompositeTransaction extends Transaction{
    
    private ArrayList<Transaction> transactionList;
    private String name;
    
    public CompositeTransaction(String name)
    {
        transactionList = new ArrayList<Transaction> ();
        this.name = name;
    }
    
    public CompositeTransaction()
    {
        this("GenericTransaction");
    }
    
    public boolean addAtomicTransaction(AccountDatabase database, int src, int dst, int amount)
    {
        return this.transactionList.add(new AtomicTransaction(src,dst,amount,database));
    }
    
    public boolean process() throws TransactionFailureException
    {
        int i = 0;
        boolean pass = true;
        while(i<transactionList.size() && pass)
        {
            pass = transactionList.get(i).process();
            if(!pass)
            {
                throw new TransactionFailureException("The transaction: "+transactionList.get(i).toString()+" failed to be processed.");
            }
            i++;
            
        }
        
        return !pass;
    }
    
}
