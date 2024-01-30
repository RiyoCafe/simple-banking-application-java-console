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
        String type="";
        System.out.println("Please enter your name: ");
        name = scanner.next();
        while (true) {
            System.out.println("Please enter your option: ");
            int option = scanner.nextInt();
            if(option<1 || option>8) continue;
            switch (option) {
                case 1:
                    System.out.println("Please enter your account type: \n" +
                            "1. Savings(Opening Limit : "+CONSTANTS.SAVINGS_OPENING_LIMIT+" , Maintenance Limit : "
                                    +CONSTANTS.SAVINGS_MAINTENANCE_LIMIT+")\n" +
                            "2. Current(Opening Limit : "+CONSTANTS.CURRENT_OPENING_LIMIT+" , Maintenance Limit : "
                            +CONSTANTS.CURRENT_MAINTENANCE_LIMIT+")\n" +
                            "3. Salary(Opening Limit : "+CONSTANTS.SALARY_OPENING_LIMIT+" , Maintenance Limit : "
                            +CONSTANTS.SALARY_MAINTENANCE_LIMIT+")\n");
                    int accountType = scanner.nextInt();

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
                    System.out.println("Account Created Successfully!!");
                    break;
                case 2:
                    bank.displayAccounts(name);
                    break;
                case 3:
                    System.out.println("Please Enter your Account Number: ");
                    accountNumber = scanner.next();
                    System.out.println("Please Enter your updated account type:\n "+
                            "1. Savings(Opening Limit : "+CONSTANTS.SAVINGS_OPENING_LIMIT+" , Maintenance Limit : "
                            +CONSTANTS.SAVINGS_MAINTENANCE_LIMIT+")\n" +
                            "2. Current(Opening Limit : "+CONSTANTS.CURRENT_OPENING_LIMIT+" , Maintenance Limit : "
                            +CONSTANTS.CURRENT_MAINTENANCE_LIMIT+")\n" +
                            "3. Salary(Opening Limit : "+CONSTANTS.SALARY_OPENING_LIMIT+" , Maintenance Limit : "
                            +CONSTANTS.SALARY_MAINTENANCE_LIMIT+")\n");
                    int updated_option = scanner.nextInt();
                    if(updated_option == 1) type = "SAVINGS";
                    else if(updated_option == 2) type = "CURRENT";
                    else if (updated_option ==3)  type = "SALARY";
                    else{
                        System.out.println("Error Occurred!!!");
                        break;
                    }
                    Account account = bank.updateExistingAccount(name,accountNumber,type);
                    if(account != null){
                        System.out.println("Account Updated Successfully !");
                        System.out.println(account.toString());
                    }
                    else{
                        System.out.println("Error Occurred!!!");
                    }
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
                    System.out.println("Thank you for using our service!!!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        
    }
}