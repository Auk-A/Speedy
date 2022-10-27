package avans.deeltijd.speedy.controller;

import avans.deeltijd.speedy.domain.Car;
import avans.deeltijd.speedy.service.CarService;
import org.apache.coyote.Response;
import org.json.JSONException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    // Add a new car by license plate
    @PostMapping("/new")
    public ResponseEntity createCar(@RequestParam String license_plate) throws JSONException {
        return switch (carService.newCar(license_plate)) {
            case PLATE_NOT_FOUND -> new ResponseEntity<>("Plate not found", HttpStatus.CONFLICT);
            case CAR_ALREADY_EXISTS -> new ResponseEntity<>("Car already exists", HttpStatus.CONFLICT);
            case NEW_CAR_ADDED -> new ResponseEntity<>("New car has been added", HttpStatus.OK);
            default -> new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }

    // Get all cars by request params
    @GetMapping("/filter")
    public ResponseEntity getAll(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String color,
            @RequestParam(required = false, defaultValue = "0") int min_value,
            @RequestParam(required = false, defaultValue = "2147483647") int max_value,
            @RequestParam(required = false, defaultValue = "0") int min_capacity,
            @RequestParam(required = false, defaultValue = "1900-01-01") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = {"yyyy"}) Date min_build_date,
            @RequestParam(required = false, defaultValue = "2100-01-01") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = {"yyyy"}) Date max_build_date
    ) {

        List<Car> foundCars = carService.filterCars(brand, model, color, min_value, max_value, min_capacity, min_build_date, max_build_date);
        if (foundCars.isEmpty()) {
            return new ResponseEntity<>(foundCars, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(foundCars, HttpStatus.OK);
        }
    }

    @GetMapping("/find")
    public ResponseEntity getAll(@RequestParam String license_plate) {

        List<Car> foundCar = carService.findCar(license_plate);
        if (foundCar.isEmpty()) {
            return new ResponseEntity<>(foundCar, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(foundCar, HttpStatus.OK);
        }
    }
}
