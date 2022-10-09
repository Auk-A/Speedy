package avans.deeltijd.speedy;

import lombok.Getter;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class FCEV extends Car{
    @Getter
    private final String carType = "Fuel cell electric vehicle";

    public FCEV() {
    }

    public FCEV(String licensePlate, String brand, String model, String color, double value, LocalDate dateOfBuild, int paxCapacity) {
        super(licensePlate, brand, model, color, value, dateOfBuild, paxCapacity);
    }
}
