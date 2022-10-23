package avans.deeltijd.speedy.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class FCEV extends Car {
    @Getter
    private final String carType = "Fuel cell electric vehicle";

    public FCEV() {
        super();
    }
}
