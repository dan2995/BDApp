package mt.edu.um.b_d_app;

public abstract class Transaction {
    
    private String name;
    private RiskTypes type;
    
    Transaction(String name, RiskTypes type)
    {
        this.name = name;
        this.type = type;
    }
    
    public abstract boolean process()throws TransactionFailureException, InterruptedException;//operation method
    
    public String getTransactionName()
    {
        return this.name;
    }
    
    public RiskTypes getType ()
    {
        return this.type;
    }
    
    public abstract Transaction getTransaction(String name);//getChild method
    
    public abstract boolean removeTransaction (String name);//remove method
    
    public abstract boolean addTransaction (AccountDatabase database, int src, int dst, double amount, String name, RiskTypes type);//add method
    
    public abstract boolean addTransaction (String name, RiskTypes type);//add method
    
    public abstract boolean addTransaction(CompositeTransaction transaction);
    
    public abstract int getListSize();

    //public abstract Iterator  createIterator(){

}
