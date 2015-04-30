/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mt.edu.um.b_d_app;

import java.util.ArrayList;

/**
 *
 * @author Beatrix
 */
public class CompositeTransaction extends Transaction{
    
    private ArrayList<Transaction> transactionList;
    private String name;
    
    CompositeTransaction(String name)
    {
        transactionList = new ArrayList<Transaction> ();
        this.name = name;
    }
    
    CompositeTransaction()
    {
        this("GenericTransaction");
    }
    
    public boolean process()
    {
        int i = 0;
        boolean fail = false;
        while(i<transactionList.size() && !fail)
        {
            fail = transactionList.get(i).process();
            i++;
        }
        
        return !fail;
    }
    
}
