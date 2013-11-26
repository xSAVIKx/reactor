package ua.com.globallogic.basecamp.sergiichuk.fileManager;

import ua.com.globallogic.basecamp.sergiichuk.fileManager.command.Command;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.command.CommandContainer;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.FileManagerException;

/**
 * Runner class
 * 
 * @author Iurii Sergiichuk
 * 
 */
public class Runner {
    private FileManager fm;

    public Runner() {
	fm = new FileManagerImplementation();
    }

    public Runner(FileManager fm) {
	this.fm = fm;
    }

    public void run() {
	CommandGetter commandGetter = new CommandGetter();
	while (true) {
	    try {
		String[] commandSyntax = commandGetter.getCommandSyntax();
		String command = CommandGetter.getCommand(commandSyntax);
		String argument = CommandGetter.getArgument(commandSyntax);
		Command commandToExecute = CommandContainer.get(command);
		commandToExecute.execute(fm, argument);
	    } catch (FileManagerException e) {
		String msg = e.getLocalizedMessage();
		if (msg != null && !msg.isEmpty()) {
		    System.err.println(msg);
		} else {
		    e.printStackTrace();
		}
	    }
	}
    }
}
