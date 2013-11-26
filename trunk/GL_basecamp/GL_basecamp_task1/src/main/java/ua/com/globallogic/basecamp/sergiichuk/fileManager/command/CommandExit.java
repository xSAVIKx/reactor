package ua.com.globallogic.basecamp.sergiichuk.fileManager.command;

import ua.com.globallogic.basecamp.sergiichuk.fileManager.FileManager;

public class CommandExit extends Command {

    @Override
    public void execute(FileManager fm, String arg) {
	System.exit(0);
    }

}
