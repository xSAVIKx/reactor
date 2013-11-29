package ua.com.globallogic.basecamp.sergiichuk.fileManager.command;

import java.io.File;
import java.util.Collection;

import ua.com.globallogic.basecamp.sergiichuk.fileManager.FileManager;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.IllegalPathException;

public class CommandLs extends Command {

    @Override
    public void execute(FileManager fm, String arg) throws IllegalPathException {
	if (fm == null)
	    throw new IllegalArgumentException("FileManager cannot be null");

	Collection<File> dirStruct = null;

	if (arg == null || arg.isEmpty())
	    dirStruct = fm.getDirectoryStructure();
	else
	    dirStruct = fm.getDirectoryStructure(arg);
	for (File element : dirStruct) {
	    System.out.println(element);
	}

    }

}
