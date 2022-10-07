package test.java.com.glady.dictribute;

import main.java.com.glady.Distribution;
import main.java.com.glady.Meal;
import main.java.com.glady.models.Company;
import main.java.com.glady.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DistributeMealTest {

    private User user;
    private Company company;
    private LocalDate date;

    @BeforeEach
    public void init() {
        user = new User();
        company = new Company(200.00);
        date = LocalDate.of(2022, 10, 10);
    }

    @Test
    public void distributeMeal() {
        // Arrange
        Distribution distribution = new Distribution(new Meal(100.00, date));

        // Act
        Meal meal = (Meal) distribution.distribute(user, company);

        // Assert
        Assertions.assertEquals(100.00, meal.getAmount());
        Assertions.assertTrue(meal.getExpiredDate().isEqual(LocalDate.of(2023, 2, 28)));
    }

    @Test
    public void distributeMealCaseCompanyBalanceNotSufficient() {
        // 1st distribution
        // Arrange
        Distribution firstDistribution = new Distribution(new Meal(80.00, date));

        // Act
        Meal meal = (Meal) firstDistribution.distribute(user, company);

        // Assert
        Assertions.assertEquals(80.00, meal.getAmount());
        Assertions.assertTrue(meal.getExpiredDate().isEqual(LocalDate.of(2023, 2, 28)));


        // 2nd distribution
        Distribution secondDistribution = new Distribution(new Meal(121.00, date));

        // Act
        Meal meal2 = (Meal) secondDistribution.distribute(user, company);

        // Assert
        // The balance of company is not sufficient
        Assertions.assertNull(meal2);
    }

}