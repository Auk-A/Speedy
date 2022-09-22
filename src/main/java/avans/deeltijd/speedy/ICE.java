package avans.deeltijd.speedy;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class ICE extends Car{
    private String carType;
    private Engine engineType;

    public ICE() {
    }

    public ICE(Engine engineType) {
        this.carType = "Internal combustion engine";
        this.engineType = engineType;
    }

    public ICE(String licensePlate, String brand, String model, String color, double value, LocalDate dateOfBuild, int paxCapacity, Engine engineType) {
        super(licensePlate, brand, model, color, value, dateOfBuild, paxCapacity);
        this.carType = "Internal combustion engine";
        this.engineType = engineType;
    }
}
