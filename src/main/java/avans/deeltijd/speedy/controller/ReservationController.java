package avans.deeltijd.speedy.controller;

import avans.deeltijd.speedy.service.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // Add a new reservation by user id and car license plate
    @PostMapping("/new")
    public ResponseEntity startTrip(
            @RequestParam Long user_id,
            @RequestParam String license_plate,
            @RequestParam
            @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate start_date,
            @RequestParam
            @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate end_date) {

        return switch(reservationService.newReservation(user_id, license_plate, start_date, end_date)) {
            case RESERVATION_FAILED -> new ResponseEntity<>("Reservation could not be created", HttpStatus.CONFLICT);
            case RESERVATION_CREATED -> new ResponseEntity<>("Reservation has been created", HttpStatus.CREATED);
            default -> new ResponseEntity<>("Reservation could not be created. Contact system administrator. ", HttpStatus.CONFLICT);
        };
    }
}
