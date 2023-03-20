package com.mathews.core;

import com.mathews.utils.MobileProperties;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class AppiumServerConfig {

    private static AppiumDriverLocalService service;
    private static AppiumServiceBuilder builder;
    private static DesiredCapabilities cap;

    public static void startServer() {
        if (service == null) {
            //Set Capabilities
            cap = new DesiredCapabilities();
            cap.setCapability("noReset", "false");

            //Build the Appium service
            builder = new AppiumServiceBuilder();
            builder.withIPAddress("127.0.0.1");
            builder.usingPort(4723);
            builder.withCapabilities(cap);
            builder.usingDriverExecutable(new File(MobileProperties.getProp("nodeJs_path")));
            builder.withAppiumJS(new File(MobileProperties.getProp("mainJs_path")));
            builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
            builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

            //Start the server with the builder
            service = AppiumDriverLocalService.buildService(builder);
            service.start();
        }
    }

    public static void stopServer() {
        if (service != null) {
            if (service.isRunning()) {
                service.stop();
                service = null;
            }
        }
    }
}
