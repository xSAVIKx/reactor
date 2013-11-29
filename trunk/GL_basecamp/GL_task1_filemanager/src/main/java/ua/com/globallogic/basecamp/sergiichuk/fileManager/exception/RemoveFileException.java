package ua.com.globallogic.basecamp.sergiichuk.fileManager.exception;

public class RemoveFileException extends FileManagerException {

    private static final long serialVersionUID = 3972788471316585933L;

    public RemoveFileException() {
    }

    public RemoveFileException(String message) {
	super(message);
    }

    public RemoveFileException(Throwable cause) {
	super(cause);
    }

    public RemoveFileException(String message, Throwable cause) {
	super(message, cause);
    }

    public RemoveFileException(String message, Throwable cause,
	    boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

}
