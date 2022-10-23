package avans.deeltijd.speedy.controller;

import avans.deeltijd.speedy.domain.Car;
import avans.deeltijd.speedy.domain.BEV;
import avans.deeltijd.speedy.domain.FCEV;
import avans.deeltijd.speedy.domain.ICE;
import avans.deeltijd.speedy.repository.CarRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // Add a new car by license plate
    @PostMapping("/new")
    public ResponseEntity<HttpStatus> createCar(@RequestParam String licensePlate) {
        if (carRepository.findByLicensePlateIgnoringCase(licensePlate).isEmpty()) {
            Car addedCar = new Car(licensePlate);
            switch (addedCar.getType(licensePlate)) {
                case "ICE":
                    addedCar = new ICE(licensePlate);
                    break;
                case "BEV":
                    addedCar = new BEV(licensePlate);
                    break;
                case "FCEV":
                    addedCar = new FCEV(licensePlate);
                    break;
                default:
                    break;
            }
            carRepository.save(addedCar);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } else {
            return ResponseEntity.ok(HttpStatus.CONFLICT);
        }

    }

    // Get all cars by request params
    @GetMapping("/filter")
    public List<Car> getAll(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String color,
            @RequestParam(required = false, defaultValue = "0") int min_value,
            @RequestParam(required = false, defaultValue = "2147483647") int max_value,
            @RequestParam(required = false, defaultValue = "1900-01-01") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = {"yyyy"}) Date min_build_date,
            @RequestParam(required = false, defaultValue = "2100-01-01") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = {"yyyy"}) Date max_build_date
    ) {
        List<Car> found = new ArrayList<>(carRepository.findAll());

        //Filter by brand
        if (model != null && !model.isEmpty()) {
            found = found.stream()
                    .filter(car -> model.equals(car.getModel()))
                    .collect(Collectors.toList());
        }

        // Filter model
        if (brand != null && !brand.isEmpty()) {
            found = found.stream()
                    .filter(car -> brand.equals(car.getBrand()))
                    .collect(Collectors.toList());
        }

        // Filter color
        if (color != null && !color.isEmpty()) {
            found = found.stream()
                    .filter(car -> color.equals(car.getColor()))
                    .collect(Collectors.toList());
        }

        // Filter car value
        if (min_value > 0 || max_value < 2147483647) {
            found = found.stream()
                    .filter(car -> min_value <= car.getValue())
                    .filter(car -> max_value >= car.getValue())
                    .collect(Collectors.toList());
        }

        // Filter by date
        found = found.stream()
                .filter(car -> min_build_date.before(car.getDateOfBuild()))
                .filter(car -> max_build_date.after(car.getDateOfBuild()))
                .collect(Collectors.toList());

        return found;
    }

    @GetMapping("/find")
    public List<Car> getAll(@RequestParam String license_plate) {
        List<Car> found = new ArrayList<>();
        if (license_plate != null && !license_plate.isEmpty()) {
            return carRepository.findByLicensePlateIgnoringCase(license_plate);
        }

        return found;
    }

    @RequestMapping("")
    @ResponseBody
    public String getCarData(HttpServletRequest request) {

        String carId = request.getParameter("id");

        if (carId.length() != 6)
            return "Not a valid registration";

        return "Return JSON with car data for car " + carId + ".";
    }
}
