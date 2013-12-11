package ua.com.globallogic.basecamp.sergiichuk.propertiesManager;

public enum Property {
    PROPERTIES_FILE_NAME("propertiesFileName", "parser.properties"), RESULT_FOLDER(
	    "resultFolder", "result"), NESTING_LEVEL("nestingLevel", "1");
    private String name;
    private String value;

    private Property(String name, String value) {
	this.name = name;

	this.value = value;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

}
