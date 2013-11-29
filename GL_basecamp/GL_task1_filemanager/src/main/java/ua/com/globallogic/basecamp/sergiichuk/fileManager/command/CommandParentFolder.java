package ua.com.globallogic.basecamp.sergiichuk.fileManager.command;

import ua.com.globallogic.basecamp.sergiichuk.fileManager.FileManager;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.ChangeDirectoryException;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.IllegalPathException;

public class CommandParentFolder extends Command {

    @Override
    public void execute(FileManager fm, String arg)
	    throws ChangeDirectoryException, IllegalPathException {
	if (fm == null)
	    throw new IllegalArgumentException("FileManager cannot be null");
	String parent = fm.getCurrentDirectory().getParent();
	if (parent == null) {
	    throw new IllegalPathException("No parent directory");
	} else {

	    fm.changeDirectory(parent);
	}
    }

}
