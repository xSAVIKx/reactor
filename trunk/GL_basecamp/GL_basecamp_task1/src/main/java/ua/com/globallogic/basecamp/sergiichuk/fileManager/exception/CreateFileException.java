package ua.com.globallogic.basecamp.sergiichuk.fileManager.exception;

public class CreateFileException extends FileManagerException {

    private static final long serialVersionUID = -9053502668460139962L;

    public CreateFileException() {
    }

    public CreateFileException(String message) {
	super(message);
    }

    public CreateFileException(Throwable cause) {
	super(cause);
    }

    public CreateFileException(String message, Throwable cause) {
	super(message, cause);
    }

    public CreateFileException(String message, Throwable cause,
	    boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }
}
