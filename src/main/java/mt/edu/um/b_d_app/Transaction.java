package mt.edu.um.b_d_app;

public abstract class Transaction {
    
    private String name;
    
    Transaction(String name)
    {
        this.name = name;
    }
    
    public abstract boolean process()throws TransactionFailureException;
    
    public String getTransactionName()
    {
        return this.name;
    }
    
    public abstract Transaction getTransaction(String name);
    
    public abstract boolean removeTransaction (String name);
    
    public abstract boolean addTransaction (AccountDatabase database, int src, int dst, int amount, String name);
    
    public abstract boolean addTransaction (String name);
    
}
