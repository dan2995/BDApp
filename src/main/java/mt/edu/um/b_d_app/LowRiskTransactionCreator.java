package mt.edu.um.b_d_app;

import java.util.ArrayList;

/*

Low risk deposits take src account 8665
Low risk main transaction always have src account number 3133
For commission src 6588, dst 4445, and 5% of the amount quoted in the main transaction

*/

public class LowRiskTransactionCreator extends TransactionCreator {

    
    int CommissionSourceAccountNo = 6588;
    int CommissionDstAccountNo = 4445;
    double commissionPercentage = 0.05;

    int DepositSourceAccountNo = 8665;
    int MainTransSourceAccountNo = 3133;


    public Transaction createTransaction(Account depositDSTAccount, double depositAmount,ArrayList<Account> destinationList, ArrayList<Double> amountList,AccountDatabase database)
    {
        //Specification states that all of the compound transactions have three transactions at at level 1 (topmost level of the transaction)
        Transaction result = new CompositeTransaction("LowRiskTransaction", RiskTypes.LOW);
        //adding some form of ID should these transactions be placed in a structure together to be searched?

        //There is only one deposit transaction with o known destination account number
        result.addTransaction(database, DepositSourceAccountNo, depositDSTAccount.getAccountNumber(), depositAmount, "DepositTransaction", RiskTypes.LOW);

        //creating the main compound transaction and the commsion compoun transaction
        CompositeTransaction main = new CompositeTransaction("MainTransaction", RiskTypes.LOW);
        CompositeTransaction commission = new CompositeTransaction("CommissionTransaction", RiskTypes.LOW);
        for(int i = 0; i<destinationList.size();i++)
        {
            main.addTransaction(database, MainTransSourceAccountNo, destinationList.get(i).getAccountNumber(), amountList.get(i), "MTransaction"+i, RiskTypes.LOW);
            commission.addTransaction(database, this.CommissionSourceAccountNo, this.CommissionDstAccountNo, amountList.get(i)*this.commissionPercentage, "CTransaction"+i, RiskTypes.LOW);
        }

        result.addTransaction(main);
        result.addTransaction(commission);
        
        return result;
    }

    public int getCommissionSourceAccountNo()
    {
        return this.CommissionSourceAccountNo;
    }

    public int getCommissionDstAccountNo()
    {
        return this.CommissionDstAccountNo;
    }

    public double getCommissionPercentage()
    {
        return this.commissionPercentage;
    }

    public int getDepositSourceAccountNo()
    {
        return this.DepositSourceAccountNo;
    }

    public int getMainTransSourceAccountNo()
    {
        return this.MainTransSourceAccountNo;
    }
}
