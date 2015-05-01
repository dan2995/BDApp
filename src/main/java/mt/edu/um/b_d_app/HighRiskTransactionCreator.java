package mt.edu.um.b_d_app;

import java.util.ArrayList;

/**
 * Created by Daniela on 01/05/2015.
 */

/*

High risk deposits take src account 3123
High risk main transaction always have src account number 3143
For commission src 6565, dst 4444, and 10% of the amount quoted in the main transaction

*/

public class HighRiskTransactionCreator extends TransactionCreator {
    
    public Transaction createTransaction(ArrayList<Account> destinationList, ArrayList<Integer> amountList)
    {
        //Specification states that all of the compound transactions have three transactions at at level 1 (topmost level of the transaction)
        Transaction result = new CompositeTransaction("HighRiskTransaction");
        //adding some form of ID should these transactions be placed in a structure together to be searched?
        
        
    }
    
}
