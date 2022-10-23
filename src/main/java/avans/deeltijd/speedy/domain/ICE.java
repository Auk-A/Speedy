package avans.deeltijd.speedy.domain;

import lombok.Getter;

import javax.persistence.Entity;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ICE extends Car {
    @Getter
    private final String carType = "Internal combustion engine";
    @Getter
    private Engine engineType;

    public ICE(){
    }

    public ICE(String licensePlate) {
        super(licensePlate);
    }

    public void setEngineType() {
        this.engineType = engineType;
    }
}
