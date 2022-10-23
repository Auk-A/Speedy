package avans.deeltijd.speedy.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Car {
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
    private Date dateOfBuild;
    @Getter
    private int paxCapacity;
    @Getter
    @Setter
    private boolean rentedOut;

    public Car() {
    }

    public Car(String licensePlate) {
        try {

            String uri = "https://opendata.rdw.nl/resource/m9d7-ebf2.json?kenteken=" + licensePlate;
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.getForObject(uri, String.class);
            String carInfo = restTemplate.getForObject(uri, String.class);

            JSONArray json_arr = new JSONArray(carInfo);
            JSONObject obj = (JSONObject) json_arr.get(0);

            // Set object properties
            this.licensePlate = obj.getString("kenteken");
            this.brand = obj.getString("merk");
            this.model = obj.getString("handelsbenaming");
            this.color = obj.getString("eerste_kleur");
            this.value = obj.getInt("catalogusprijs");
            this.dateOfBuild = getBuildDate(obj.getString("datum_eerste_toelating"));
            this.paxCapacity = obj.getInt("aantal_zitplaatsen");

        } catch (ParseException | JSONException ignored) {
        }
    }

    public Date getBuildDate(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.parse(dateString);
    }

    public void rentCar() {
        this.rentedOut = true;
    }

    public boolean usesExternal() {
        return false;
    }
}
