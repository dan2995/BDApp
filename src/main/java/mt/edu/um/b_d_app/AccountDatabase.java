package mt.edu.um.b_d_app;

import java.util.ArrayList;

public class AccountDatabase {

    private ArrayList<Account> accountList = new ArrayList<Account>();

    public AccountDatabase(){ //empty accountList = 0;
    }

    public boolean addAccount(int accountNumber, String accountName, long accountBalance){
        boolean flag = false;
        Account account1;
        int i = 0;
        while(flag == false && i < accountList.size()) {
            if (accountList.get(i).getAccountNumber() == accountNumber) {
                flag = true;
            }
            i++;
        }
        if(!flag){
            account1 = new Account(accountNumber,accountName,accountBalance);
            accountList.add(account1);
        }
        return !flag;
    }
    
    //Overloading the method
    public boolean addAccount(Account account){
        boolean flag = false;
        int i = 0;
        while(flag == false && i < accountList.size()){
            if(accountList.get(i).getAccountNumber() == account.accountNumber){
                flag = true;
            }
            i++;
        }
        if(!flag){
            accountList.add(account);
        }
        return !flag;
    }

    public Account getAccount(int accountNumber){
        for(int i = 0; i<accountList.size();i++)
        {
            if(accountList.get(i).getAccountNumber() == accountNumber)
            {
                return accountList.get(i);
            }
        }
        return  null;
    }

    public int getSize(){
        return accountList.size();
    }

}