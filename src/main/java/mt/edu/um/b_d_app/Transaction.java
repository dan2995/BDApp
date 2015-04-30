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
    private AccountDatabase database;//apply the singleton pattern to the database
    //might become a redundant structure in this class
    //have a single database in the transaction manager?
    
    public Transaction(int source, int destination, int amount, AccountDatabase database)
    {
        this.database = database;
        this.sourceAccountNumber = source;
        this.destinationAccountNumber = destination;
        this.amount = amount;
    }
    
     public Transaction()
    {
        //handling an invalid transaction by setting all fields to zero
        //account numbers have been confirmed to be assumed to start from one
        
        this(0,0,0, null);
    }
    
    public int getSourceAccountNumber()
    {
        return this.sourceAccountNumber;
    }
    
    /*private void setSourceAccountNumber(int accountNumber)
    {
        this.sourceAccountNumber = accountNumber;
    }*/
    
    public int getDestinationAccountNumber()
    {
        return this.destinationAccountNumber;
    }
    
    /*private void setDestinationAccountNumber(int accountNumber)
    {
        this.destinationAccountNumber = accountNumber;
    }*/
    
    public long getAmount()
    {
        return this.amount;
    }
    
    /*private void setAmount(long amount)
    {
        this.amount = amount;
    }*/
    
    //accesses the account database to look up the accounts
    public boolean process()
    {
        if(this.database==null) return false;
        
        Account source = this.database.getAccount(this.sourceAccountNumber);
        Account destination = this.database.getAccount(this.destinationAccountNumber);
        
        if(source==null || destination==null)
        {
            return false;
        }
        
        //make sure account is changing the balance
        if(source.adjustBalance(-(this.amount)) && destination.adjustBalance(this.amount))
        {
            return true;
        }
        else
        {
            return false;
        }
        //room for further check
        //split adjustBalance into checkBalance to check that therer are sufficient funds and use setBalance
    }
    
}
