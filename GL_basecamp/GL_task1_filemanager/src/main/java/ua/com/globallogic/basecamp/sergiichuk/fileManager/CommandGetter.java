package ua.com.globallogic.basecamp.sergiichuk.fileManager;

import java.util.Scanner;

public class CommandGetter {
    private Scanner scanner;

    public CommandGetter() {
	scanner = new Scanner(System.in);
	scanner.useDelimiter("\\n");
    }

    public String[] getCommandSyntax() {
	String[] commandSyntax = null;
	String readedLine = null;
	if (scanner.hasNextLine())
	    readedLine = scanner.nextLine();
	if (readedLine != null)
	    commandSyntax = readedLine.split(" ", 2);

	return commandSyntax;
    }

    public static String getCommand(String[] commandSyntax) {
	if (commandSyntax != null)
	    return commandSyntax[0];
	return "";
    }

    public static String getArgument(String[] commandSyntax) {
	if (commandSyntax != null && commandSyntax.length > 1)
	    return commandSyntax[1];
	return "";
    }
}
