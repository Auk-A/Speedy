package avans.deeltijd.speedy.controller;

import avans.deeltijd.speedy.domain.CustomResponse;
import avans.deeltijd.speedy.service.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/trip")
public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    // Add a new car by license plate
    @PostMapping("/start")
    public ResponseEntity startTrip(@RequestParam Long user_id, @RequestParam String license_plate) {
        if(tripService.start(user_id, license_plate) == CustomResponse.TRIP_STARTED) {
            return new ResponseEntity("Trip started", HttpStatus.OK);
        } else {
            return new ResponseEntity("Trip could not be started", HttpStatus.CONFLICT);
        }
    }
}
