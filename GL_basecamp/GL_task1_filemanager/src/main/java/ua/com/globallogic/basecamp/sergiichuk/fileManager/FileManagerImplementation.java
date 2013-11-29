package ua.com.globallogic.basecamp.sergiichuk.fileManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.ChangeDirectoryException;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.CreateDirectoryException;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.CreateFileException;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.IllegalPathException;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.RemoveFileException;

/**
 * This class is an implementation of given FileManager interface
 * 
 * @author Iurii Sergiichuk
 * 
 */
public class FileManagerImplementation implements FileManager {

    // current directory file holder
    private File currentDirectory;
    // comparator for LS command
    private Comparator<File> getDirectoriesFirstComparator = new Comparator<File>() {
	@Override
	public int compare(File o1, File o2) {
	    if (o1.isDirectory() && !o2.isDirectory())
		return -1;
	    else if (o2.isDirectory() && !o1.isDirectory()) {
		return 1;
	    } else
		return 0;

	};
    };

    public FileManagerImplementation() {
	File tmp = new File(Main.class.getProtectionDomain().getCodeSource()
		.getLocation().getPath());
	currentDirectory = new File(tmp.getAbsolutePath());
    }

    @Override
    public File getCurrentDirectory() {
	return currentDirectory;
    }

    public void setFilesComparator(Comparator<File> filesComparator) {
	this.getDirectoriesFirstComparator = filesComparator;
    }

    @Override
    public void removeFile(String name) throws RemoveFileException {
	File fileToRemove = getNeededFile(name);
	if (!fileToRemove.delete())
	    throw new RemoveFileException(String.format(
		    "File '%s' cannot be deleted", name));

    }

    /**
     * This method is used to look for file in working directory, if path string
     * doesn't contain absolute path to file
     * 
     * @param path
     *            absolute or relative path to file
     * @return File associated with given path
     */
    private File getNeededFile(String path) {
	File neededFile = null;
	if (path.lastIndexOf(File.separator) != -1) {
	    neededFile = new File(path);
	} else {
	    neededFile = new File(currentDirectory.getPath() + File.separator
		    + path);
	}
	return neededFile;
    }

    @Override
    public void changeDirectory(String path) throws ChangeDirectoryException {
	if (path.endsWith("..."))
	    return;
	File directoryToChange = getNeededFile(path);
	if (directoryToChange.isDirectory()) {
	    currentDirectory = directoryToChange;
	} else {
	    throw new ChangeDirectoryException(String.format(
		    "No such path '%s' was found.", path));

	}
    }

    @Override
    public void createFile(String fileName) throws CreateFileException {
	File fileToCreate = getNeededFile(fileName);
	try {
	    if (!fileToCreate.createNewFile()) {
		throw new CreateFileException(String.format(
			"File with such name: '%s' cannot be created.",
			fileName));
	    }
	} catch (IOException e) {
	    throw new CreateFileException(String.format(
		    "File '%s' cannot be created.", fileName));
	}
    }

    @Override
    public void createDirectory(String directoryName)
	    throws CreateDirectoryException {
	File directoryToCreate = getNeededFile(directoryName);
	if (!directoryToCreate.mkdir()) {
	    throw new CreateDirectoryException(String.format(
		    "Directory '%s' cannot be created.", directoryName));
	}

    }

    @Override
    public Collection<File> getDirectoryStructure() {
	Collection<File> directoryStructure = new ArrayList<>();
	File[] dirStruct = currentDirectory.listFiles();
	Arrays.sort(dirStruct, getDirectoriesFirstComparator);
	for (File element : dirStruct) {
	    directoryStructure.add(element);
	}
	return directoryStructure;
    }

    @Override
    public Collection<File> getDirectoryStructure(String path)
	    throws IllegalPathException {
	File dirToHaveStructure = new File(path);
	Collection<File> directoryStructure = null;
	if (dirToHaveStructure.isDirectory()) {
	    directoryStructure = new ArrayList<>();
	    File[] dirStruct = dirToHaveStructure.listFiles();
	    Arrays.sort(dirStruct, getDirectoriesFirstComparator);
	    for (File element : dirStruct) {
		directoryStructure.add(element);
	    }
	} else {
	    throw new IllegalPathException(String.format(
		    "No such path '%s' was found.", path));
	}
	return directoryStructure;
    }
}