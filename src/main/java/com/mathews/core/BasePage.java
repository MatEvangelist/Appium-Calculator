package com.mathews.core;

import com.mathews.report.screenshot.ScenarioRepository;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.mathews.core.MobileDriverManager.getDriverWait;
import static com.mathews.core.MobileDriverManager.getMobDriver;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(new AppiumFieldDecorator(getMobDriver(), Duration.ofSeconds(10)), this);
    }

    public static final int DEFAULT_SECONDS = 15;
    private static final int DEFAULT_AMOUNT_OF_SWIPE = 3;

    private static final Logger log = Logger.getLogger(BasePage.class.getName());

    /**
     * Method to click on a specific element located with a certain wait time in seconds
     *
     * @param element
     * @param sec
     */
    protected void clickElement(MobileElement element, int sec) throws MalformedURLException {
        try {
            waitElementToBeClickable(element, sec);
            element.click();
        } catch (ElementNotInteractableException e) {
            tapElement(element);
        } catch (WebDriverException e) {
            JavascriptExecutor js = getMobDriver();
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            log.error("Falha ao clicar no elemento :" + element + " - " + e);
        }
    }

    /**
     * Method to click on a specific element located with the default wait time in seconds
     *
     * @param element
     */
    protected void clickElement(MobileElement element) throws MalformedURLException {
        clickElement(element, DEFAULT_SECONDS);
    }

    protected void clickElement(By by, int sec) throws MalformedURLException {
        waitElementToBeClickable(by, sec);
        getMobDriver().findElement(by).click();
    }

    protected void clickElement(By by) throws MalformedURLException {
        clickElement(by, DEFAULT_SECONDS);
    }

    protected String getText(MobileElement element, int sec) {
        String valor = null;
        try {
            waitVisibilityOfElement(element, sec);
            valor = element.getText();
        } catch (Exception e) {
            log.error("Falha ao clicar no elemento: " + element + " - " + e);
        }
        return valor;
    }

    protected String getText(MobileElement element) throws MalformedURLException {
        return getText(element, DEFAULT_SECONDS);
    }

    protected String getText(By by, int sec) throws MalformedURLException {
        waitVisibilityOfElement(by, sec);
        return getMobDriver().findElement(by).getText();
    }

    protected String getText(By by) throws MalformedURLException {
        return getText(by, DEFAULT_SECONDS);
    }

    protected void clearText(MobileElement element, int sec) throws MalformedURLException {
        waitPresenceOfElementLocated(element, sec);
    }

    protected void clearText(MobileElement element) throws MalformedURLException {
        clearText(element, DEFAULT_SECONDS);
    }

    protected void clearText(By by, int sec) throws MalformedURLException {
        waitPresenceOfElementLocated(by);
        getMobDriver().findElement(by).clear();
    }

    protected void clearText(By by) throws MalformedURLException {
        clearText(by, DEFAULT_SECONDS);
    }

    protected void sendText(MobileElement element, int sec, String txt) throws MalformedURLException {
        waitPresenceOfElementLocated(element, sec);
        element.sendKeys(txt);
    }

    protected void sendText(MobileElement element, String txt) throws MalformedURLException {
        sendText(element, DEFAULT_SECONDS, txt);
    }

    protected void tap(int x, int y) {
        new TouchAction<>(getMobDriver()).tap(PointOption.point(new Point(x, y))).perform();
    }

    protected void tapElement(MobileElement element, int sec) throws MalformedURLException {
        waitPresenceOfElementLocated(element, sec);
        int x = element.getLocation().x + (getElementWidth(element) / 2);
        int y = element.getLocation().y + (getElementHeight(element) / 2);
        new TouchAction<>(getMobDriver()).tap(PointOption.point(new Point(x, y))).perform();
    }

    protected void tapWithJavaScript(double x, double y) {
        JavascriptExecutor js = getMobDriver();
        HashMap<String, Double> tapObject = new HashMap<String, Double>();
        tapObject.put("X", x);
        tapObject.put("Y", y);
        js.executeScript("mobile:tap", tapObject);
    }


    protected void tapElement(MobileElement element) throws MalformedURLException {
        tapElement(element, DEFAULT_SECONDS);
    }

    protected void swipe(double start, double end, double atHeight) {
        int y = (int) (getScreenHeight() * atHeight);
        int x_start = (int) (getScreenWidth() * start);
        int x_end = (int) (getScreenWidth() * end);

        new TouchAction<>(getMobDriver()).press(PointOption.point(x_start, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(x_end, y)).release().perform();
    }

    protected void swipe(double start, double end) {
        swipe(start, end, 0.5);
    }

    protected void swipeToLeft() {
        swipe(0.1, 0.9);
    }

    protected void swipeToRight() {
        swipe(0.9, 0.1);
    }

    protected void swipeElement(MobileElement element, double start, double end) throws MalformedURLException {
        int y = element.getLocation().y + (getElementHeight(element) / 2);
        int x_start = (int) (getElementWidth(element) * start) + element.getLocation().getX();
        int x_end = (int) (getElementWidth(element) * end) + element.getLocation().getX();

        new TouchAction<>(getMobDriver()).press(PointOption.point(x_start, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                        .moveTo(PointOption.point(x_end, y)).release().perform();
    }

    protected void getWait(int num) {
        try {
            Thread.sleep(num * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void hideKeyBoard() {
        getMobDriver().hideKeyboard();
    }

    protected List<MobileElement> getElementList(MobileElement... elements) {
        return Arrays.stream(elements).collect(Collectors.toList());
    }

    protected List<String> getStringList(MobileElement... elements) {
        return Arrays.stream(elements).map(MobileElement::getText).collect(Collectors.toList());
    }

    protected void printElementsTextLocated(MobileElement element) {
        getStringList(element).forEach(System.out::println);
    }

    private int getElementWidth(MobileElement element) throws MalformedURLException {
        waitPresenceOfElementLocated(element);
        return element.getSize().width;
    }

    private int getElementHeight(MobileElement element) throws MalformedURLException {
        waitPresenceOfElementLocated(element);
        return element.getSize().height;
    }

    private int getScreenWidth() {
        return getMobDriver().manage().window().getSize().width;
    }

    private int getScreenHeight() {
        return getMobDriver().manage().window().getSize().height;
    }

    private By getByAndLocator(MobileElement element) {
        String data = element.toString().split(
                "(?=\\sid:\\s|\\saccessibility:\\s|\\sclassName:\\s|\\sxpath:\\s|" +
                        "By.id:\\s|MobileBy.AccessibilityId:\\s|MobileBy.className:\\s|" +
                        "By.className:\\s|MobileBy.id:\\s|By.xpath:\\s|MobileBy.xpath\\s)")[1];

        String[] locator = StringUtils.removeEnd(data, "]").split(":\\s");

        String by = locator[0];
        String identifier = StringUtils.removeEnd(locator[1], "})");

        switch (by) {
            case "id":
            case "By.id":
            case "MobileBy.id":
                return By.id(identifier);
            case "accessibility":
            case "MobileBy.AccessibilityId":
                return MobileBy.AccessibilityId(identifier);
            case "By.className":
            case "MobileBy.className":
            case "className":
                return By.className(identifier);
            case "xpath":
            case "By.xpath":
            case "MobileBy.xpath":
                return By.xpath(identifier);
            default:
                log.error("Error! [" + by + "]");
                return null;
        }
    }

    private void waitPresenceOfElementLocated(By by, int sec) throws MalformedURLException {
        getDriverWait(sec).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private void waitPresenceOfElementLocated(MobileElement element, int sec) throws MalformedURLException {
        By by = getByAndLocator(element);
        waitPresenceOfElementLocated(by, sec);
    }

    private void waitVisibilityOfElement(MobileElement element, int sec) throws MalformedURLException {
        getDriverWait(sec).until(ExpectedConditions.visibilityOf(element));
    }

    private void waitVisibilityOfElement(By by, int sec) throws MalformedURLException {
        getDriverWait(sec).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private void waitElementToBeClickable(MobileElement element, int sec) throws MalformedURLException {
        getDriverWait(sec).until(ExpectedConditions.elementToBeClickable(element));
    }

    private void waitElementToBeClickable(By by, int sec) throws MalformedURLException {
        getDriverWait(sec).until(ExpectedConditions.elementToBeClickable(by));
    }

    private void waitPresenceOfElementLocated(By by) throws MalformedURLException {
        getDriverWait().until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private void waitPresenceOfElementLocated(MobileElement element) throws MalformedURLException {
        By by = getByAndLocator(element);
        waitPresenceOfElementLocated(by);
    }

    private void waitVisibilityOfElement(MobileElement element) throws MalformedURLException {
        getDriverWait().until(ExpectedConditions.visibilityOf(element));
    }

    private void waitVisibilityOfElement(By by) throws MalformedURLException {
        getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private void waitElementToBeClickable(MobileElement element) throws MalformedURLException {
        getDriverWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    private void waitElementToBeClickable(By by) throws MalformedURLException {
        getDriverWait().until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void takeScreenshot() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ScenarioRepository.screenshot();
    }
}
