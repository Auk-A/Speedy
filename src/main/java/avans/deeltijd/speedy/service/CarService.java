package avans.deeltijd.speedy.service;

import avans.deeltijd.speedy.domain.*;
import avans.deeltijd.speedy.repository.CarRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CustomResponse newCar(String license_plate) throws JSONException {
        if (carRepository.findByLicensePlateIgnoringCase(license_plate).isEmpty()) {
            Car addedCar;
            String carType = Car.getType(license_plate);
            if (carType == null) {
                return CustomResponse.PLATE_NOT_FOUND;
            }
            switch (carType) {
                case "ICE":
                    addedCar = new ICE(license_plate);
                    break;
                case "BEV":
                    addedCar = new BEV(license_plate);
                    break;
                case "FCEV":
                    addedCar = new FCEV(license_plate);
                    break;
                default:
                    return CustomResponse.PLATE_NOT_FOUND;
            }
            carRepository.save(addedCar);
            return CustomResponse.NEW_CAR_ADDED;
        } else {
            return CustomResponse.CAR_ALREADY_EXISTS;
        }
    }

    public List<Car> filterCars(String brand, String model, String color, int min_value, int max_value, int min_capacity, Date min_build_date, Date max_build_date) {
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

        // Filter amount of seats
        if (min_capacity > 0 || min_capacity < 2147483647) {
            found = found.stream()
                    .filter(car -> min_capacity <= car.getPaxCapacity())
                    .collect(Collectors.toList());
        }

        // Filter by date
        found = found.stream()
                .filter(car -> min_build_date.before(car.getDateOfBuild()))
                .filter(car -> max_build_date.after(car.getDateOfBuild()))
                .collect(Collectors.toList());

        return found;
    }

    public List<Car> findCar(String license_plate) {
        List<Car> found = new ArrayList<>();
        if (license_plate != null && !license_plate.isEmpty()) {
            return carRepository.findByLicensePlateIgnoringCase(license_plate);
        }

        return found;
    }
}
