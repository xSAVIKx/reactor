package ua.com.globallogic.basecamp.sergiichuk.fileManager.command;

import ua.com.globallogic.basecamp.sergiichuk.fileManager.FileManager;

public class CommandNoCommand extends Command {

    @Override
    public void execute(FileManager fm, String arg) {
	System.err.println("No such command. Try to use 'help' command.");
    }

}
