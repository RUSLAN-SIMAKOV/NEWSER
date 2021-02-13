package ruslan.simakov.newser.exeption;

import java.util.function.Supplier;

public class SpringVerificationTokenException extends RuntimeException {
    public SpringVerificationTokenException(String ex) {
        super(ex);
    }
}
