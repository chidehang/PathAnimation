package com.demo.cdh.pathanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static android.animation.ObjectAnimator.ofFloat;

public class MainActivity extends Activity implements View.OnClickListener {

    private RelativeLayout rlController;
    private ImageView ivCover;
    private ImageButton btnShare;
    private Button btnWechat, btnWxMoment;
    private LinearLayout llBtnPanel;

    private float startX;
    private float translateOffset;
    private float MIN_X_DISTANCE = 400;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rlController = (RelativeLayout) findViewById(R.id.rlController);
        ivCover = (ImageView) findViewById(R.id.image);
        btnShare = (ImageButton) findViewById(R.id.btnShare);
        btnWechat = (Button) findViewById(R.id.btnWechat);
        btnWxMoment = (Button) findViewById(R.id.btnWxMoment);
        llBtnPanel = (LinearLayout) findViewById(R.id.llBtnPanel);

        btnShare.setOnClickListener(this);
        btnWechat.setOnClickListener(this);
        btnWxMoment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnShare:
                startBezierMovement();
                break;

            case R.id.btnWechat:
                Toast.makeText(this, "分享到微信", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnWxMoment:
                Toast.makeText(this, "分享到朋友圈", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void startBezierMovement() {
        //设置移动路径，包括起始点、终止点 、锚点1、锚点2
        PathPoint path = new PathPoint(0, 0, -700, 0, -200, 200, -200, 200);
        //记录起点X
        startX = 0;
        //计算偏移量
        translateOffset = btnShare.getWidth() / 2;
        ObjectAnimator animator = ObjectAnimator.ofObject(this, "translate", new BezierTypeEvaluator(), path);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if(Math.abs(startX-btnShare.getX())>MIN_X_DISTANCE && !flag) {
                    flag = true;
                    btnShare.setImageDrawable(new BitmapDrawable());
                    //按钮面板下移
                    rlController.setY(rlController.getY() + translateOffset);

                    //移动到一定值，开始进行缩放动画
                    ObjectAnimator animator1 = ofFloat(new ViewWrapper(btnShare), "scale", 0f, 10f).setDuration(1000);
                    animator1.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            btnShare.setVisibility(View.GONE);
                            rlController.setBackgroundColor(Color.parseColor("#3a84ff"));
                            showControlBtn();
                        }
                    });
                    animator1.start();
                }
            }
        });
        animator.start();
    }

    /**
     * 显示按钮动画
     */
    public void showControlBtn() {
        int count = llBtnPanel.getChildCount();
        for (int i=0; i<count; i++) {
            View v = llBtnPanel.getChildAt(i);
            ObjectAnimator animator = ObjectAnimator.ofFloat(new ViewWrapper(v), "scale", 0f, 1f);
            animator.setStartDelay(i * 200);
            animator.start();
        }
    }

    public void setTranslate(PathPoint pathPoint) {
        btnShare.setTranslationX(pathPoint.markerX);
        if(flag)
            btnShare.setTranslationY(pathPoint.markerY + translateOffset);
        else
            btnShare.setTranslationY(pathPoint.markerY);
    }
}
