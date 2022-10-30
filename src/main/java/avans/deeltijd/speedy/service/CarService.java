package avans.deeltijd.speedy.service;

import avans.deeltijd.speedy.domain.*;
import avans.deeltijd.speedy.repository.CarRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CustomResponse newCar(String license_plate) throws JSONException {
        if (carRepository.findByLicensePlateIgnoringCase(license_plate).isEmpty()) {
            Car addedCar;
            String carType = CarService.getType(license_plate);
            if (carType == null) {
                return CustomResponse.PLATE_NOT_FOUND;
            }
            switch (carType) {
                case "ICE":
                    addedCar = new ICE(license_plate);
                    break;
                case "BEV":
                    addedCar = new BEV(license_plate);
                    break;
                case "FCEV":
                    addedCar = new FCEV(license_plate);
                    break;
                default:
                    return CustomResponse.PLATE_NOT_FOUND;
            }
            carRepository.save(addedCar);
            return CustomResponse.NEW_CAR_ADDED;
        } else {
            return CustomResponse.CAR_ALREADY_EXISTS;
        }
    }

    public List<Car> filterCars(String brand, String model, String color, int min_value, int max_value, int min_capacity, Date min_build_date, Date max_build_date) {
        List<Car> found = new ArrayList<>(carRepository.findAll());

        //Filter by brand
        if (model != null && !model.isEmpty()) {
            found = found.stream()
                    .filter(car -> model.equals(car.getModel()))
                    .collect(Collectors.toList());
        }

        // Filter model
        if (brand != null && !brand.isEmpty()) {
            found = found.stream()
                    .filter(car -> brand.equals(car.getBrand()))
                    .collect(Collectors.toList());
        }

        // Filter color
        if (color != null && !color.isEmpty()) {
            found = found.stream()
                    .filter(car -> color.equals(car.getColor()))
                    .collect(Collectors.toList());
        }

        // Filter car value
        if (min_value > 0 || max_value < 2147483647) {
            found = found.stream()
                    .filter(car -> min_value <= car.getValue())
                    .filter(car -> max_value >= car.getValue())
                    .collect(Collectors.toList());
        }

        // Filter amount of seats
        if (min_capacity > 0) {
            found = found.stream()
                    .filter(car -> min_capacity <= car.getPaxCapacity())
                    .collect(Collectors.toList());
        }

        // Filter by date
        found = found.stream()
                .filter(car -> min_build_date.before(car.getDateOfBuild()))
                .filter(car -> max_build_date.after(car.getDateOfBuild()))
                .collect(Collectors.toList());

        return found;
    }

    public List<Car> findCar(String license_plate) {
        List<Car> found = new ArrayList<>();
        if (license_plate != null && !license_plate.isEmpty()) {
            return carRepository.findByLicensePlateIgnoringCase(license_plate);
        }

        return found;
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

    public static Date getBuildDate(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.parse(dateString);
    }
}
