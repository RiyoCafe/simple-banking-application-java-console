package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
// Assuming that every user has unique name to reduce its complexity.
public final class Bank {
    private String name;
    private static Bank INSTANCE;
    private HashMap<String, List<Account>> userToAccount;
    private HashMap<String,Account> accountNumberToAccount;
    private int accountCnt;

    private Bank(){
        accountCnt = 0;
        userToAccount = new HashMap<>();
        accountNumberToAccount = new HashMap<>();
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
        boolean validation = createdAccount.validateOpening();
        if(validation == false) return false;
        if(userToAccount.containsKey(name)){
            userToAccount.get(name).add(createdAccount);
        }
        else{
            List<Account> accounts = new ArrayList<>();
            accounts.add(createdAccount);
            userToAccount.put(name,accounts);
        }

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
    private boolean checkMigration(Account account){
        return account.validateMigrate();
    }
    public Account updateExistingAccount(String name,String accountNumber,String type){
        if(checkExistence(name, accountNumber)){
            Account account = accountNumberToAccount.get(accountNumber);
            AccountType updatedAccountType = AccountType.valueOf(type);
            Account temp = null;
            if(updatedAccountType == AccountType.SAVINGS){
                account = new SavingsAccount(name,accountNumber,account.getBalance());
            }
            else if(updatedAccountType == AccountType.SALARY){
                account = new SalaryAccount(name,accountNumber,account.getBalance());
            }
            else{
                account = new CurrentAccount(name,accountNumber,account.getBalance());
            }

            if(checkMigration(account) == false) return null;
//            AccountType updatedAccountType = AccountType.valueOf(type);
//            account.setAccountType(updatedAccountType);
            accountNumberToAccount.put(accountNumber,account);
            List<Account> accounts = userToAccount.get(name);
            userToAccount.remove(name);
            for(int i =0;i<accounts.size();i++){
                if(accounts.get(i).getNumber().equals(accountNumber)){
                    accounts.remove(i);
                    break;
                }
            }
            accounts.add(account);
            userToAccount.put(name,accounts);
            return account;

        }
        return null;
    }
    public boolean deleteAccount(String name,String accountNumber){
        if(checkExistence(name,accountNumber))  {
            accountNumberToAccount.remove(accountNumber);
            List<Account> accounts = userToAccount.get(name);
            for(int i = 0;i<accounts.size();i++){
                if(accounts.get(i).getNumber().equals(accountNumber)){
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
        String accountNumber = account.getNumber();
        accountNumberToAccount.put(accountNumber,account);

        List<Account> existingAccounts = userToAccount.get(account.getName());
        userToAccount.remove(account.getName());
        for(int i=0;i<existingAccounts.size();i++){
            if(existingAccounts.get(i).getNumber().equals(accountNumber)){
                existingAccounts.remove(i);
                break;
            }
        }
        existingAccounts.add(account);
        userToAccount.put(account.getName(),existingAccounts);
    }
    public Account depositInAccount(String name,String accountNumber,double amount){
        if(checkExistence(name, accountNumber)){
            Account account = accountNumberToAccount.get(accountNumber);
            double newBalance = account.getBalance() + amount;
            updateAccount(account,newBalance);
            return account;
        }
        return null;
    }
    public boolean withdrawFromAccount(String name,String accountNumber,double amount){
        if(checkExistence(name, accountNumber)){
            Account account = accountNumberToAccount.get(accountNumber);
            double newAvailableBalance = account.getBalance() - amount;
            boolean validateWithdraw = account.validateDecrease(amount);
            if(validateWithdraw == false)   {
                System.out.println("You have a maintenance limit of "+account.getAccountMaintenanceLimit()+" taka");
                return false;
            }
            updateAccount(account,newAvailableBalance);
            System.out.println("Here is your "+amount+" taka.");
            return true;

        }
        System.out.println("Error occurred!!!");
        return false;
    }
    public Account searchAccount(String name,String accountNumber)
    {
        if(checkExistence(name, accountNumber)){
            Account account = accountNumberToAccount.get(accountNumber);
            return account;
        }
        return null;
    }

}
