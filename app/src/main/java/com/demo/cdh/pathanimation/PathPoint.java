package com.demo.cdh.pathanimation;

/**
 * Created by hang on 2017/4/14.
 * 三阶贝塞尔曲线
 */

public class PathPoint {
    public float m0X;  //端点 起点
    public float m0Y;
    public float m1X;  //终点
    public float m1Y;
    public float mControl0X; //锚点
    public float mControl0Y;
    public float mControl1X;
    public float mControl1Y;

    public float markerX;   //路径上当前标记点
    public float markerY;

    public PathPoint() {
        this.m0X = 0;
        this.m0Y = 0;
        this.m1X = 0;
        this.m1Y = 0;
        this.mControl0X = 0;
        this.mControl0Y = 0;
        this.mControl1X = 0;
        this.mControl1Y = 0;
        this.markerX = 0;
        this.markerY = 0;
    }

    public PathPoint(float m0X, float m0Y, float m1X, float m1Y, float mControl0X, float mControl0Y, float mControl1X, float mControl1Y) {
        this.m0X = m0X;
        this.m0Y = m0Y;
        this.m1X = m1X;
        this.m1Y = m1Y;
        this.mControl0X = mControl0X;
        this.mControl0Y = mControl0Y;
        this.mControl1X = mControl1X;
        this.mControl1Y = mControl1Y;
        this.markerX = m0X;
        this.markerY = m0Y;
    }
}
