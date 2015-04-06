package mt.edu.um.b_d_app;

import java.util.ArrayList;

/**
 *
 * @author Beatrix
 */
public class AccountDatabase {

    ArrayList<Account> accountList = new ArrayList<Account>(); //creating an arraylist of tuples
    //Vector accountVector = new Vector();

    AccountDatabase(){ //empty accountList = 0;
    }

    boolean addAccount(int accountNumber, String accountName, long accountBalance){
        boolean flag = false;
        Account account1;
        //for(int i = 0; i < accountList.size(); i++) {
        int i = 0;
            while(flag == false && i < accountList.size()){
                if(accountList.get(i).getAccountNumber() == accountNumber){
                    flag = true;
                }
                if(!flag){
                    account1 = new Account(accountNumber,accountName,accountBalance);
                    accountList.add(account1);
                }
                i++;
            }
        //}
        return !flag;
    }

    Account getAccount(int accountNumber){
        boolean flag = false;

        while(flag == false){
            if(accountList.get(i).getAccountNumber() == accountNumber){
                return accountList.get(i);
                flag = true;
            }
        }
        
        return  null;
    }

    int getSize(){
        return accountList.size();
    }

}