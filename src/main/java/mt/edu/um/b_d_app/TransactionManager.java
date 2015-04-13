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
public class TransactionManager {
    
    private int numTransactionsProcessed;
    AccountDatabase database;
    
    //Proposed handling of the 15 second rule
    /*
    Have an array list that stores instances of the class LastUsed, which has the account number and the time of last use 
    When handling a transaction, traverse the array list. If the current time is more than 15 seconds later, remove the object from the list
    If the required account numbers have no corresponding object or have been found and deleted, proceed with the transaction and create
    new objects after the balances have been altered
    Else return false
    */
    
    private ArrayList<LastUsed> active = new ArrayList<LastUsed> ();
    
    public TransactionManager(AccountDatabase database)
    {
        this.database = database;
        this.numTransactionsProcessed = 0;
    }
    
    public TransactionManager()
    {
        this(null);
    }
    
    //make sure that src is never equal to dst
    
    public boolean processTransaction(int src, int dst, int amount)
    {
        Transaction new_t = new Transaction(src,dst,amount,database);
        boolean flag = new_t.process();
        if(flag)
        {
            this.setNumberTransactionsProcessed();
        }
        return flag;
    }
    
    public boolean processTransaction(Transaction transaction)
    {
        boolean flag = transaction.process();
        if(flag)
        {
            this.setNumberTransactionsProcessed();
        }
        return flag;
    }
    
    public int getNumberTransactionsProcessed()
    {
        return this.numTransactionsProcessed;
    }
    
    private void setNumberTransactionsProcessed()
    {
        this.numTransactionsProcessed++;
    }
    
    public boolean timeRuleVerification(int src, int dst)
    {
        //locate the account in the active list if they exist
        //if neither exists
        int i = 0;
        boolean stop = false;
        while(i<active.size() && (!stop))
        {
            int currentElement = active.get(i).getAccountNumber();
            if(currentElement == src || currentElement == dst)
            {
                long timestamp = active.get(i).getLastUsed();
                long now = System.nanoTime();
                if((timestamp+15)>=now)//15 seconds has not passed yet
                {
                    stop = true;
                }
            }
        }
        
        return !stop;
    }
}
