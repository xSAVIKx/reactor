package ua.com.globallogic.basecamp.sergiichuk.fileManager.command;

import ua.com.globallogic.basecamp.sergiichuk.fileManager.CommandName;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.FileManager;

public class CommandHelp extends Command {
    private final static CommandName[] commandArray = CommandName.values();
    private final static String helpStringBegin = "usage:\n";

    private String getHelpString(String arg) {
	StringBuilder stringBuilder = new StringBuilder(helpStringBegin);
	boolean isCommandFound = false;
	for (CommandName element : commandArray) {
	    if (element.getCommandName().equalsIgnoreCase(arg)) {
		stringBuilder.append(element.getCommandDescription());
		isCommandFound = true;
		break;
	    }
	}
	if (isCommandFound)
	    return stringBuilder.toString();
	else
	    return getHelpString();
    }

    private String getHelpString() {
	StringBuilder stringBuilder = new StringBuilder(helpStringBegin);
	for (CommandName element : commandArray) {
	    stringBuilder.append(element.getCommandDescription());
	    stringBuilder.append('\n');
	}
	return stringBuilder.toString();
    }

    @Override
    public void execute(FileManager fm, String arg) {
	String helpString = "";
	if (arg != null && !arg.isEmpty())
	    helpString = getHelpString(arg);
	else
	    helpString = getHelpString();
	System.out.println(helpString);
    }

}
