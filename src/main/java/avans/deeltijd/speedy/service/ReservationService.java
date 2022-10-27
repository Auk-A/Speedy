package avans.deeltijd.speedy.service;

import avans.deeltijd.speedy.domain.CustomResponse;
import avans.deeltijd.speedy.domain.Reservation;
import avans.deeltijd.speedy.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public CustomResponse newReservation(Long user_id, String license_plate, LocalDate start_date, LocalDate end_date) {
        List<Reservation> existingReservation = reservationRepository.findReservationByUserIdAndLicensePlate(user_id, license_plate);
        if(existingReservation.isEmpty()) {
            Reservation reservation = new Reservation(license_plate, user_id, createReservationDate(), start_date, end_date);
            reservationRepository.save(reservation);
            return CustomResponse.RESERVATION_CREATED;
        } else {
            return CustomResponse.RESERVATION_FAILED;
        }
    }

    public static LocalDate createReservationDate() {
        return LocalDate.now();
    }
}
