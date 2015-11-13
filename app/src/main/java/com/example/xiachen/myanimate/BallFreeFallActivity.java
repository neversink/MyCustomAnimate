package com.example.xiachen.myanimate;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * Created by xiachen on 15/11/12.
 */
public class BallFreeFallActivity extends AppCompatActivity {

    private int mScreenHeight;
    private ImageView mBlueBall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freefall);

        mBlueBall = (ImageView) findViewById(R.id.id_ball);

        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;

    }

    public void verticalRun(View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, mScreenHeight - mBlueBall.getHeight());
        animator.setDuration(1000).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mBlueBall.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
    }

    public void paowuxian(View view) {
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(3000);
        animator.setObjectValues(new PointF(0, 0));
        animator.setInterpolator(new LinearInterpolator());
        animator.setEvaluator(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                PointF pointF = new PointF();
                pointF.x = 200 * fraction * 3;
                pointF.y = 200 * (fraction * 3) * (fraction * 3);
                return pointF;
            }
        });

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                mBlueBall.setX(pointF.x);
                mBlueBall.setY(pointF.y);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.w("never", "animate start");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.w("never", "animate end");

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.w("never", "animate cancel");

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.w("never", "animate repeat");

            }
        });
        animator.start();

    }
}
