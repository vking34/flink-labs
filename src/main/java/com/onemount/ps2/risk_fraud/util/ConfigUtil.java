package com.onemount.ps2.risk_fraud.util;

import org.apache.flink.shaded.jackson2.org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.Reader;
import java.util.Properties;

public class ConfigUtil {

    private final static Properties PROPERTIES = new Properties();


    public static Properties loadConfig() throws Exception {
        try (Reader reader = new FileReader("/home/vuonglv3/Desktop/ps2/lab/risk-fraud/src/main/resources/application.properties")) {
            PROPERTIES.load(reader);
            return PROPERTIES;
        }
    }

    public static Properties getProperties() throws Exception {
        if (PROPERTIES.isEmpty()) {
            loadConfig();
        }

        return PROPERTIES;
    }
}
