package com.orangehrm.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads config.properties once on class load.
 * All tests and pages get values via static getters — zero hardcoding.
 */
public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("config.properties not found on classpath");
            }
            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getBaseUrl()            { return properties.getProperty("base.url"); }
    public static String getValidUsername()      { return properties.getProperty("valid.username"); }
    public static String getValidPassword()      { return properties.getProperty("valid.password"); }
    public static String getBrowser()            { return properties.getProperty("browser", "chrome"); }
    public static int    getExplicitWaitSeconds(){ return Integer.parseInt(properties.getProperty("explicit.wait.seconds", "10")); }
    public static boolean isHeadless()           { return Boolean.parseBoolean(properties.getProperty("headless", "false")); }
}
