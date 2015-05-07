package mt.edu.um.b_d_app;

import java.util.ArrayList;

/*

High risk deposits take src account 3123
High risk main transaction always have src account number 3143
For commission src 6565, dst 4444, and 10% of the amount quoted in the main transaction

*/

public class HighRiskTransactionCreator extends TransactionCreator {
    
    private int CommissionSourceAccountNo = 6565;
    private int CommissionDstAccountNo = 4444;
    private double commissionPercentage = 0.1;
    
    private int DepositSourceAccountNo = 3123;
    private int MainTransSourceAccountNo = 3143;
    
    
    public Transaction createTransaction(Account depositDSTAccount, float depositAmount, ArrayList<Account> destinationList, ArrayList<Double> amountList,AccountDatabase database)
    {
        //Specification states that all of the compound transactions have three transactions at at level 1 (topmost level of the transaction)
        Transaction result = new CompositeTransaction("HighRiskTransaction");
        //adding some form of ID should these transactions be placed in a structure together to be searched?
        
        //There is only one deposit transaction with o known destination account number
        result.addTransaction(database, DepositSourceAccountNo, depositDSTAccount.getAccountNumber(), depositAmount, "DepositTransaction");
        
        //creating the main compound transaction and the commsion compoun transaction
        CompositeTransaction main = new CompositeTransaction("MainTransaction");
        CompositeTransaction commission = new CompositeTransaction("CommissionTransaction");
        for(int i = 0; i<destinationList.size();i++)
        {
            main.addTransaction(database, MainTransSourceAccountNo, destinationList.get(i).getAccountNumber(), amountList.get(i), "MTransaction"+i);
            commission.addTransaction(database, this.CommissionSourceAccountNo, this.CommissionDstAccountNo, amountList.get(i)*this.commissionPercentage, "CTransaction"+i);
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
