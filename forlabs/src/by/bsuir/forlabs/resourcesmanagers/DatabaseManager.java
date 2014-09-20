package by.bsuir.forlabs.resourcesmanagers;

import java.util.ResourceBundle;

public class DatabaseManager {

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.database");
    // класс извлекает информацию из файла database.properties

    private DatabaseManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
