package bill.management.electricity.exception;

public class BillNotFoundException extends Exception{
    public BillNotFoundException() {
        super("Bill id not found");
    }
}
