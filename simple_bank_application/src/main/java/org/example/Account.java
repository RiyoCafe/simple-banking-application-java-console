package org.example;

import java.util.Date;

public class Account {
    private String name;
    private String number;
    private Date creationDate;
    private double balance;
    private AccountType accountType;
    private double accountOpeningLimit;
    private double accountMaintenanceLimit;
    public Account(String name, String number, double balance) {
        this.name = name;
        this.number = number;
        this.creationDate = new java.util.Date();
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getAccountOpeningLimit() {
        return accountOpeningLimit;
    }

    public void setAccountOpeningLimit(double accountOpeningLimit) {
        this.accountOpeningLimit = accountOpeningLimit;
    }

    public double getAccountMaintenanceLimit() {
        return accountMaintenanceLimit;
    }

    public void setAccountMaintenanceLimit(double accountMaintenanceLimit) {
        this.accountMaintenanceLimit = accountMaintenanceLimit;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", creationDate=" + creationDate +
                ", balance=" + balance +
                ", accountType=" + accountType +
                '}';
    }
}
