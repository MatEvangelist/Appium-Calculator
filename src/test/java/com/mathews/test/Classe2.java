package com.mathews.test;

import com.microsoft.appcenter.appium.Factory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.JUnitCore;

public class Classe2 {

    @Rule
    public TestWatcher watcher = Factory.createWatcher();


    public static void main(String[] args) {

    }

    @Test
    public void teste() {
        JUnitCore.main("com.mathews.test.Runner");
    }
}
