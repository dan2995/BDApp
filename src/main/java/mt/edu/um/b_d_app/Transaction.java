package mt.edu.um.b_d_app;

public abstract class Transaction{
    
    private String name;
    
    Transaction(String name)
    {
        this.name = name;
    }
    
    public abstract boolean process()throws TransactionFailureException,InterruptedException;//operation method
    
    public String getTransactionName()
    {
        return this.name;
    }
    
    public abstract Transaction getTransaction(String name);//getChild method
    
    public abstract boolean removeTransaction (String name);//remove method
    
    public abstract boolean addTransaction (AccountDatabase database, int src, int dst, int amount, String name);//add method
    
    public abstract boolean addTransaction (String name);//add method
    
    public abstract boolean addTransaction(CompositeTransaction transaction);
    
    public abstract int getListSize();
    
}
