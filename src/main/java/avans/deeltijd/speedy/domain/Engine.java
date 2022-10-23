package avans.deeltijd.speedy.domain;

public enum Engine {
    DIESEL,
    PETROL;

    @Override
    public String toString() {
        switch (this) {
            case DIESEL:
                return "Diesel";
            case PETROL:
                return "Petrol";
            default:
                throw new IllegalArgumentException();
        }
    }
}
