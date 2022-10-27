package avans.deeltijd.speedy.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reservation {
    @Id
    @Getter
    private long reservationNumber;
    @Getter
    private String licensePlate;
    @Getter
    private long userId;
    @Setter
    @Getter
    private LocalDate reservationDate;
    @Setter
    @Getter
    private LocalDate startDate;
    @Setter
    @Getter
    private LocalDate endDate;

}
