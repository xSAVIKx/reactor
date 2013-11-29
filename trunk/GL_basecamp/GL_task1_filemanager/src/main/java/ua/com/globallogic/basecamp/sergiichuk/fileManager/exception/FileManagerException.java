package ua.com.globallogic.basecamp.sergiichuk.fileManager.exception;

public class FileManagerException extends Exception {

    private static final long serialVersionUID = 3272815873329223381L;

    public FileManagerException() {
    }

    public FileManagerException(String message) {
	super(message);
    }

    public FileManagerException(Throwable cause) {
	super(cause);
    }

    public FileManagerException(String message, Throwable cause) {
	super(message, cause);
    }

    public FileManagerException(String message, Throwable cause,
	    boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

}
