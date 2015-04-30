package mt.edu.um.b_d_app;

public abstract class Transaction {
    
    protected String name;
    
    public abstract boolean process()throws TransactionFailureException;
    
    public String getTransactionName()
    {
        return this.name;
    }
}
