package avans.deeltijd.speedy.unit;

import avans.deeltijd.speedy.domain.Reservation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ReservationUnitTest {

    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Test
    public void userAgeShouldBeCorrectWhenOneDayAfter() {
        // GIVEN
        Reservation reservation = new Reservation(
                1,
                "R907BZ",
                1,
                LocalDate.parse ("27-10-2022", df),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2)
        );

        // WHEN
        LocalDate startDate = reservation.getStartDate();

        // THEN
        assertNotEquals(LocalDate.now().plusDays(0), startDate);
        assertEquals(LocalDate.now().plusDays(1), startDate);
        assertNotEquals(LocalDate.now().plusDays(2), startDate);
    }
}
