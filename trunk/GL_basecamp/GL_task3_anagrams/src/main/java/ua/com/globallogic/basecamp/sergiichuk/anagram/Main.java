package ua.com.globallogic.basecamp.sergiichuk.anagram;

import java.io.File;

public class Main {

    private static void usage() {
	System.out
		.println("usage: anagram inputFileName outputFileName\n inputFileName should not be equals to outputFileName");
    }

    public static void main(String[] args) {
	if (args.length != 2 || args[0].equalsIgnoreCase(args[1])) {
	    usage();
	    return;
	}
	File input = new File(args[0]);
	if (!input.exists()) {
	    System.err.println(String.format("No such file '%s' was found.",
		    args[0]));
	    return;
	}
	File output = new File(args[1]);
	Runner setRunner = new SetRunner();
	setRunner.run(input, output);
    }

}
