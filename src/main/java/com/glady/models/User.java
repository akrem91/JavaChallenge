package main.java.com.glady.models;

import main.java.com.glady.Gift;
import main.java.com.glady.Meal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {

    private final List<Gift> gifts = new ArrayList<>();
    private final List<Meal> meals = new ArrayList<>();

    public User() {

    }

    // Getters
    public List<Gift> getGifts() { return gifts; }

    public List<Meal> getMeals() { return meals; }

    public double calculateGiftBalance(LocalDate checkDate) {
        return this.gifts.stream()
                .filter(card -> checkDate.isBefore(card.getExpiredDate()))
                .mapToDouble(Gift::getAmount)
                .sum();
    }

    public double calculateMealBalance(LocalDate checkDate) {
        return this.meals.stream()
                .filter(card -> card.getExpiredDate().isBefore(checkDate))
                .mapToDouble(Meal::getAmount)
                .sum();
    }
}
