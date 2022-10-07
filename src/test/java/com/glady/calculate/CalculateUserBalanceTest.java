package test.java.com.glady.calculate;

import main.java.com.glady.Distribution;
import main.java.com.glady.Gift;
import main.java.com.glady.Meal;
import main.java.com.glady.models.Company;
import main.java.com.glady.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CalculateUserBalanceTest {

    private User user;
    private Company company;

    private LocalDate date;

    @BeforeEach
    public void init() {
        user = new User();
        company = new Company(500.00);
        date = LocalDate.of(2020, 6, 15);
    }

    @Test
    public void calculateUserBalanceCaseTwoValidGiftTest() {
        // Arrange
        // 1st distribution
        Distribution firstDistribution = new Distribution(new Gift(200.00, date));
        Gift gift1 = (Gift) firstDistribution.distribute(user, company);

        // 2nd distribution
        Distribution secondDistribution = new Distribution(new Gift(101.00, date));
        Gift gift2 = (Gift) secondDistribution.distribute(user, company);

        // Act
        double balance = user.calculateGiftBalance(LocalDate.of(2021, 6, 13));

        // Assert
        Assertions.assertEquals(gift1.getAmount() + gift2.getAmount(), balance);
    }

    @Test
    public void calculateUserBalanceCaseGiftExpiredTest() {
        // Arrange
        // 1st distribution
        Distribution firstDistribution = new Distribution(new Gift(200.00, date));
        firstDistribution.distribute(user, company);

        // 2nd distribution
        Distribution secondDistribution = new Distribution(new Gift(101.00, LocalDate.of(2021, 12, 13)));
        Gift gift2 = (Gift) secondDistribution.distribute(user, company);

        // Act
        double balance = user.calculateGiftBalance(LocalDate.of(2021, 6, 15));

        // Assert
        Assertions.assertEquals(gift2.getAmount(), balance);
    }

    @Test
    public void calculateUserBalanceCaseTwoValidMealTest() {
        // Arrange
        // 1st distribution
        Distribution firstDistribution = new Distribution(new Meal(200.00, date));
        Meal meal1 = (Meal) firstDistribution.distribute(user, company);

        // 2nd distribution
        Distribution secondDistribution = new Distribution(new Meal(101.00, date));
        Meal meal2 = (Meal) secondDistribution.distribute(user, company);

        // Act
        double balance = user.calculateMealBalance(LocalDate.of(2021, 3, 1));

        // Assert
        Assertions.assertEquals(meal1.getAmount() + meal2.getAmount(), balance);
    }

    @Test
    public void calculateUserBalanceCaseMealExpiredTest() {
        // Arrange
        // 1st distribution
        Distribution distribution = new Distribution(new Meal(200.00, date));
        distribution.distribute(user, company);

        // 2nd distribution
        Distribution secondDistribution = new Distribution(new Meal(101.00, LocalDate.of(2019, 10, 10)));
        Meal meal2 = (Meal) secondDistribution.distribute(user, company);

        // Act
        double balance = user.calculateMealBalance(LocalDate.of(2020, 3, 1));

        // Assert
        Assertions.assertEquals(meal2.getAmount(), balance);
    }
}
