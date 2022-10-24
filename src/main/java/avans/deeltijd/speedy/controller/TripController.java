package avans.deeltijd.speedy.controller;

import avans.deeltijd.speedy.domain.Car;
import avans.deeltijd.speedy.domain.Trip;
import avans.deeltijd.speedy.repository.CarRepository;
import avans.deeltijd.speedy.repository.TripRepository;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {
    private final TripRepository tripRepository;
    private final CarRepository carRepository;

    public TripController(TripRepository tripRepository, CarRepository carRepository) {
        this.tripRepository = tripRepository;
        this.carRepository = carRepository;
    }

    // Add a new car by license plate
    @PostMapping("/start")
    public ResponseEntity startTrip(@RequestParam Long user_id, @RequestParam String license_plate) throws JSONException {
        List<Car> foundCars = carRepository.findByLicensePlateIgnoringCase(license_plate);
        Car foundCar = foundCars.get(0);
        foundCar.rentCar();
        carRepository.save(foundCar);
        Trip newTrip = new Trip(user_id, license_plate);
        tripRepository.save(newTrip);

        return new ResponseEntity<>("Trip started", HttpStatus.CREATED);
    }
}
