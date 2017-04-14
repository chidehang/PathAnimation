package com.demo.cdh.pathanimation;

import android.view.View;

/**
 * Created by hang on 2017/3/20.
 */

public class ViewWrapper<T extends View> {

    private T view;

    public ViewWrapper(T v) {
        this.view = v;
    }

    public void setScale(float scale) {
        view.setScaleX(scale);
        view.setScaleY(scale);
    }
}
