package mt.edu.um.b_d_app;

public class Account {

    private int accountNumber;
    private String accountName;
    private double accountBalance;
    private long lastAccess;

    public Account(int accNumber, String accName, double accBalance){
        accountNumber = accNumber;
        accountName = accName;
        accountBalance = accBalance;
        lastAccess = 0;
    }

    public int getAccountNumber(){
        return accountNumber;
    }

    public String getAccountName(){
        return accountName;
    }

    private void setAccountBalance(double accountBalance){
        this.accountBalance = accountBalance;
    }

    public double getAccountBalance(){
        return accountBalance;
    }

    //the method that takes the amount, invoked by Transaction.process()
    public boolean adjustBalance(double amount){
        if((this.accountBalance + amount) >= 0)
        {
            this.setAccountBalance(this.getAccountBalance()+amount);
            return true;
        }
        return false;
    }

    public void setLastAccess(long lastAccessInput){
        this.lastAccess = lastAccessInput;
    }

    public long getLastAccess(){
        return lastAccess;
    }
}
