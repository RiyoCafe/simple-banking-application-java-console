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

    public boolean createAccount(String name, double balance,String type){
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
        if(createdAccount.getAccountOpeningLimit()>balance) return false;
        userToAccount.get(name).add(createdAccount);
        accountNumberToAccount.put(newAccountUuid,createdAccount);
        return true;

    }
    public void displayAccounts(String name){
        List<Account> accounts = userToAccount.get(name);
        for(int i=0;i<accounts.size();i++){
            System.out.println(accounts.get(i).toString());
        }
    }
    private boolean checkExistence(String name, String accountNumber){
        Account account = accountNumberToAccount.get(accountNumber);
        if(account == null) return false;
        if(!account.getName().equals(name)) return false;
        return true;
    }
    public boolean updateAccount(String name,String accountNumber){
        if(checkExistence(name, accountNumber)){

        }
        return true;
    }
    public boolean deleteAccount(String name,String accountNumber){
        if(checkExistence(name,accountNumber))  {
            accountNumberToAccount.remove(accountNumber);
            List<Account> accounts = userToAccount.get(name);
            for(int i = 0;i<accounts.size();i++){
                if(accounts.get(i).getName().equals(name)){
                    accounts.remove(i);
                    break;
                }
            }
            userToAccount.remove(name);
            if(accounts != null)    userToAccount.put(name,accounts);
            return true;
        }
        return false;
    }
    private void updateAccount(Account account, double newBalance){
        account.setBalance(newBalance);

        accountNumberToAccount.put(account.getNumber(),account);

        List<Account> existingAccounts = userToAccount.get(name);
        userToAccount.remove(name);
        for(int i=0;i<existingAccounts.size();i++){
            if(existingAccounts.get(i).getName().equals(name)){
                existingAccounts.remove(i);
                break;
            }
        }
        existingAccounts.add(account);
        userToAccount.put(name,existingAccounts);
    }
    public boolean depositInAccount(String name,String accountNumber,double amount){
        if(checkExistence(name, accountNumber)){
            Account account = accountNumberToAccount.get(accountNumber);
            double newBalance = account.getBalance() + amount;
            updateAccount(account,newBalance);
            return true;
        }
        return false;
    }
    public boolean withdrawFromAccount(String name,String accountNumber,double amount){
        if(checkExistence(name, accountNumber)){
            Account account = accountNumberToAccount.get(accountNumber);
            double newAvailableBalance = account.getBalance() - amount;
            if(newAvailableBalance< account.getAccountMaintenanceLimit())   {
                System.out.println("You have a maintenance limit of "+account.getAccountMaintenanceLimit()+" taka");
                return false;
            }
            updateAccount(account,newAvailableBalance);
            return true;

        }
        System.out.println("Error occurred!!!");
        return false;
    }

}
