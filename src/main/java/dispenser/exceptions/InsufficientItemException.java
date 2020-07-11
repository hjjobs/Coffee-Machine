package dispenser.exceptions;

public class InsufficientItemException extends Exception {
    public InsufficientItemException(String message) {
        super(message);
    }

    public InsufficientItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientItemException(Throwable cause) {
        super(cause);
    }

    protected InsufficientItemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
