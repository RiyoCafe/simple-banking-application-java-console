package org.example;

public class SalaryAccount extends Account{

    public SalaryAccount(String name, String number, double balance) {
        super(name, number, balance);
        super.setAccountOpeningLimit(CONSTANTS.SALARY_OPENING_LIMIT);
        super.setAccountMaintenanceLimit(CONSTANTS.SALARY_MAINTENANCE_LIMIT);
        super.setAccountType(AccountType.SALARY);
    }
}
