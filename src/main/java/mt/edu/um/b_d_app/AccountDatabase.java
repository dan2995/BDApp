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
        for(int i = 0; i < accountVector.size(); i++) {
            while (accountVector[i].getAccountNumber() != accountNumber){
                account1 = new Account(accountNumber,accountName,accountBalance);
                accountVector.add(account1);
                return true;
            }
        }return false;
    }


}