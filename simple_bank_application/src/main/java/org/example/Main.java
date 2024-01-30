package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = Bank.getInstance();
        System.out.println("Welcome!!!\nThank you for using our services!!!" +
                "\nThe following operations are available: \n" +
                "1. Create a new account\n" +
                "2. Display all accounts\n" +
                "3. Update an account\n" +
                "4. Delete an account\n" +
                "5. Deposit an amount into your account\n" +
                "6. Withdraw an amount from your account\n" +
                "7. Search for account\n" +
                "8. Exit");
        // take option from user
        Scanner scanner = new Scanner(System.in);
        String name="";
        String accountNumber = "";
        while (true) {
            System.out.println("Please enter your option: ");
            int option = scanner.nextInt();
            switch (option) {
                System.out.println("Please enter your name: ");
                name = scanner.next();

                case 1:
                    System.out.println("Please enter your account type: \n" +
                            "1. Savings\n" +
                            "2. Current\n" +
                            "3. Salary\n");
                    int accountType = scanner.nextInt();
                    String type;
                    System.out.println("Please enter your initial balance: ");
                    double initialBalance = scanner.nextDouble();
                    if(accountType == 1)    type = "SAVINGS";
                    else if(accountType == 2) type = "CURRENT";
                    else if(accountType == 3) type = "SALARY";
                    else{
                        System.out.println("Error Occurred!!!");
                        break;
                    }
                    bank.createAccount(name, initialBalance,type);
                    break;
                case 2:
                    bank.displayAccounts(name);
                    break;
                case 3:

                    bank.updateAccount();
                    break;
                case 4:
                    System.out.println("Please Enter your Account Number: ");
                    accountNumber = scanner.next();
                    boolean result = bank.deleteAccount(name,accountNumber);
                    if(result == false){
                        System.out.println("Error Occurred!!!");
                    }
                    else{
                        System.out.println("Account Deleted Successfully.");
                    }
                    break;
                case 5:
                    System.out.println("Please Enter your Account Number: ");
                    accountNumber = scanner.next();
                    System.out.println("Please Enter the amount to be deposited: ");
                    double amount = scanner.nextDouble();
                    Account newModifiedAccount = bank.depositInAccount(name,accountNumber,amount);
                    if(newModifiedAccount != null){
                        System.out.println(newModifiedAccount.toString());
                    }
                    else{
                        System.out.println("Error Occurred!!!");
                    }
                    break;
                case 6:
                    System.out.println("Please Enter your Account Number: ");
                    accountNumber = scanner.next();
                    System.out.println("Please Enter the amount to be withdrawed :");
                    double withdrawAmount = scanner.nextDouble();
                    bank.withdrawFromAccount(name,accountNumber,withdrawAmount);
                    break;
                case 7:
                    System.out.println("Please Enter your Account Number: ");
                    accountNumber = scanner.next();
                    
                    Account searchedAccount = bank.searchAccount(name,accountNumber);
                    if(searchedAccount !=null){
                        System.out.println(searchedAccount.toString());
                    }
                    else{
                        System.out.println("Error Occurred!!!");
                    }
                    break;
                case 8:
                    System.out.println("Thank you for using our services!!!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        
    }
}