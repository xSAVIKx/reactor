package ua.com.globallogic.basecamp.sergiichuk.fileManager;

public enum CommandName {
    LS_COMMAND("ls", "ls [path] - show directory files. If path is set - show path directory files."), 
    CD_COMMAND("cd", "cd 'path' - change current directory to 'path'."), 
    PARENT_FOLDER_COMMAND("..", ".. - change current directory to parent directory."), 
    TOUCH_COMMAND("touch", "touch fileName - create file with 'fileName' name."), 
    MKDIR_COMMAND("mkdir", "mkdir 'dirName' - create directory with given 'dirName' name."), 
    RM_COMMAND("rm", "rm name - remove given 'name'."), 
    HELP_COMMAND("help", "help [commandName] - show help info. If 'commandName' is set - tries to show help info about 'commandName' command."), 
    EXIT_COMMAND("exit", "exit - terminates the programm."), 
    NO_COMMAND("noCommand");
    private String commandName;
    private String commandDescription;

    CommandName(String commandName) {
	this.commandName = commandName;
	commandDescription = "";
    }

    CommandName(String commandName, String commandDescription) {
	this.commandName = commandName;
	this.commandDescription = commandDescription;
    }

    public String getCommandName() {
	return commandName;
    }

    public String getCommandDescription() {
	return commandDescription;
    }
}
