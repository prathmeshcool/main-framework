package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties props = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }

    public static int getInt(String key, int defaultVal) {
        String v = props.getProperty(key);
        if (v == null) return defaultVal;
        return Integer.parseInt(v);
    }

    public static boolean getBool(String key, boolean defaultVal) {
        String v = props.getProperty(key);
        if (v == null) return defaultVal;
        return Boolean.parseBoolean(v);
    }
}
