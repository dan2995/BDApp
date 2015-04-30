/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mt.edu.um.b_d_app;

//add the imports

/**
 *
 * @author Beatrix
 */
public class CompositeTransactionTest {

    AccountDatabase database1;
    Account account1;
    Account account2;
    Account account3;


    @Before
    public void SetUp() {
        database1 = new AccountDatabase();
        account1 = new Account(1, "myAccount1", 100);
        account2 = new Account(2, "myAccount2", 200);
        account3 = new Account(3, "myAccount3", 300);
        TransactionManager(database1);
        CompositeTransaction("TransactionTest");
        CompositeTransaction();
    }

    @Test
    public void addAtomicTransactionTest(){
        assertEquals(true,account.adjustBalance(30));
    }





    //begin
    /*Create
    new accounts (three)
    new composite transaction with a given name
    new composite transaction with no parameters
    have a transaction manager
    have an account database*/
    
    //test addAtomicTransaction
    
    //test addCompositeTransaction
    
    //test getTransaction
    
    //test process
    
    //test removeTransaction
    
}
