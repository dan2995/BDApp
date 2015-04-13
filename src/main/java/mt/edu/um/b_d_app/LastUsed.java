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
public class LastUsed {
    
    private int accNumber;
    private long lastAccess;//last time in nanoseconds the account was used in a transacion
    
    LastUsed()
    {
        this(0,0);
    }
    
    //assuming time passed as a parameter as a safety measure against different executions of System.nanoTime()
    LastUsed(int accNumber, long time)
    {
        this.accNumber = accNumber;
        this.lastAccess = time;
    }
    
    public int getAccountNumber()
    {
        return this.accNumber;//skeleton code
    }
    
    public long getLastUsed()
    {
        return this.lastAccess;//skeleton code
    }
    
    public void setLastUsed(long time)
    {
        this.lastAccess = time;
    }
            
    
}
