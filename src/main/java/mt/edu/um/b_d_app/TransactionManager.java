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

//UPDATE FOR THE NEW TRANSACTION STRUCTURE

public class TransactionManager {
    
    private int numTransactionsProcessed;
    //AccountDatabase database;
    //private ArrayList<LastUsed> active = new ArrayList<LastUsed> ();
    
    //either have a database in the transaction object or here in the transaction manager
    //if in the transaction object assumes that the transactions come from the same database 
    public TransactionManager(AccountDatabase database)
    {
        //this.database = database;
        this.numTransactionsProcessed = 0;
    }
    
    public TransactionManager()
    {
        this(null);
    }
    
    //make sure that src is never equal to dst
    
    public boolean processTransaction(int src, int dst, int amount, String name)
    {
        if(this.timeRuleVerification(src, dst))
        {
            //refactor into its own method and pass to the implementation of processTransacion seen below
            AtomicTransaction new_t = new AtomicTransaction(src,dst,amount,database, name);
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
    
    public boolean processTransaction(AtomicTransaction transaction)
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
    
    //generic Transaction object
    public boolean processTransaction (Transaction transaction)
    {
        
    }
    
    public int getNumberTransactionsProcessed()
    {
        return this.numTransactionsProcessed;
    }
    
    private void setNumberTransactionsProcessed()
    {
        this.numTransactionsProcessed++;
    }
    
    /*
    //an improvement would be to remove any element of active that has an expired timestamp
    private boolean timeRuleVerification(int src, int dst)
    //needs the database to prevent using the default TM database meber of the transaction object database is different
    {
        //locate the account in the active list if they exist
        //if neither exists
        int i = 0;
        boolean stop = false;
        long now = (System.nanoTime()/1000);
        //assuming that processing of the check below is not sufficient enough to make an account record checked later in the loop valid
        //i.e. that the below procesing time is negligible
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
    }*/
    
    private void updateActive (int src, int dst)
    {
        long now = System.nanoTime();
        active.add(new LastUsed(src, now));
        active.add(new LastUsed(dst, now)); 
    }
}