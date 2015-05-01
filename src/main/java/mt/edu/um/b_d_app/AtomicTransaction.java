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
public class AtomicTransaction extends Transaction {
    
    private int sourceAccountNumber;
    private int destinationAccountNumber;
    private long amount;
    private AccountDatabase database;//apply the singleton pattern to the database
    //might become a redundant structure in this class
    //have a single database in the transaction manager?
    
    public AtomicTransaction(int source, int destination, int amount, AccountDatabase database, String name)
    {
        super(name);
        this.database = database;
        this.sourceAccountNumber = source;
        this.destinationAccountNumber = destination;
        this.amount = amount;
        
    }
    
     public AtomicTransaction()
    {
        //handling an invalid transaction by setting all fields to zero
        //account numbers have been confirmed to be assumed to start from one
        
        this(0,0,0, null,"GenericTransaction");
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
        
        //check neither account has been used in the last 15 seconds
        if(!this.timeRuleVerification(this.sourceAccountNumber, this.destinationAccountNumber))
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
    
    //the following are methods that are redundant for an atomic transaction but have had to be implemented to facilitate generalisation in the CompositeComponent class
    
    public Transaction getTransaction (String name)
    {
        //no lower levels to search through
        return null;
    }
    
    public boolean removeTransaction (String name)
    {
        return false;//no components to remove in an atomic transaction
    }
    
    public boolean addTransaction(String name)
    {
        return false;
    }
    
    public boolean addTransaction(AccountDatabase database, int src, int dst, int amount, String name)
    {
        return false;
    }
 
    public int getListSize()
    {
        return 0;
    }
    
    public boolean addTransaction(CompositeTransaction transaction)
    {
        return false;
    }
    
    private boolean timeRuleVerification(int src, int dst)
    //needs the database to prevent using the default TM database meber of the transaction object database is different
    {
        //locate the account in the active list if they exist
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
        
        
        
    }
    
}
