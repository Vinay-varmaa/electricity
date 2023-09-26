package bill.management.electricity.exception;

public class OfficeNotFound extends Exception{
    public OfficeNotFound() {
        super("Office Id Not Found");
    }
}
