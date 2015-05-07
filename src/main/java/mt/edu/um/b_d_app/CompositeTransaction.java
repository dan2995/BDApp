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
        super(name);
        transactionList = new ArrayList<Transaction> ();
    }
    
    public CompositeTransaction()
    {
        this("GenericTransaction");
    }
    
    public boolean addTransaction(AccountDatabase database, int src, int dst, double amount, String name)
    {
        return this.transactionList.add(new AtomicTransaction(src,dst,amount,database,name));
    }
    
    public boolean addTransaction(String name)
    {
        return this.transactionList.add(new CompositeTransaction(name));
    }
    
    public boolean addTransaction(CompositeTransaction transaction)
    {
        return this.transactionList.add(transaction);
    }
    
    public boolean process() throws TransactionFailureException, InterruptedException
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
            Thread.sleep(15000);
            i++;
            
        }
        
        return !pass;
    }
    
    public boolean removeTransaction(String name)
    {
        
        //revised version for nested structure
        int i = 0;
        boolean found = false;
        while(i<this.transactionList.size() && !found)
        {
            if((this.transactionList.get(i)).getTransactionName().equals(name))
            {
                this.transactionList.remove(i);
                //search through the nested levels too
                found = true;
            }
            //check the current transactions lower levels
            else
            {
                found = this.transactionList.get(i).removeTransaction(name);
            }
            i++;
        }
        
        return found;
    }
    
    public Transaction getTransaction(String name)
    {
        int i = 0;
        while(i<this.transactionList.size())
        {
            if((this.transactionList.get(i)).getTransactionName().equals(name))
            {
                return this.transactionList.get(i);
                //search through the nested levels too
            }
            //check the current transactions lower levels
            Transaction result = this.transactionList.get(i).getTransaction(name);
            if(result!=null)
            {
               return result;
            }

            i++;
        }
        
        return null;
    }

    public int getListSize(){
        return transactionList.size();
    }
    
}
