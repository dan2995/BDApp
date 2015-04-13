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
        if(this.timeRuleVerification(src, dst))
        {
            Transaction new_t = new Transaction(src,dst,amount,database);
            boolean flag = new_t.process();
            if(flag)
            {
                this.setNumberTransactionsProcessed();
                this.updateActive(src, dst);
            }
            return flag;
        }
        else
        {
            return false;
        }
    }
    
    public boolean processTransaction(Transaction transaction)
    {
        if(this.timeRuleVerification(transaction.getSourceAccountNumber(), transaction.getDestinationAccountNumber()))
        {
            boolean flag = transaction.process();
            if(flag)
            {
                this.setNumberTransactionsProcessed();
                this.updateActive(transaction.getSourceAccountNumber(), transaction.getDestinationAccountNumber());
            }
            return flag;
        }
        else
        {
            return false;
        }
    }
    
    public int getNumberTransactionsProcessed()
    {
        return this.numTransactionsProcessed;
    }
    
    private void setNumberTransactionsProcessed()
    {
        this.numTransactionsProcessed++;
    }
    
    private boolean timeRuleVerification(int src, int dst)
    {
        //locate the account in the active list if they exist
        //if neither exists
        int i = 0;
        boolean stop = false;
        long now = System.nanoTime();
        while((i<active.size()) && (!stop))
        {
            int currentElement = (active.get(i)).getAccountNumber();
            if((currentElement == src) || (currentElement == dst))
            {
                long timestamp = (active.get(i)).getLastUsed();    
                long passPoint = (timestamp/1000) + 15000000;
                if(passPoint > now)//15 seconds has not passed yet (*10^6 to convert to micro seconds)
                {
                    stop = true;
                }
                else//transaction may proceed
                {
                    active.remove(i);
                    i--;//counteract the increment due to the shift of the elements
                }    
            }
            i++;
               
        }
        
        return !stop;
    }
    
    private void updateActive (int src, int dst)
    {
        long now = System.nanoTime();
        active.add(new LastUsed(src, now));
        active.add(new LastUsed(dst, now)); 
    }
}