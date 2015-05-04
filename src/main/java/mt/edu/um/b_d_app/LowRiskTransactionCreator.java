package mt.edu.um.b_d_app;

import java.util.ArrayList;

/*

High risk deposits take src account 8665
High risk main transaction always have src account number 3133
For commission src 6588, dst 4445, and 5% of the amount quoted in the main transaction

*/

public class LowRiskTransactionCreator extends TransactionCreator {

    int CommissionSourceAccountNo = 6588;
    int CommissionDstAccountNo = 4445;
    double commissionPercentage = 0.05;

    int DepositSourceAccountNo = 8665;
    int MainTransSourceAccountNo = 3133;

    //temporary default deposit account is Unknown
    int Unknown = 0;
    int Amount = 0;

    public Transaction createTransaction(ArrayList<Account> destinationList, ArrayList<Double> amountList,AccountDatabase database)
    {
        //Specification states that all of the compound transactions have three transactions at at level 1 (topmost level of the transaction)
        Transaction result = new CompositeTransaction("LowRiskTransaction");
        //adding some form of ID should these transactions be placed in a structure together to be searched?

        //There is only one deposit transaction with o known destination account number
        result.addTransaction(database, DepositSourceAccountNo, Unknown, Amount, "DepositTransaction");

        //creating the main compound transaction and the commsion compoun transaction
        CompositeTransaction main = new CompositeTransaction("MainTransaction");
        CompositeTransaction commission = new CompositeTransaction("CommissionTransaction");
        for(int i = 0; i<destinationList.size();i++)
        {
            main.addTransaction(database, MainTransSourceAccountNo, destinationList.get(i).getAccountNumber(), amountList.get(i), "MTransaction"+i);
            commission.addTransaction(database, this.CommissionSourceAccountNo, this.CommissionDstAccountNo, amountList.get(i)*this.commissionPercentage, "CTransaction"+i);
        }


        return result;
    }

}
