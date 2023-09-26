package bill.management.electricity.exception;

public class ReadingIdNotFoundException extends Exception{
    public ReadingIdNotFoundException(){
        super("Reading id not found");
    }
}
