package com.singtel.todomvc.web.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Environment {
    private ClassLoader classLoader = this.getClass().getClassLoader();
    private Properties properties;
    static final Log logger = LogFactory.getLog(Environment.class);
    private static Environment instance = null;

    public static Environment getInstance(String configFile) {
        if (instance == null) {
            try {
                instance = new Environment(configFile);
            } catch (IOException var2) {
                logger.info(var2);
                logger.error("Unable to find " + configFile + " trying to read from env variable");
                instance = new Environment();
            }
        }

        return instance;
    }

    private Environment() {
    }

    private Environment(String configFile) throws IOException {
        this.properties = new Properties();
        InputStream inputStream = null;
        inputStream = new FileInputStream(new File(this.classLoader.getResource(configFile).getFile()));
        this.properties.load(inputStream);
    }

    public String getProperty(String key) {
        String result = null;
        if (this.properties != null) {
            result = this.properties.getProperty(key);
        }

        return result == null ? Property.get(key) : result;
    }

    public String getPropertyOrDefault(String key, String defaultValue) {
        String value = this.getProperty(key);
        return value != null && !value.isEmpty() ? this.getProperty(key) : defaultValue;
    }
}
