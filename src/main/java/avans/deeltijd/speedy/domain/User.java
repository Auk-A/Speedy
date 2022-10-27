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
    @Size(min = 3, max = 15)
    @NotBlank(message = "First name is mandatory")
    public String firstName;
    @NotBlank(message = "Email is mandatory")
    private String userEmail;
    private String lastName;
    @Size(min = 3, max = 30)
    @NotBlank(message = "Last name is mandatory")
    private LocalDate dateOfBirth;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String state;
    private String password;
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

    public String getUserEmail() {
        return userEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    //Calculates age with dateOfBirth on get.
    public Integer getAge(){
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }
}
