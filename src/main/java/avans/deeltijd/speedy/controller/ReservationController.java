package avans.deeltijd.speedy.controller;

import avans.deeltijd.speedy.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // Add a new car by license plate
    @PostMapping("/new")
    public void startTrip(@RequestParam Long user_id, @RequestParam String license_plate) {
        return;
    }
}
