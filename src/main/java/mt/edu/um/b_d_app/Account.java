package mt.edu.um.b_d_app;

public class Account {

    int accountNumber;
    String accountName;
    long accountBalance;

    Account(int accNumber, String accName, long accBalance){
        accountNumber = accNumber;
        accountName = accName;
        accountBalance = accBalance;
    }

    private void setAccountNumber(int accountNumber){
        this.accountNumber = accountNumber;
    }

    int getAccountNumber(){
        return accountNumber;
    }

    private void setAccountName(String accountName){
        this.accountName = accountName;
    }

    String getAccountName(){
        return accountName;
    }

    private void setAccountBalance(long accountBalance){
        this.accountBalance = accountBalance;
    }

    long getAccountBalance(){
        return accountBalance;
    }

    //the method that takes the amount, invoked by Transaction.process()
    boolean adjustBalance(long amount){
        if((this.accountBalance + amount) >= 0)
        {
            this.setAccountBalance(this.getAccountBalance()+amount);
            return true;
        } 
        //else return false;
        return false;
    }
}
