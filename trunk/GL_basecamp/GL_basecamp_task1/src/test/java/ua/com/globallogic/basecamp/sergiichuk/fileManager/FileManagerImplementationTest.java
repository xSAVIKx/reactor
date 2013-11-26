package ua.com.globallogic.basecamp.sergiichuk.fileManager;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.ChangeDirectoryException;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.CreateDirectoryException;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.CreateFileException;
import ua.com.globallogic.basecamp.sergiichuk.fileManager.exception.RemoveFileException;

public class FileManagerImplementationTest {
    private FileManager fm;
    private final static String CREATE_FILE_TMP_FILENAME = "createFileTmp";
    private final static String REMOVE_FILE_TMP_FILENAME = "removeFileTmp";
    private final static String DIRECTORY_TO_CHANGE = ".";
    private final static String CREATE_DIRECTORY_TMP_FILENAME = "createDirectoryTmp";

    @Before
    public void setUp() throws Exception {
	fm = mock(FileManagerImplementation.class);
    }

    @Test
    public void testRemoveFileTmp() throws RemoveFileException {
	doNothing().when(fm).removeFile(REMOVE_FILE_TMP_FILENAME);
	fm.removeFile(REMOVE_FILE_TMP_FILENAME);
	verify(fm).removeFile(REMOVE_FILE_TMP_FILENAME);
    }

    @Test
    public void testChangeDirectory() throws ChangeDirectoryException {
	doNothing().when(fm).changeDirectory(DIRECTORY_TO_CHANGE);
	fm.changeDirectory(DIRECTORY_TO_CHANGE);
	verify(fm).changeDirectory(DIRECTORY_TO_CHANGE);
    }

    @Test
    public void testCreateFileTmp() throws CreateFileException {
	doNothing().when(fm).createFile(CREATE_FILE_TMP_FILENAME);
	fm.createFile(CREATE_FILE_TMP_FILENAME);
	verify(fm).createFile(CREATE_FILE_TMP_FILENAME);
    }

    @Test
    public void testCreateDirectory() throws CreateDirectoryException {
	doNothing().when(fm).createDirectory(CREATE_DIRECTORY_TMP_FILENAME);
	fm.createDirectory(CREATE_DIRECTORY_TMP_FILENAME);
	verify(fm).createDirectory(CREATE_DIRECTORY_TMP_FILENAME);
    }

}
