package avans.deeltijd.speedy.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity(name = "`USERS`")
@NoArgsConstructor
public class User {
    @Id
    @Getter
    @Setter
    private long id;
    @Getter
    @Setter
    @NotBlank(message = "First name is mandatory")
    @Size(min = 3, max = 15)
    public String firstName;
    @Getter
    @Setter
    @NotBlank(message = "Email is mandatory")
    private String userEmail;
    @Getter
    @Setter
    @Size(min = 3, max = 30)
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    @Getter
    @Setter
    private LocalDate dateOfBirth;
    @Getter
    @Setter
    private String street;
    @Getter
    @Setter
    private String houseNumber;
    @Getter
    @Setter
    private String postalCode;
    @Getter
    @Setter
    private String city;
    @Getter
    @Setter
    private String state;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private LocalDateTime registrationDate;
    @Transient //Is not persistent in DB.
    private int age; //is calculated on get.

    public User(String userEmail,
                String firstName,
                String lastName,
                LocalDate dateOfBirth,
                String street,
                String houseNumber,
                String postalCode,
                String city,
                String state,
                String password,
                LocalDateTime registrationDate) {
        this.userEmail = userEmail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.state = state;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    //test constructor
    public User(String firstName, String lastName, String userEmail, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userEmail = userEmail;
        this.dateOfBirth = dateOfBirth;
    }

    //Calculates age with dateOfBirth on get.
    public Integer getAge(){
        this.age = Period.between(this.dateOfBirth, LocalDate.now()).getYears();
        return this.age;
    }
}
