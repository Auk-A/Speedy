package avans.deeltijd.speedy;


import lombok.Getter;

public class Trip {
    @Getter
    private long id;
    private String userId;
    private String licensePlate;
    private String pointPickUp;
    private String pointDropOff;
    private double tripDuration;.
    private double tripDistance;
    private double tripPrice;

    // constructor
    public Trip(long id, String userId, String licensePlate, String pointPickUp, String pointDropOff, double tripDuration, double tripDistance, double tripPrice) {
        this.id = id;
        this.userId = userId;
        this.licensePlate = licensePlate;
        this.pointPickUp = pointPickUp;
        this.pointDropOff = pointDropOff;
        this.tripDuration = tripDuration;
        this.tripDistance = tripDistance;
        this.tripPrice = tripPrice;
    }

    // Getters en setters
    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
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

    public String getUserId() {
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

    public double getTripPrice() {
        return tripPrice;
    }
}
