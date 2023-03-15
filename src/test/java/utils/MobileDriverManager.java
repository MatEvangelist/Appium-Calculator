package utils;

import com.microsoft.appcenter.appium.Factory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MobileDriverManager {

    @Rule
    public TestWatcher watcher = Factory.createWatcher();

        protected static AndroidDriver<MobileElement> mobDriver;
//    protected static EnhancedAndroidDriver<MobileElement> mobDriver;

    public static void createDriver() throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        DesiredCapabilities cap = new DesiredCapabilities();
        if (mobDriver == null) {
            File appAndroid = new File("src/test/resources/apks/calculadora.apk");
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, "pixel_5");
            cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            cap.setCapability(MobileCapabilityType.APP, appAndroid.getAbsolutePath());
            cap.setCapability("autoGrantPermissions", true);

//            mobDriver = Factory.createAndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
            mobDriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        }

        mobDriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
    }

//    public static EnhancedAndroidDriver<MobileElement> getMobDriver() {
    public static AndroidDriver<MobileElement> getMobDriver() {
        if (mobDriver == null) {
            try {
                createDriver();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mobDriver;
    }


    public static void killDriver() {
        if (mobDriver != null) {
//            mobDriver.label("Stopping App");
            mobDriver.closeApp();
            mobDriver.quit();
            mobDriver = null;
        }
    }

    public static void relaunchApp() {
        mobDriver.launchApp();
    }
}
