package com.saucedemo.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TestConfig {
    private static final Properties DEFAULTS = loadDefaults();

    private TestConfig() {
    }

    public static String baseUrl() {
        return value("baseUrl");
    }

    public static String browser() {
        return value("browser").toLowerCase();
    }

    public static boolean headless() {
        return Boolean.parseBoolean(value("headless"));
    }

    public static double slowMo() {
        return Double.parseDouble(value("slowMo"));
    }

    private static String value(String key) {
        String systemValue = System.getProperty(key);
        return systemValue == null || systemValue.isBlank()
                ? DEFAULTS.getProperty(key)
                : systemValue;
    }

    private static Properties loadDefaults() {
        Properties properties = new Properties();
        try (InputStream input = TestConfig.class.getClassLoader()
                .getResourceAsStream("test.properties")) {
            if (input == null) {
                throw new IllegalStateException("test.properties was not found");
            }
            properties.load(input);
            return properties;
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load test.properties", exception);
        }
    }
}
