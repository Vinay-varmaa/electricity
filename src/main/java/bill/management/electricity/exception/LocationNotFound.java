package bill.management.electricity.exception;

public class LocationNotFound extends RuntimeException {
    public LocationNotFound() {
        super("Location not found");
    }
}
