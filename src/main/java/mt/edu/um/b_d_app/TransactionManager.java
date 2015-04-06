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
    
    public TransactionManager()
    {
        this.numTransactionsProcessed = 0;
    }
    
    public boolean processTransaction(Transaction trans)
    {
        this.setNumberTransactionsProcessed();
        return false;
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
