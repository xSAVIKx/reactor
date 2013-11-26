package ua.com.globallogic.basecamp.sergiichuk.fileManager.command;

import ua.com.globallogic.basecamp.sergiichuk.fileManager.FileManager;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.ChangeDirectoryException;

public class CommandCd extends Command {

    @Override
    public void execute(FileManager fm, String arg)
	    throws ChangeDirectoryException {
	if (fm == null || arg == null)
	    throw new IllegalArgumentException("arguments cannot be null");

	fm.changeDirectory(arg);
    }

}
