package avans.deeltijd.speedy.controller;

import avans.deeltijd.speedy.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
        return switch(userService.newUser(first_name, last_name, user_email, date_of_birth)) {
            case INCORRECT_DATE_FORMAT -> new ResponseEntity<>("Date format incorrect: Should be dd-MM-yyyy", HttpStatus.CONFLICT);
            case NEW_USER_ADDED -> new ResponseEntity<>("New user created", HttpStatus.CREATED);
            case USER_ALREADY_EXISTS -> new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
            default -> new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }

}
