package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MobileProperties {

    Properties prop = new Properties();
    File file = new File("mobileProperties");

    public String getProp(String valor) {
        try {
            if (System.getProperty("env") == null) {
                prop.load(getClass().getClassLoader().getResourceAsStream("dev.properties"));
            } else {
                prop.load(getClass().getClassLoader().getResourceAsStream(System.getProperty("env")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(valor);
    }
}


