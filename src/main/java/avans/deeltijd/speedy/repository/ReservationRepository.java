package avans.deeltijd.speedy.repository;

import avans.deeltijd.speedy.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findReservationByUserIdAndLicensePlate(Long user_id, String licence_plate);

}
