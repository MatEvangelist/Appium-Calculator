package com.mathews.report.screenshot;

import com.mathews.report.screenshot.impl.RunScreenshot;
import com.mathews.report.screenshot.impl.UntilScreenshot;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Screenshot {

    public static byte[] take(AndroidDriver<MobileElement> androidDriver) {
        return new RunScreenshot().take(UntilScreenshot.screenshotMobile(androidDriver));
    }

    public static byte[] take(AndroidDriver<MobileElement> androidDriver, MobileElement... elements) {
        return new RunScreenshot().take(UntilScreenshot.screenshotMobile(androidDriver, elements));
    }
}
