package ua.com.globallogic.basecamp.sergiichuk.fileManager.command;

import java.util.HashMap;
import java.util.Map;

import ua.com.globallogic.basecamp.sergiichuk.fileManager.CommandName;

public final class CommandContainer {

    private static Map<String, Command> commandContainer = new HashMap<>();

    static {
	commandContainer.put(CommandName.CD_COMMAND.getCommandName(),
		new CommandCd());
	commandContainer.put(CommandName.LS_COMMAND.getCommandName(),
		new CommandLs());
	commandContainer.put(CommandName.MKDIR_COMMAND.getCommandName(),
		new CommandMkDir());
	commandContainer.put(CommandName.TOUCH_COMMAND.getCommandName(),
		new CommandTouch());
	commandContainer.put(CommandName.HELP_COMMAND.getCommandName(),
		new CommandHelp());
	commandContainer.put(
		CommandName.PARENT_FOLDER_COMMAND.getCommandName(),
		new CommandParentFolder());
	commandContainer.put(CommandName.RM_COMMAND.getCommandName(),
		new CommandRm());
	commandContainer.put(CommandName.EXIT_COMMAND.getCommandName(),
		new CommandExit());
	commandContainer.put(CommandName.NO_COMMAND.getCommandName(),
		new CommandNoCommand());
    }

    public static Command get(String commandName) {
	if (commandName == null || !commandContainer.containsKey(commandName)) {
	    return commandContainer
		    .get(CommandName.NO_COMMAND.getCommandName());
	}
	return commandContainer.get(commandName);
    }

}
