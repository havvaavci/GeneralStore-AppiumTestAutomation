package com.havva.generalstore.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

        private static Properties properties;

        static {
            try {
                String path = "src/test/resources/config.properties";
                FileInputStream fis = new FileInputStream(path);
                properties = new Properties();
                properties.load(fis);
                fis.close();
            } catch (Exception e) {
                throw new RuntimeException("Config file not found: " + e.getMessage());
            }
        }

        public static String get(String key) {
            // Jenkins override: -Dkey=value
            if (System.getProperty(key) != null) {
                return System.getProperty(key);
            }
            return properties.getProperty(key);
        }
    }


