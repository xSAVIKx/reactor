package ua.com.globallogic.basecamp.sergiichuk.urlRwManager.exception;

public class WrongUrlException extends RuntimeException {

    private static final long serialVersionUID = 7359622724041224276L;

    public WrongUrlException() {
    }

    public WrongUrlException(String message) {
	super(message);
    }

    public WrongUrlException(Throwable cause) {
	super(cause);
    }

    public WrongUrlException(String message, Throwable cause) {
	super(message, cause);
    }

    public WrongUrlException(String message, Throwable cause,
	    boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

}
