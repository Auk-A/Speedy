package avans.deeltijd.speedy.service;

import avans.deeltijd.speedy.domain.CustomResponse;
import avans.deeltijd.speedy.domain.User;
import avans.deeltijd.speedy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CustomResponse newUser(String first_name, String last_name, String user_email, String date_of_birth) {
        LocalDate dateOfBirth;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            dateOfBirth = LocalDate.parse(date_of_birth, formatter);
        } catch (Exception ignore) {
            return CustomResponse.INCORRECT_DATE_FORMAT;
        }
        try {
            User createdUser = new User(first_name, last_name, user_email, dateOfBirth);
            userRepository.save(createdUser);
            return CustomResponse.NEW_USER_ADDED;
        } catch (Exception ignore) {
            return CustomResponse.USER_ALREADY_EXISTS;
        }
    }
}
