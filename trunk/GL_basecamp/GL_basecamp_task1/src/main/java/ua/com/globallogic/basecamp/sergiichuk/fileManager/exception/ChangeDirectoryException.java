package ua.com.globallogic.basecamp.sergiichuk.fileManager.exception;

public class ChangeDirectoryException extends FileManagerException {

    private static final long serialVersionUID = -8834820524609062247L;

    public ChangeDirectoryException() {
	super();
    }

    public ChangeDirectoryException(String message, Throwable cause,
	    boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);

    }

    public ChangeDirectoryException(String message, Throwable cause) {
	super(message, cause);

    }

    public ChangeDirectoryException(String message) {
	super(message);

    }

    public ChangeDirectoryException(Throwable cause) {
	super(cause);

    }

}
