package com.mathews.report.screenshot.impl;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class UntilScreenshot {

//    public static IScreenshot<byte[]> screenShotMobile(final AndroidDriver<MobileElement> androidDriver) {
//        return input -> ScreenshotUtils.screenshot(androidDriver);
//    }

    public static IScreenshot<byte[]> screenshotMobile(final AndroidDriver<MobileElement> androidDriver) {
        return new IScreenshot<byte[]>() {
            @Override
            public byte[] apply(Object input) {
                return ScreenshotUtils.screenshot(androidDriver);
            }
        };
    }

    public static IScreenshot<byte[]> screenshotMobile(final AndroidDriver<MobileElement> androidDriver, final MobileElement... elements) {
        return new IScreenshot<byte[]>() {
            @Override
            public byte[] apply(Object input) {
                String jsHighlight = ScreenshotUtils.getJavaScriptHighlight(androidDriver, elements);
                String jsUnHighlight = ScreenshotUtils.getJavaScriptUnHighlight(androidDriver, elements);
                ScreenshotUtils.executeJavaScript(androidDriver, jsHighlight, elements);
                byte[] screenshot = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.BYTES);
                ScreenshotUtils.executeJavaScript(androidDriver, jsUnHighlight, elements);
                return screenshot;
            }
        };
    }
}
