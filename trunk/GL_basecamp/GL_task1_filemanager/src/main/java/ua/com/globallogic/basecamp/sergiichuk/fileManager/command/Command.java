package ua.com.globallogic.basecamp.sergiichuk.fileManager.command;

import ua.com.globallogic.basecamp.sergiichuk.fileManager.FileManager;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.FileManagerException;

public abstract class Command {

    public abstract void execute(FileManager fm, String arg)
	    throws FileManagerException;

    @Override
    public String toString() {
	return getClass().getSimpleName();
    }

}
