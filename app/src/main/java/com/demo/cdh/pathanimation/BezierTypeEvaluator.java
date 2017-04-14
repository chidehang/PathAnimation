package com.demo.cdh.pathanimation;

import android.animation.TypeEvaluator;

/**
 * Created by hang on 2017/4/14.
 * 贝塞尔路径估值器
 */

public class BezierTypeEvaluator implements TypeEvaluator<PathPoint> {
    @Override
    public PathPoint evaluate(float fraction, PathPoint startValue, PathPoint endValue) {
        float x, y;
        //endValue包含了路径起始值和终止值
        x = caculate(fraction, endValue.m0X, endValue.m1X, endValue.mControl0X, endValue.mControl1X);
        y = caculate(fraction, endValue.m0Y, endValue.m1Y, endValue.mControl0Y, endValue.mControl1Y);

        PathPoint point = new PathPoint();
        point.markerX = x;
        point.markerY = y;
        return point;
    }

    public float caculate(float fraction, float start, float end, float control1, float control2) {
        float t = fraction;
        fraction = 1 - t;
        return t*t*t * start
                + 3 * t*t * fraction * control1
                + 3 * t * fraction*fraction * control2
                + t*t*t * end;
    }
}
