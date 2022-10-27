package avans.deeltijd.speedy.service;

import avans.deeltijd.speedy.domain.Car;
import avans.deeltijd.speedy.domain.CustomResponse;
import avans.deeltijd.speedy.domain.Reservation;
import avans.deeltijd.speedy.domain.Trip;
import avans.deeltijd.speedy.repository.CarRepository;
import avans.deeltijd.speedy.repository.ReservationRepository;
import avans.deeltijd.speedy.repository.TripRepository;
import avans.deeltijd.speedy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class ReservationService {
    @Autowired
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public CustomResponse newReservation(Long user_id, String license_plate) {
        List<Reservation> existingReservation = reservationRepository.findReservationByUserIdAndLicensePlate(user_id, license_plate);
        if(existingReservation.isEmpty()) {
            Reservation reservation = new Reservation();
            reservationRepository.save(reservation);
            return CustomResponse.RESERVATION_CREATED;
        } else {
            return CustomResponse.RESERVATION_FAILED;
        }
    }
}
