package main.java.com.glady;

import main.java.com.glady.models.Company;
import main.java.com.glady.models.User;

import java.time.LocalDate;

public class Gift implements Deposit {

    private final double amount;
    private final LocalDate receivedDate;
    private LocalDate expiredDate;

    public Gift(double amount, LocalDate receivedDate) {
        this.amount = amount;
        this.receivedDate = receivedDate;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    @Override
    public Deposit distribute(User user, Company company) {
        if (this.amount > company.getBalance()) {
            System.err.println("The balance of the company is not sufficient.");
            return null;
        } else {
            company.setBalance(company.getBalance() - this.amount);
            this.expiredDate = getReceivedDatePlusOneYear(this.receivedDate);
            user.getGifts().add(this);
            return this;
        }
    }

    private LocalDate getReceivedDatePlusOneYear(LocalDate receivedDate) {
        return receivedDate.plusYears(1).minusDays(1);
    }
}
