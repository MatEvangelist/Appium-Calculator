package com.mathews.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MobileProperties {

    static Properties prop = new Properties();
    static File file = new File("mobileProperties");

    public static String getProp(String valor) {
        try {
            if (System.getProperty("env") == null) {
                prop.load(new FileInputStream(file.getAbsolutePath() + "/dev.properties"));
            } else {
                prop.load(new FileInputStream(file.getCanonicalPath() + "/" + System.getProperty("env")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(valor);
    }
}


