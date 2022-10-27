package avans.deeltijd.speedy.controller;

import avans.deeltijd.speedy.domain.User;
import avans.deeltijd.speedy.repository.UserRepository;
import avans.deeltijd.speedy.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Add a new user
    @PostMapping("/new")
    public ResponseEntity createUser(
            @RequestParam String first_name,
            @RequestParam String last_name,
            @RequestParam String user_email,
            @RequestParam String date_of_birth
    ) {
        return userService.newUser(first_name, last_name, user_email, date_of_birth);
    }

}
