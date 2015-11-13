package com.example.xiachen.myanimate;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import java.util.Date;

/**
 * Created by xiachen on 15/11/12.
 */
public class AnimatorSetActivity extends AppCompatActivity {
    private ImageView mBlueBall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animatorsetactivity);

        mBlueBall = (ImageView) findViewById(R.id.id_ball);

    }

    public void togetherRun(View view) {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBlueBall, "scaleX", 1.0f, 2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBlueBall, "scaleY", 1.0f, 2f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(1000);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.playTogether(anim1, anim2);
        animatorSet.start();
    }

    public void playWithAfter(View view) {

        float cx = mBlueBall.getX();

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBlueBall, "scaleX", 1.0f, 2f);
        anim1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                Log.w("never", "anim1 start");
            }
        });
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBlueBall, "scaleY", 1.0f, 2f);
        anim2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                Log.w("never", "anim2 start");
            }
        });
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(mBlueBall, "x", cx, 0f);
        anim3.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                Log.w("never", "anim3 start");
            }
        });
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(mBlueBall, "x", 2 * cx);
        anim4.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                Log.w("never", "anim4 start");
            }
        });
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim1).with(anim2).before(anim3).after(anim4);
//        animSet.play(anim2).before(anim3);
//        animSet.play(anim3).after(anim4);
        final long d1 = System.currentTimeMillis();
        animSet.setDuration(2000);

        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                long d2 = System.currentTimeMillis();
                Log.w("never", "动画事件" + (d2 - d1));
            }
        });
        animSet.start();
    }
}