package ua.com.globallogic.basecamp.sergiichuk.fileManager;

import java.io.File;
import java.util.Collection;

import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.ChangeDirectoryException;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.CreateDirectoryException;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.CreateFileException;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.IllegalPathException;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.RemoveFileException;

/**
 * File manager interface
 * 
 * @author Iurii Sergiichuk
 * 
 */
public interface FileManager {
    /**
     * This method returns File associated with current directory
     * 
     * @return current directory File
     */
    File getCurrentDirectory();

    /**
     * This method tries to change current directory
     * 
     * @param path
     *            path to change current directory to
     */
    void changeDirectory(String path) throws ChangeDirectoryException;

    /**
     * This method tries to create file with given fileName
     * 
     * @param fileName
     *            name to create file with
     */
    void createFile(String fileName) throws CreateFileException;

    /**
     * This method tries to create directory with given directoryName
     * 
     * @param directoryName
     *            name to create directory with
     */
    void createDirectory(String directoryName) throws CreateDirectoryException;

    /**
     * This method tries to remove file which is associated with given name
     * 
     * @param name
     *            name of file to remove
     */
    void removeFile(String name) throws RemoveFileException;

    /**
     * This method return current directory structure
     * 
     * @return structure of current directory
     */
    Collection<File> getDirectoryStructure();

    /**
     * This method return structure of path-associated directory
     * 
     * @param path
     *            path to directory which structure should be returned
     * @return structure of needed directory
     */
    Collection<File> getDirectoryStructure(String path)
	    throws IllegalPathException;
}
