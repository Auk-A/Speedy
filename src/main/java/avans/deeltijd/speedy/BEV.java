package avans.deeltijd.speedy;

import lombok.Getter;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class BEV extends Car{
    @Getter
    private final String carType = "Battery electric vehicle";

    public BEV() {
    }

    public BEV(String licensePlate, String brand, String model, String color, double value, LocalDate dateOfBuild, int paxCapacity) {
        super(licensePlate, brand, model, color, value, dateOfBuild, paxCapacity);
    }
}
