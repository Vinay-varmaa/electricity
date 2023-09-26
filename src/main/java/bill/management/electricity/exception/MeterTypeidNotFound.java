package bill.management.electricity.exception;

public class MeterTypeidNotFound extends RuntimeException{

    public MeterTypeidNotFound() {
        super("meter id not found");

    }
}
