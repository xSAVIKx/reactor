package ua.com.globallogic.basecamp.sergiichuk.fileManager.exception;

public class CreateDirectoryException extends FileManagerException {

    private static final long serialVersionUID = 4570710884674809445L;

    public CreateDirectoryException() {
    }

    public CreateDirectoryException(String message) {
	super(message);
    }

    public CreateDirectoryException(Throwable cause) {
	super(cause);
    }

    public CreateDirectoryException(String message, Throwable cause) {
	super(message, cause);
    }

    public CreateDirectoryException(String message, Throwable cause,
	    boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

}
