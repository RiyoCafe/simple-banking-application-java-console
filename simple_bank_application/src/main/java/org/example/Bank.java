package org.example;

import java.util.HashMap;
import java.util.List;

public final class Bank {
    private String name;
    private static Bank INSTANCE;
    private HashMap<String, List<Account>> userToAccount;
    private HashMap<String,Account> accountNumberToAccount;
    private int accountCnt;

    private Bank(){
        accountCnt = 0;
    }
    public static Bank getInstance(){
        if(INSTANCE == null)    INSTANCE = new Bank();
        return INSTANCE;
    }

    public void createAccount(String name, double balance,String type){
        // The new account number will be existing maximum account number + 1
        accountCnt++;
        String newAccountUuid = Integer.toString(accountCnt);

        AccountType accountType = AccountType.valueOf(type);
        Account createdAccount ;
        if(accountType == AccountType.SAVINGS){
            createdAccount = new SavingsAccount(name,newAccountUuid,balance);
        }
        else if(accountType == AccountType.SALARY){
            createdAccount = new SalaryAccount(name,newAccountUuid,balance);
        }
        else{
            createdAccount = new CurrentAccount(name,newAccountUuid,balance);
        }
        userToAccount.get(name).add(createdAccount);
        accountNumberToAccount.put(newAccountUuid,createdAccount);

    }
    public void displayAccounts(String name){
        List<Account> accounts = userToAccount.get(name);
        for(int i=0;i<accounts.size();i++){
            System.out.println(accounts.get(i).toString());
        }
    }

}
