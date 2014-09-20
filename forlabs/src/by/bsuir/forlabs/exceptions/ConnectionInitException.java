package by.bsuir.forlabs.exceptions;

public class ConnectionInitException extends TechnicalException {

    public ConnectionInitException() {
        super();
    }

    public ConnectionInitException(String message) {
        super(message);
    }

    public ConnectionInitException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionInitException(Throwable cause) {
        super(cause);
    }
}
