package com.mathews.core;


import com.mathews.utils.MobileProperties;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MobileDriverManager {

    private static WebDriverWait wait;
    private static AndroidDriver<MobileElement> mobDriver;

    public static void createDriver() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        if (mobDriver == null) {
            cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            cap.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + MobileProperties.getProp("appCalculator"));
            cap.setCapability(MobileCapabilityType.UDID, "emulator-5554");
            cap.setCapability("autoGrantPermissions", true);

            mobDriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        }

        wait = new WebDriverWait(mobDriver, BasePage.DEFAULT_SECONDS);
    }

    public static AndroidDriver<MobileElement> getMobDriver() {
        if (mobDriver == null) {
            try {
                createDriver();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mobDriver;
    }

    public static WebDriverWait getDriverWait() throws MalformedURLException {
        if (mobDriver == null) {
            createDriver();
        }
        return wait;
    }

    public static WebDriverWait getDriverWait(int sec) throws MalformedURLException {
        if (mobDriver == null) {
            createDriver();
        }

        wait = new WebDriverWait(mobDriver, sec);
        return wait;
    }

    public static void killDriver() {
        if (mobDriver != null) {
            mobDriver.closeApp();
            mobDriver.quit();
            mobDriver = null;
        }
    }

    public static void relaunchApp() {
        mobDriver.launchApp();
    }
}
