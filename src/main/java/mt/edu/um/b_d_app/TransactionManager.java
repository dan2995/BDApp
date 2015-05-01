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
    
    /*
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
    }*/
    
    //generic Transaction object
    public boolean processTransaction (Transaction transaction) throws TransactionFailureException
    {
        return transaction.process();
    }
    
    public int getNumberTransactionsProcessed()
    {
        return this.numTransactionsProcessed;
    }
    
    private void setNumberTransactionsProcessed()
    {
        this.numTransactionsProcessed++;
    }
    
}