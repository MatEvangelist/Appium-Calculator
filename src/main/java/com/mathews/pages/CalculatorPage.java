package com.mathews.pages;

import com.mathews.core.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import java.net.MalformedURLException;

public class CalculatorPage extends BasePage {

    //    @AndroidFindBy(id = "android_field_first_number")
    @AndroidFindBy(xpath = "//android.widget.EditText[@text='FIRST NUMBER']")
    @iOSXCUITFindBy(id = "apple_first_input")
    public MobileElement cmpFirstNumber;

    @AndroidFindBy(id = "android_field_second_number")
    @iOSXCUITFindBy(id = "apple_second_input")
    public MobileElement cmpSecondNumber;

    @AndroidFindBy(id = "android_button_sum")
    @iOSXCUITFindBy(id = "apple-sum-button")
    public MobileElement btnSum;

    @AndroidFindBy(id = "android_button_sub")
    @iOSXCUITFindBy(id = "apple-subtract-button")
    public MobileElement btnSub;

    @AndroidFindBy(id = "android_button_mult")
    @iOSXCUITFindBy(id = "apple-multiply-button")
    public MobileElement btnMult;

    @AndroidFindBy(id = "android_button_div")
    @iOSXCUITFindBy(id = "apple-divide-button")
    public MobileElement btnDiv;

    @AndroidFindBy(id = "android_result_text")
    @iOSXCUITFindBy(id = "apple_result_text")
    public MobileElement txtResult;

    @AndroidFindBy(xpath = "//*")
    public MobileElement elementos;

    public void typeFirstNumber(String num) throws MalformedURLException {
        clickElement(cmpFirstNumber);
        clearText(cmpFirstNumber);
        cmpFirstNumber.sendKeys(num);
//        takeScreenshot();

    }

    public void typeSecondNumber(String num2) throws MalformedURLException {
        clearText(cmpSecondNumber);
        cmpSecondNumber.sendKeys(num2);
//        takeScreenshot();
    }

    public String getResult() throws MalformedURLException {
//        takeScreenshot();
        return super.getText(txtResult);
    }


    public void closeKeyboard() {
        super.hideKeyBoard();
    }

    public void sum() throws MalformedURLException {
        super.clickElement(btnSum);
    }

    public void getWait(int num) {
        super.getWait(num);
    }
}
