package test;

import com.microsoft.appcenter.appium.Factory;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.JUnitCore;

public class Test {

    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    public static void main(String[] args) {
        JUnitCore.main("test.Runner");
    }
}
