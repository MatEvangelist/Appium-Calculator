package com.mathews.report.screenshot.impl;

import com.google.common.base.Function;
import com.mathews.report.screenshot.exceptions.ScreenshotException;

public class RunScreenshot {

    public <V, T> V take(Function<? super T, V> isTrue) {
        try {
            return isTrue.apply(null);
        } catch (Exception e) {
            throw new ScreenshotException(e);
        }
    }
}
