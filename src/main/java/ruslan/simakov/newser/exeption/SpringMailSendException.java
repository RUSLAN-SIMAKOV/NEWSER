package ruslan.simakov.newser.exeption;

public class SpringMailSendException extends RuntimeException {
    public SpringMailSendException(String ex) {
        super(ex);
    }
}
