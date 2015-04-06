/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mt.edu.um.b_d_app;

/**
 *
 * @author Beatrix
 */
public class Account {

    int accountNumber;
    String accountName;
    long accountBalance;

    Account(int accNumber, String accName, long accBalance){
        accountNumber = accNumber;
        accountName = accName;
        accountBalance = accBalance;
    }

    void setAccountNumber(int accountNumber){
        this.accountNumber = accountNumber;
    }
}
