package ruslan.simakov.newser.exeption;

public class SpringUserNotFoundException extends RuntimeException {
    public SpringUserNotFoundException(String ex) {
        super(ex);
    }
}
