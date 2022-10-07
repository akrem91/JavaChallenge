package main.java.com.glady;

import main.java.com.glady.models.Company;
import main.java.com.glady.models.User;

public class Distribution {

    private final Deposit deposit;

    public Distribution(Deposit deposit) {
        this.deposit = deposit;
    }

    public Deposit distribute(User user, Company company) {
        return this.deposit.distribute(user, company);
    }
}
