package avans.deeltijd.speedy.repository;

import avans.deeltijd.speedy.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    List<Car> findByLicensePlateIgnoringCase(String license_plate);
}
