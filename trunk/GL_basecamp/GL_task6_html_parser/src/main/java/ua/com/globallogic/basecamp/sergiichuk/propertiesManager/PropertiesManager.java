package ua.com.globallogic.basecamp.sergiichuk.propertiesManager;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger
	    .getLogger(PropertiesManager.class);

    public static Properties defaultProperties = new Properties();
    static {
	for (Property element : Property.values()) {
	    defaultProperties
		    .setProperty(element.getName(), element.getValue());
	}
    }
    private Properties currentProperties;

    public PropertiesManager() {
	currentProperties = new Properties();
	currentProperties.putAll(defaultProperties);
    }

    public Properties getProperties() {
	if (logger.isDebugEnabled()) {
	    logger.debug("getProperties() - start"); //$NON-NLS-1$
	}

	File propertyFile = new File(Property.PROPERTIES_FILE_NAME.getValue());
	if (propertyFile.exists()) {
	    try {
		currentProperties.load(new FileReader(propertyFile));
	    } catch (IOException ignored) {
		logger.warn("getProperties() - exception ignored", ignored); //$NON-NLS-1$
	    }
	} else {
	    try {
		currentProperties.store(new FileWriter(propertyFile), "");
	    } catch (IOException ignored) {
		logger.warn("getProperties() - exception ignored", ignored); //$NON-NLS-1$
	    }
	}
	if (currentProperties.isEmpty())
	    currentProperties = new Properties(defaultProperties);

	if (logger.isDebugEnabled()) {
	    logger.debug("getProperties() - end - return value=" + currentProperties); //$NON-NLS-1$
	}
	return currentProperties;

    }

}
