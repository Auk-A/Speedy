package avans.deeltijd.speedy.service;

import avans.deeltijd.speedy.domain.Car;
import avans.deeltijd.speedy.domain.CustomResponse;
import avans.deeltijd.speedy.domain.Trip;
import avans.deeltijd.speedy.repository.CarRepository;
import avans.deeltijd.speedy.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {
    private final TripRepository tripRepository;
    private final CarRepository carRepository;
    @Autowired
    public TripService(TripRepository tripRepository, CarRepository carRepository) {
        this.tripRepository = tripRepository;
        this.carRepository = carRepository;
    }

    public CustomResponse start(Long user_id, String license_plate) {
        List<Car> foundCars = carRepository.findByLicensePlateIgnoringCase(license_plate);
        Car foundCar = foundCars.get(0);
        foundCar.rentCar();
        carRepository.save(foundCar);
        Trip newTrip = new Trip(user_id, license_plate);
        tripRepository.save(newTrip);

        return CustomResponse.TRIP_STARTED;
    }
}
