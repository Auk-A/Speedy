package avans.deeltijd.speedy.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reservation {
    @Id
    @Getter
    @Setter
    private long reservationNumber;
    @Autowired
    @Getter
    private String licensePlate;
    @Autowired
    @Getter
    private long userId;
    @Getter
    @Setter
    private LocalDate reservationDate;
    @Getter
    @Setter
    private LocalDate startDate;
    @Getter
    @Setter
    private LocalDate endDate;

}
