package avans.deeltijd.speedy.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Trip {
    @Id
    @Getter
    @Setter
    private Long id;
    private Long userId;
    private String licensePlate;
    private String pointPickUp;
    private String pointDropOff;
    private double tripDuration;
    private double tripDistance;
    private int tripPrice;

    // constructor
    public Trip(Long id, Long userId, String licensePlate, String pointPickUp, String pointDropOff, double tripDuration, double tripDistance, int tripPrice) {
        this.id = id;
        this.userId = userId;
        this.licensePlate = licensePlate;
        this.pointPickUp = pointPickUp;
        this.pointDropOff = pointDropOff;
        this.tripDuration = tripDuration;
        this.tripDistance = tripDistance;
        this.tripPrice = tripPrice;
    }

    public Trip() {
    }

    public Trip(Long userId, String licensePlate) {
        this.userId = userId;
        this.licensePlate = licensePlate;
    }

    // Getters en setters
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPointPickUp(String pointPickUp) {
        this.pointPickUp = pointPickUp;
    }

    public void setPointDropOff(String pointDropOff) {
        this.pointDropOff = pointDropOff;
    }

    public long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getPointPickUp() {
        return pointPickUp;
    }

    public String getPointDropOff() {
        return pointDropOff;
    }

    public double getTripDuration() {
        return tripDuration;
    }

    public double getTripDistance() {
        return tripDistance;
    }

    public int getTripPrice() {
        return tripPrice;
    }
}
