package avans.deeltijd.speedy.domain;

public enum FuelType {
    DIESEL,
    PETROL,
    LPG;

    @Override
    public String toString() {
        switch (this) {
            case DIESEL:
                return "Diesel";
            case PETROL:
                return "Petrol";
            case LPG:
                return "LPG";
            default:
                throw new IllegalArgumentException();
        }
    }
}
