package mt.edu.um.b_d_app;

import java.util.ArrayList;

/**
 *
 * @author Beatrix
 */
public class AccountDatabase {

    ArrayList<Account> accountList = new ArrayList<Account>(); //creating an arraylist of tuples
    //Vector accountVector = new Vector();

    AccountDatabase(){ //empty constructor - nothing to initialise
    }

    boolean addAccount(int accountNumber, String accountName, long accountBalance){
        Account account1;
        for(int i = 0; i < accountList.size(); i++) {
            while (accountList.get(i).getAccountNumber() != accountNumber){
                account1 = new Account(accountNumber,accountName,accountBalance);
                accountList.add(account1);
                return true;
            }
        }return false;
    }

    Account getAccount(int accountNumber){
        for(int i =0;i<accountList.size();i++){
            if(accountList.get(i).getAccountNumber() == accountNumber){
                return accountList.get(i);
            }
        }
        return  null;
    }

    int getSize(){
        return accountList.size();
    }

}