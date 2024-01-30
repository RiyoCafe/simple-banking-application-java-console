package org.example;

public class SavingsAccount extends Account {

    public SavingsAccount(String name, String number, double balance) {
        super(name, number, balance);
        super.setAccountOpeningLimit(CONSTANTS.SAVINGS_OPENING_LIMIT);
        super.setAccountMaintenanceLimit(CONSTANTS.SAVINGS_MAINTENANCE_LIMIT);
        super.setAccountType(AccountType.SAVINGS);
    }
}
