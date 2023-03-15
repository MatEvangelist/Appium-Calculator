package pages;

import core.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import static utils.MobileDriverManager.getMobDriver;

public class CalculatorPage extends BasePage {

    @AndroidFindBy(id = "android_field_first_number")
    @iOSXCUITFindBy(id = "apple_first_input")
    public MobileElement first_number;

    @AndroidFindBy(id = "android_field_second_number")
    @iOSXCUITFindBy(id = "apple_second_input")
    public MobileElement second_number;

    @AndroidFindBy(id = "android_button_sum")
    @iOSXCUITFindBy(id = "apple-sum-button")
    public MobileElement button_sum;

    @AndroidFindBy(id = "android_button_sub")
    @iOSXCUITFindBy(id = "apple-subtract-button")
    public MobileElement button_sub;

    @AndroidFindBy(id = "android_button_mult")
    @iOSXCUITFindBy(id = "apple-multiply-button")
    public MobileElement button_mult;

    @AndroidFindBy(id = "android_button_div")
    @iOSXCUITFindBy(id = "apple-divide-button")
    public MobileElement button_div;

    @AndroidFindBy(id = "android_result_text")
    @iOSXCUITFindBy(id = "apple_result_text")
    public MobileElement result_text;

    public void typeFirstNumber(String num) {
        first_number.clear();
        first_number.sendKeys(num);
//        super.label("Digitei o primeiro valor");
    }

    public void typeSecondNumber(String num2) {
        second_number.clear();
        second_number.sendKeys(num2);
//        super.label("Digitei o segundo valor");
    }

    public String getResult() {
        return result_text.getText().toString().trim();
//        getMobDriver().label("Cliquei no botão soma");
    }


    public void closeKeyboard() {
        super.hideKeyBoard();
    }

    public void sum() {
        button_sum.click();
    }

    public void getWait(int num) {
        super.getWait(num);
    }
}
