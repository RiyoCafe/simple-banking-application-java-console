package org.example;

public class SalaryAccount extends Account{
    final double SALARY_OPENING_LIMIT = 2000;
    final double SALARY_MAINTENANCE_LIMIT = 1000;
    public SalaryAccount(String name, String number, double balance) {
        super(name, number, balance);
        super.setAccountOpeningLimit(SALARY_OPENING_LIMIT);
        super.setAccountMaintenanceLimit(SALARY_MAINTENANCE_LIMIT);
        super.setAccountType(AccountType.SALARY);
    }
}
