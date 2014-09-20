package by.bsuir.forlabs.resourcesmanagers;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.config");
    // класс извлекает информацию из файла config.properties
    private ConfigurationManager() { }
    synchronized public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}