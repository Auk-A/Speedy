package avans.deeltijd.speedy.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @Column(name = "`VALUE`")
    private int value;
    @Getter
    private Date dateOfBuild;
    @Getter
    private int paxCapacity;
    @Getter
    @Setter
    private boolean rentedOut;

    public Car(String licensePlate) {
        CreateCar(licensePlate);
    }

    public Car() {
    }

    public void CreateCar(String licensePlate) {
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

    public static String getFuelDescription(JSONObject obj) {
        String carType = null;
        try {
            switch (obj.getString("brandstof_omschrijving")) {
                case "Elektriciteit":
                    carType = "BEV";
                    break;
                case "Waterstof":
                    carType = "FCEV";
                    break;
                default:
                    carType = "ICE";
                    break;
            }
        } catch (JSONException ignored) {
        }
        return carType;
    }

    public static JSONArray getFuelInfo(String licensePlate) {
        String carInfo;
        try {
            String uri = "https://opendata.rdw.nl/resource/8ys7-d773.json?kenteken=" + licensePlate;
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForObject(uri, String.class);
            carInfo = restTemplate.getForObject(uri, String.class);
            return new JSONArray(carInfo);
        } catch(JSONException ignored){return null;}
    }

    public static String getType(String licensePlate) throws JSONException {
        JSONArray fuelInfo = getFuelInfo(licensePlate);
        if(fuelInfo.length() == 0) {
            return null;
        }
        String carType = "";
            String type1 = getFuelDescription(fuelInfo.getJSONObject(0));
            String type2 = "";
            if (fuelInfo.length() > 1) {
                type2 = getFuelDescription(fuelInfo.getJSONObject(1));
            }

            if (type1.equals("BEV") && type2.equals("FCEV")) {
                carType = "FCEV";
            } else {
                carType = type1;
            }
        return carType;
    }

    public Date getBuildDate(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.parse(dateString);
    }

    public void rentCar() {
        this.rentedOut = true;
    }
}
