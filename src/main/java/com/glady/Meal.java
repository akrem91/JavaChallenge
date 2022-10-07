package main.java.com.glady;

import main.java.com.glady.models.Company;
import main.java.com.glady.models.User;

import java.time.LocalDate;
import java.time.Month;


public class Meal implements Deposit {

    private final double amount;
    private final LocalDate receivedDate;
    private LocalDate expiredDate;

    public Meal(double amount, LocalDate receivedDate) {
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
            user.getMeals().add(this);
            return this;
        }
    }

    private LocalDate getReceivedDatePlusOneYear(LocalDate receivedDate) {
        LocalDate dateYearPlusOne = receivedDate.plusYears(1);
        LocalDate dateYearPlusOneMonthStart = LocalDate.of(dateYearPlusOne.getYear(), Month.FEBRUARY,1);
        return dateYearPlusOneMonthStart.plusDays(dateYearPlusOneMonthStart.lengthOfMonth()-1);
    }
}
