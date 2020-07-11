package dispenser.exceptions;

public class MissingItemException extends Exception {
    public MissingItemException(String message) {
        super(message);
    }

    public MissingItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingItemException(Throwable cause) {
        super(cause);
    }

    protected MissingItemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
