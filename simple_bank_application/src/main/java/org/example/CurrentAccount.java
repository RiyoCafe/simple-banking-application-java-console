package org.example;

public class CurrentAccount extends Account{

    public CurrentAccount(String name, String number, double balance) {
        super(name, number, balance);
        super.setAccountOpeningLimit(CONSTANTS.CURRENT_OPENING_LIMIT);
        super.setAccountMaintenanceLimit(CONSTANTS.CURRENT_MAINTENANCE_LIMIT);
        super.setAccountType(AccountType.CURRENT);
    }
}
