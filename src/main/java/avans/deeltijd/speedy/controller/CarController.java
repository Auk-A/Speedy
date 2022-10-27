package avans.deeltijd.speedy.controller;

import avans.deeltijd.speedy.domain.Car;
import avans.deeltijd.speedy.service.CarService;
import org.json.JSONException;
import org.springframework.format.annotation.DateTimeFormat;
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

        return carService.newCar(license_plate);
    }

    // Get all cars by request params
    @GetMapping("/filter")
    public List<Car> getAll(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String color,
            @RequestParam(required = false, defaultValue = "0") int min_value,
            @RequestParam(required = false, defaultValue = "2147483647") int max_value,
            @RequestParam(required = false, defaultValue = "0") int min_capacity,
            @RequestParam(required = false, defaultValue = "1900-01-01") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = {"yyyy"}) Date min_build_date,
            @RequestParam(required = false, defaultValue = "2100-01-01") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = {"yyyy"}) Date max_build_date
    ) {

        return carService.filterCars(brand, model, color, min_value, max_value, min_capacity, min_build_date, max_build_date);
    }

    @GetMapping("/find")
    public List<Car> getAll(@RequestParam String license_plate) {

        return carService.findCar(license_plate);
    }
}
