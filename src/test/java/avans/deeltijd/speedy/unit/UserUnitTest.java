package avans.deeltijd.speedy.unit;

import avans.deeltijd.speedy.domain.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserUnitTest {

    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Test
    public void instantiateUserGetUserAge_expectNotNull() {

        // GIVEN
        User user = new User(
                "test",
                "test",
                "test@test.com",
                LocalDate.parse("01-01-1990", df)
        );

        // WHEN
        user.getAge();

        // THEN
        assertNotNull(user.getAge());
    }

}