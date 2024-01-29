package org.example;

public class SavingsAccount extends Account {
    final double SAVINGS_OPENING_LIMIT = 100000;
    final double SAVINGS_MAINTENANCE_LIMIT = 50000;

    public SavingsAccount(String name, String number, double balance) {
        super(name, number, balance);
        super.setAccountOpeningLimit(SAVINGS_OPENING_LIMIT);
        super.setAccountMaintenanceLimit(SAVINGS_MAINTENANCE_LIMIT);
        super.setAccountType(AccountType.SAVINGS);
    }
}
