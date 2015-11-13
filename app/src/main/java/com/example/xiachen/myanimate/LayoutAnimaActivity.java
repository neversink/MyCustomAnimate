package com.example.xiachen.myanimate;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;

/**
 * Created by xiachen on 15/11/12.
 */
public class LayoutAnimaActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private ViewGroup viewGroup;
    private GridLayout mGridLayout;
    private int mVal = 0;
    private LayoutTransition mTransition;
    private CheckBox mAppear, mChangeAppear, mDisAppear, mChangeDisAppear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layouttransition);
        viewGroup = (ViewGroup) findViewById(R.id.id_container);
        mAppear = (CheckBox) findViewById(R.id.id_appear);
        mChangeAppear = (CheckBox) findViewById(R.id.id_change_appear);
        mDisAppear = (CheckBox) findViewById(R.id.id_disappear);
        mChangeDisAppear = (CheckBox) findViewById(R.id.id_change_disappear);

        mAppear.setOnCheckedChangeListener(this);
        mChangeAppear.setOnCheckedChangeListener(this);
        mDisAppear.setOnCheckedChangeListener(this);
        mChangeDisAppear.setOnCheckedChangeListener(this);

        mGridLayout = new GridLayout(this);
        mGridLayout.setColumnCount(5);
        mTransition = new LayoutTransition();
        mGridLayout.setLayoutTransition(mTransition);
        viewGroup.addView(mGridLayout);
    }

    public void addBtn(View view) {
        final Button button = new Button(this);
        button.setText((++mVal) + "");
        mGridLayout.addView(button, Math.min(1, mGridLayout.getChildCount()));
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mGridLayout.removeView(button);
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mTransition = new LayoutTransition();
        mTransition.setAnimator(LayoutTransition.APPEARING, mAppear.isChecked() ? mTransition.getAnimator(LayoutTransition.APPEARING) : null);
        mTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, mChangeAppear.isChecked() ? mTransition.getAnimator(LayoutTransition.CHANGE_APPEARING) : null);
        mTransition.setAnimator(LayoutTransition.DISAPPEARING, mDisAppear.isChecked() ? mTransition.getAnimator(LayoutTransition.DISAPPEARING) : null);
        mTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, mChangeDisAppear.isChecked() ? mTransition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING) : null);
        mGridLayout.setLayoutTransition(mTransition);

    }

}
