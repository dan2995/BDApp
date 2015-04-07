/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mt.edu.um.b_d_app;

/**
 *
 * @author Beatrix
 */
public class TransactionManager {
    
    private int numTransactionsProcessed;
    AccountDatabase database;
    
    public TransactionManager(AccountDatabase database)
    {
        this.database = database;
        this.numTransactionsProcessed = 0;
    }
    
    public TransactionManager()
    {
        this(null);
    }
    
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
}
