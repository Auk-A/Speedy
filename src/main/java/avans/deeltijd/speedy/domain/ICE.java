package avans.deeltijd.speedy.domain;

import lombok.Getter;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Entity
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ICE extends Car {
    @Getter
    private final String carType = "Internal combustion engine";
    @Getter
    private String engineType;

    public ICE() {
    }

    public ICE(String licensePlate) throws JSONException {
        super(licensePlate);
        setEngineType();
    }

    public void setEngineType() throws JSONException {
        JSONArray fuelInfo = Car.getFuelInfo(this.getLicensePlate());
        JSONObject currentFuelInfo = fuelInfo.getJSONObject(0);
        String fuelType = currentFuelInfo.getString("brandstof_omschrijving");
        if (fuelType.equals("Benzine")) {
            this.engineType = Engine.PETROL.toString();
        } else this.engineType = Engine.DIESEL.toString();
    }
}
