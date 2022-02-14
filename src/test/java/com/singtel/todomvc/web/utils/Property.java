package com.singtel.todomvc.web.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Property {
    static final Log logger = LogFactory.getLog(Property.class);

    public Property() {
    }


    public static String get(String key) {
        try {
            if (!System.getenv(key).trim().isEmpty()) {
                logger.info("Using {" + key + "} value from environment variable");
                return System.getenv(key);
            } else {
                logger.info("No value for " + key);
                return "";
            }
        } catch (NullPointerException var4) {
            try {
                if (!System.getProperty(key).trim().isEmpty()) {
                    logger.info("Using " + key + " value from property");
                    return System.getProperty(key);
                } else {
                    logger.info("No value for " + key);
                    return "";
                }
            } catch (NullPointerException var3) {
                logger.info("No value for " + key);
                return "";
            }
        }
    }
}