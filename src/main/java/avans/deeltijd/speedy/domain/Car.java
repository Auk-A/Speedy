package avans.deeltijd.speedy.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public abstract class Car {
    @Id
    @Getter
    @Setter
    private String licensePlate;
    @Getter
    private String brand;
    @Getter
    private String model;
    @Getter
    private String color;
    @Getter
    private double value;
    @Getter
    private LocalDate dateOfBuild;
    @Getter
    private int paxCapacity;
    @Getter
    @Setter
    private boolean rentedOut;

    public Car() {
    }

    public Car(String licensePlate, String brand, String model, String color, double value, LocalDate dateOfBuild, int paxCapacity) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.value = value;
        this.dateOfBuild = dateOfBuild;
        this.paxCapacity = paxCapacity;
    }

    public void rentCar() {
        this.rentedOut = true;
    }

    public boolean usesExternal() {
        return false;
    }
}
