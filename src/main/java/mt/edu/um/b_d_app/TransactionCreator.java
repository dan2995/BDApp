package mt.edu.um.b_d_app;
import java.util.ArrayList;

/**
 * Created by Daniela on 01/05/2015.
 */
public class TransactionCreator {

    //transaction manager will receive list of account numbers and find the accounts
    //move database back to transaction manager
    public Transaction createTransaction(RiskTypes type, ArrayList<Account> destinationList, ArrayList<Double> amountList, AccountDatabase database){
        
        TransactionCreator creator = findCreatorForTypeOfTransaction(type);
        
        if(creator==null)//return a null transaction of no creator for the type was found
        {
            return null;
        }
        
        //Assuming a correct creator was found
        return creator.createTransaction(destinationList, amountList, database);
        
    }
    
    public Transaction createTransaction()
    {
        return null;//set to null as no default type was specified in the assignment specification
    }
    
    public Transaction createTransaction(ArrayList<Account> destinationList, ArrayList<Double> amountList,AccountDatabase database){
        
        return null;//no type specified for this level in the transaction creator hierarchy
        
    }
    
    private TransactionCreator findCreatorForTypeOfTransaction (RiskTypes type)
    {
        if(type == RiskTypes.HIGH){
            
            return new HighRiskTransactionCreator();
        }

        if(type == RiskTypes.LOW){
            
            return new LowRiskTransactionCreator();
        }
        
        return null;
    }
}
