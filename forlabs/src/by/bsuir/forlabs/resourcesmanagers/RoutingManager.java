package by.bsuir.forlabs.resourcesmanagers;

import java.util.ResourceBundle;

public class RoutingManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.routing");
    // класс извлекает информацию из файла routing.properties
    private RoutingManager() { }
    synchronized public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}