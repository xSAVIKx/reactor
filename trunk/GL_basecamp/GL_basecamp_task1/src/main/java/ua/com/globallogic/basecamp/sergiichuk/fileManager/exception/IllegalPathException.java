package ua.com.globallogic.basecamp.sergiichuk.fileManager.exception;

public class IllegalPathException extends FileManagerException {

    private static final long serialVersionUID = 8586309606116827210L;

    public IllegalPathException() {
    }

    public IllegalPathException(String message) {
	super(message);
    }

    public IllegalPathException(Throwable cause) {
	super(cause);
    }

    public IllegalPathException(String message, Throwable cause) {
	super(message, cause);
    }

    public IllegalPathException(String message, Throwable cause,
	    boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

}
