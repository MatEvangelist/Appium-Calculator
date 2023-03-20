package com.mathews.report.screenshot;

import com.mathews.utils.FakeUtils;
import io.appium.java_client.MobileElement;
import io.cucumber.java.Scenario;

import java.util.HashMap;
import java.util.Map;

import static com.mathews.core.MobileDriverManager.getMobDriver;

public class ScenarioRepository {

    private static final Map<Long, Scenario> repository = new HashMap<Long, Scenario>();

    public static void add(Scenario scenario) {
        if (get() == null)
            repository.put(getId(), scenario);
    }

    public static void remove() {
        if (get() != null)
            repository.remove(getId());
    }

    private static Scenario get() {
        return repository.get(getId());
    }

    public static Long getId() {
        return Thread.currentThread().getId();
    }

    public static void screenshot() {
        try {
            byte[] print = Screenshot.take(getMobDriver());
            get().embed(print, "image/png", FakeUtils.generateHashValue());
        } catch (Exception ignored) {

        }
    }

    public static void screenshot(MobileElement... elements) {
        try {
            byte[] print = Screenshot.take(getMobDriver(), elements);
            get().embed(print, "image/png", FakeUtils.generateHashValue());
        } catch (Exception ignored) {

        }
    }

    public static void addText(String texto) {
        if (texto != null)
            get().log(texto);
    }

}
