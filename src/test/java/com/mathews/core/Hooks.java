package com.mathews.core;

import com.mathews.report.screenshot.ScenarioRepository;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void before(Scenario scenario) {
        ScenarioRepository.add(scenario);
        AppiumServerConfig.startServer();
    }

    @After
    public void after() {
        MobileDriverManager.killDriver();
        ScenarioRepository.remove();
    }
}
