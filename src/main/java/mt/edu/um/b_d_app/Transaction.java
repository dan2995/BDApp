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
public class Transaction {
    
    private int sourceAccountNumber;
    private int destinationAccountNumber;
    private long amount;
    
    public Transaction(int source, int destination, int amount)
    {
        this.sourceAccountNumber = source;
        this.destinationAccountNumber = destination;
        this.amount = amount;
    }
    
     public Transaction()
    {
        //handling an invalid transaction by setting all fields to zero
        //account numbers have been confirmed to be assumed to start from one
        
        this(0,0,0);
    }
    
    public int getSourceAccountNumber()
    {
        return this.sourceAccountNumber;
    }
    
    public void setSourceAccountNumber(int accountNumber)
    {
        this.sourceAccountNumber = accountNumber;
    }
    
    public int getDestinationAccountNumber()
    {
        return this.destinationAccountNumber;
    }
    
    public void setDestinationAccountNumber(int accountNumber)
    {
        this.destinationAccountNumber = accountNumber;
    }
    
    public long getAmount()
    {
        return this.amount;
    }
    
    public void setAmount(long amount)
    {
        this.amount = amount;
    }
    
    //still not sure what it does
    public boolean process()
    {
        return false;
    }
    
}
