package core;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static utils.MobileDriverManager.getMobDriver;

public class BasePage {

    public BasePage() {
        PageFactory.initElements(new AppiumFieldDecorator(getMobDriver(), Duration.ofSeconds(10)), this);
    }

//    protected void label(String lbl) {
//        getMobDriver().label(lbl);
//    }

    protected void getWait(int num) {
        try {
            Thread.sleep(num * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        label("Esperou 1 segundo");
    }

    protected void hideKeyBoard() {
        getMobDriver().hideKeyboard();
    }
}
