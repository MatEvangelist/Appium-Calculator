package test;

import com.microsoft.appcenter.appium.Factory;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/main/resources/features"},
        glue = {"steps", "core"},
        tags = "@mobile",
        plugin = {"pretty", "html:target/cucumber-html-report"},
        monochrome = true)

public class Runner {

    @Rule
    public TestWatcher watcher = Factory.createWatcher();

}
