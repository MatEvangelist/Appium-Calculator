package com.mathews.test;

import com.mathews.core.MobileDriverManager;
import com.mathews.pages.CalculatorPage;
import com.mathews.steps.BaseSteps;
import com.microsoft.appcenter.appium.Factory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;

import java.io.IOException;
import java.net.MalformedURLException;

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
    public void calc() throws MalformedURLException {
        CalculatorPage calc = new CalculatorPage();
        calc.typeFirstNumber("5");
        calc.typeSecondNumber("5");
        calc.closeKeyboard();
        calc.sum();
        Assert.assertTrue(calc.getResult().equals("10"));
    }

}
