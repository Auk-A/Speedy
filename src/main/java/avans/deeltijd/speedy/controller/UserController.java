package avans.deeltijd.speedy.controller;

import avans.deeltijd.speedy.domain.User;
import avans.deeltijd.speedy.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Add a new user
    @PostMapping("/new")
    public ResponseEntity createUser(
            @RequestParam String first_name,
            @RequestParam String last_name,
            @RequestParam String user_email,
            @RequestParam String date_of_birth
    ) {
        LocalDate dateOfBirth;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            dateOfBirth = LocalDate.parse(date_of_birth, formatter);
        } catch (Exception ignore) {
            return new ResponseEntity<>("Date format incorrect: Should be dd-MM-yyyy", HttpStatus.CONFLICT);
        }
        try {
            User createdUser = new User(first_name, last_name, user_email, dateOfBirth);
            userRepository.save(createdUser);
            return new ResponseEntity<>("New user created", HttpStatus.CREATED);
        } catch (Exception ignore) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }

    }

}
