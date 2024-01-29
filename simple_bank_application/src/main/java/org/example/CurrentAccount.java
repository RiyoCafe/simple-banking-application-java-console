package org.example;

public class CurrentAccount extends Account{
    final double CURRENT_OPENING_LIMIT = 500;
    final double CURRENT_MAINTENANCE_LIMIT=200;
    public CurrentAccount(String name, String number, double balance) {
        super(name, number, balance);
        super.setAccountOpeningLimit(CURRENT_OPENING_LIMIT);
        super.setAccountMaintenanceLimit(CURRENT_MAINTENANCE_LIMIT);
        super.setAccountType(AccountType.CURRENT);
    }
}
