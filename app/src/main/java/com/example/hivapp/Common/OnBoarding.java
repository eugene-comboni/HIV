package com.example.hivapp.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hivapp.Dashboard;
import com.example.hivapp.Login;
import com.example.hivapp.R;
import com.example.hivapp.SliderAdapter;

public class OnBoarding extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    SharedPreferences OnBoardingScreen;
    TextView[] dots;
    Button getStarted;
    Animation animation;
    int currentPos;
    String prev_Seen = "yes";


    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if (!sharedpreferences.getBoolean(prev_Seen, false)) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(prev_Seen, Boolean.TRUE);
            editor.apply();
        } else {
            moveToSecondary();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);

      /*  SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // Check if we need to display our OnboardingSupportFragment
        if (!sharedPreferences.getBoolean("onboarding_complete", false)) {
            // The user hasn't seen the OnboardingSupportFragment yet, so show it
            startActivity(new Intent(this, OnBoarding.class));

            finish();
            return;
        }*/



        getStarted = findViewById(R.id.get_started_btn);
        getStarted.setOnClickListener(this);

        //Hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);

        //Call com.example.hivapp.com.example.hivapp.Adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    public void skip(View view){
        startActivity(new Intent(getApplicationContext(), Dashboard.class));
        finish();
    }

    public void next(View view){
        viewPager.setCurrentItem(currentPos +1);
    }

    private void addDots(int position) {
        dots = new TextView[3];

        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.color_black));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;

            if (position == 0) {
                getStarted.setVisibility(View.INVISIBLE);
            } else if (position == 1) {
                getStarted.setVisibility(View.INVISIBLE);
            } else {
                animation = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.bottom_anim);
                getStarted.setAnimation(animation);
                getStarted.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_started_btn:
                OnBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
                SharedPreferences.Editor editor =  OnBoardingScreen.edit();
                editor.putBoolean( "firstTime", true);
                editor.commit();
                startActivity(new Intent(getApplicationContext(), Login.class));
                break;
        }
    }

    public void moveToSecondary(){
        // use an intent to travel from one activity to another.
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}