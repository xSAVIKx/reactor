package ua.com.globallogic.basecamp.sergiichuk.fileManager.command;

import ua.com.globallogic.basecamp.sergiichuk.fileManager.FileManager;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.CreateDirectoryException;

public class CommandMkDir extends Command {

    @Override
    public void execute(FileManager fm, String arg)
	    throws CreateDirectoryException {
	if (fm == null || arg == null) {
	    throw new IllegalArgumentException("Arguments cannot be null");
	}

	fm.createDirectory(arg);

    }

}
