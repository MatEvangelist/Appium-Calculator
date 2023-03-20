package com.mathews.test;

import com.mathews.core.AppiumServerConfig;
import com.mathews.report.Report;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com/mathews/steps", "com/mathews/core"},
        tags = "@CT01",
        plugin = {"pretty", "json:target/report/cucumber.json"},
        monochrome = true)
public class Runner {

    @AfterClass
    public static void gerarRelatorio() {
        Report.gerarRelatorioReportBuilder();
        AppiumServerConfig.stopServer();
    }

}
