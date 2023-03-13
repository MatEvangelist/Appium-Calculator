package test;


import org.junit.After;
import org.junit.Before;
import pages.CalculatorPage;
import steps.BaseSteps;
import utils.MobileDriverManager;
import com.microsoft.appcenter.appium.Factory;
import org.junit.rules.TestWatcher;
import org.junit.Rule;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class Test extends BaseSteps {

    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    @Before
    public void driverStart() {
        try {
            MobileDriverManager.createDriver();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void driverRefresh() {
        MobileDriverManager.relaunchApp();
    }

    @org.junit.Test
    public void calc(){
        CalculatorPage calc = new CalculatorPage();
        calc.typeFirstNumber("5");
        calc.typeSecondNumber("5");
        calc.closeKeyboard();
        calc.sum();
        assertTrue(calc.getResult().equals("10"));
    }

}
