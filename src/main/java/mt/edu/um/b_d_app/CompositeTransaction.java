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
    
    public CompositeTransaction(String name)
    {
        transactionList = new ArrayList<Transaction> ();
        this.name = name;
    }
    
    public CompositeTransaction()
    {
        this("GenericTransaction");
    }
    
    public boolean addAtomicTransaction(AccountDatabase database, int src, int dst, int amount, String name)
    {
        return this.transactionList.add(new AtomicTransaction(src,dst,amount,database,name));
    }

    public boolean addCompositeTransaction(String name){
        return this.transactionList.add(new CompositeTransaction(name);
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
    
    public boolean removeTransaction(String name)
    {
        int i = 0;
        boolean found = false;
        while(i<this.transactionList.size() && !found)
        {
            if((this.transactionList.get(i)).getTranscationName().equals(name))
            {
                this.transactionList.remove(i);
                found = true;
            }
            i++;
        }
        
        return found;
    }
}
