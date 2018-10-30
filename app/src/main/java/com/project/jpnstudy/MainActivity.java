package com.project.jpnstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Animation translateLeftAnim;
    Animation translateRightAnim;
    LinearLayout slidingPanel;
    Button button;

    boolean isPageOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");

        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        translateLeftAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(isPageOpen) {
                    slidingPanel.setVisibility((View.INVISIBLE));
                    button.setText("열기");
                    isPageOpen=false;
                } else {
                    button.setText("닫기");
                    isPageOpen=true;
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        translateRightAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(isPageOpen) {
                    slidingPanel.setVisibility(View.INVISIBLE);
                    button.setText("열기");
                    isPageOpen=false;
                }else {
                    button.setText("닫기");
                    isPageOpen=true;
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        slidingPanel=(LinearLayout)findViewById(R.id.slidingPanel);
        button = (Button) findViewById(R.id.right_slide_button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isPageOpen) {
                    slidingPanel.startAnimation(translateRightAnim);
                    slidingPanel.setVisibility(View.GONE);
                } else {
                    slidingPanel.startAnimation(translateLeftAnim);
                    slidingPanel.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
