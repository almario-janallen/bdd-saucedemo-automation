package config;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* This class reads values from .properties files so the rest of the code never hardcodes URLs, usernames, or browser names*/

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        // 1. Always load config.properties (shared settings)
        loadFile("src/test/resources/config/config.properties");

        // 2. Load environment-specific file on top of it
        String env = System.getProperty("env", "qa"); // defaults to qa
        loadFile("src/test/resources/config/" + env + ".properties");
    }

    private static void loadFile(String path) {
        try (FileInputStream fis = new FileInputStream(path)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config file: " + path, e);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config");
        }
        return value;
    }
}
