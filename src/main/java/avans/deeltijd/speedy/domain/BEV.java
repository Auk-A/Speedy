package avans.deeltijd.speedy.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;

import javax.persistence.Entity;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class BEV extends Car {
    @Getter
    private final String carType = "Battery electric vehicle";

    public BEV(String licensePlate) {
        super(licensePlate);
    }

    public BEV() {
    }

    public int totalCostOfOwnership() {
        return 2;
    }
}
