package by.bsuir.forlabs.resourcesmanagers;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {

    private ResourceBundle resourceBundle;
    private final String resourceName = "resources.messages";

    public MessageManager(Locale locale) {
        if (locale == null) {
            throw new IllegalArgumentException("Parameter \"locale\" cannot be null.");
        }
        resourceBundle = ResourceBundle.getBundle(resourceName, locale);
    }

    public String getProperty(String key) {
            return resourceBundle.getString(key);
    }
}
