package ua.com.globallogic.basecamp.sergiichuk.fileManager.command;

import ua.com.globallogic.basecamp.sergiichuk.fileManager.FileManager;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.RemoveFileException;

public class CommandRm extends Command {

    @Override
    public void execute(FileManager fm, String arg) throws RemoveFileException {
	if (fm == null || arg == null)
	    throw new IllegalArgumentException("Arguments cannot be null");

	fm.removeFile(arg);

    }

}
