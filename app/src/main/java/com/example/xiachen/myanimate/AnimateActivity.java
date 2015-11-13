package com.example.xiachen.myanimate;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

/**
 * Created by xiachen on 15/11/12.
 */
public class AnimateActivity extends AppCompatActivity {
    private int mScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xml_for_anim);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mScreenHeight = dm.heightPixels;
    }

    public void rotateyAnimRun(final View view) {
        final ObjectAnimator anim = ObjectAnimator.ofFloat(view, "never", 1.0f, 0.0f).setDuration(500);
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (float) animation.getAnimatedValue();
                view.setAlpha(cVal);
                view.setScaleX(cVal);
                view.setScaleY(cVal);

            }
        });
    }

    public void propertyValuesHolder(View view) {

        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("rotationX", 0f, 360f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("rotationY", 0f, 360f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY).setDuration(500).start();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void viewAnim(final View view) {
        view.animate().alpha(0).y(mScreenHeight / 2).setDuration(1000).withStartAction(new Runnable() {
            @Override
            public void run() {
                Log.w("never", "action start");
            }
        }).withEndAction(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.setY(0);
                        view.setAlpha(1);
                    }
                });
            }
        }).start();
    }

}
