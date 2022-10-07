package test.java.com.glady.dictribute;

import main.java.com.glady.Gift;
import main.java.com.glady.Meal;
import main.java.com.glady.models.Company;
import main.java.com.glady.Distribution;
import main.java.com.glady.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DistributeGiftTest {

    private User user;
    private Company company;
    private LocalDate date;

    @BeforeEach
    public void init() {
        user = new User();
        company = new Company(500.00);
        date = LocalDate.of(2022, 6, 15);
    }

    @Test
    public void distributeGift() {
        // Arrange
        Distribution distribution = new Distribution(new Gift(400.00, date));

        // Act
        Gift gift = (Gift) distribution.distribute(user, company);

        // Assert
        Assertions.assertEquals(400.00, gift.getAmount());
        Assertions.assertTrue(gift.getExpiredDate().isEqual(LocalDate.of(2023, 6, 14)));
    }

    @Test
    public void distributeGiftCaseCompanyBalanceNotSufficient() {
        // 1st distribution
        // Arrange
        Distribution distribution = new Distribution(new Gift(400.00, date));

        // Act
        Gift gift = (Gift) distribution.distribute(user, company);

        // Assert
        Assertions.assertEquals(400.00, gift.getAmount());
        Assertions.assertTrue(gift.getExpiredDate().isEqual(LocalDate.of(2023, 6, 14)));


        // 2nd distribution
        distribution = new Distribution(new Meal(101.00, date));

        // Act
        Gift gift2 = (Gift) distribution.distribute(user, company);

        // Assert
        // The balance of company is not sufficient
        Assertions.assertNull(gift2);
    }

}