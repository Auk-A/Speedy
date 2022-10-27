package avans.deeltijd.speedy.domain;

import avans.deeltijd.speedy.service.CarService;
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
    private String fuelType;

    public ICE() {
    }

    public ICE(String licensePlate) throws JSONException {
        super(licensePlate);
        setFuelType();
    }

    public void setFuelType() throws JSONException {
        JSONArray fuelInfo = CarService.getFuelInfo(this.getLicensePlate());
        JSONObject currentFuelInfo = fuelInfo.getJSONObject(0);
        String fuelType = currentFuelInfo.getString("brandstof_omschrijving");
        if (fuelType.equals("Benzine")) {
            this.fuelType = FuelType.PETROL.toString();
        } else if (fuelType.equals("LPG")) {
            this.fuelType = FuelType.LPG.toString();
        } else {
            this.fuelType = FuelType.DIESEL.toString();
        }
    }
}
