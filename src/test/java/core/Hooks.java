package core;

import com.microsoft.appcenter.appium.Factory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.AfterAll;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import utils.AppiumServerConfig;
import utils.MobileDriverManager;

public class Hooks {

    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    @Before
    public void before() {
        AppiumServerConfig.startServer();
    }

    @After
    public void after() {
        MobileDriverManager.killDriver();
    }

    @AfterAll
    public static void afterAll() {
        AppiumServerConfig.stopServer();
    }

}
