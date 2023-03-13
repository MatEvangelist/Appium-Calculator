package utils;

import com.microsoft.appcenter.appium.Factory;
import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MobileDriverManager {

    protected static EnhancedAndroidDriver<MobileElement> mobDriver;

    public static void createDriver() throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        DesiredCapabilities cap = new DesiredCapabilities();
        if (mobDriver == null) {
                File appAndroid = new File("src/test/resources/apks/calculadora.apk");
                cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
                cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                cap.setCapability(MobileCapabilityType.APP, appAndroid.getAbsolutePath());
                cap.setCapability("autoGrantPermissions", true);
            mobDriver = Factory.createAndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);

        }

        mobDriver.manage().timeouts().implicitlyWait(120,TimeUnit.SECONDS);
    }

    public static void closeDriver() {
        mobDriver.close();

    }

    public static void relaunchApp() {
        mobDriver.launchApp();
    }
}
