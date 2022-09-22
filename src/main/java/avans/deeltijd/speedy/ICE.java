package avans.deeltijd.speedy;

import lombok.Getter;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class ICE extends Car{
    @Getter
    private final String carType = "Internal combustion engine";
    @Getter
    private Engine engineType;

    public ICE() {
    }

    public ICE(Engine engineType) {
        this.engineType = engineType;
    }

    public ICE(String licensePlate, String brand, String model, String color, double value, LocalDate dateOfBuild, int paxCapacity, Engine engineType) {
        super(licensePlate, brand, model, color, value, dateOfBuild, paxCapacity);
        this.engineType = engineType;
    }
}
