package com.example.hivapp.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.hivapp.Dashboard;
import com.example.hivapp.Login;
import com.example.hivapp.R;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREENS = 5000;

    SharedPreferences OnBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(() -> {

            OnBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
            boolean isFirstTime = OnBoardingScreen.getBoolean("firstTime", true);

            if (isFirstTime ){

                SharedPreferences.Editor editor =  OnBoardingScreen.edit();
                editor.putBoolean( "firstTime", false);
                    editor.commit();

                startActivity(new Intent(this, OnBoarding.class));
                finish();
            }

            else {
                startActivity(new Intent(this, Login.class));
                finish();
            }

            Intent intent = new Intent(getApplicationContext(), OnBoarding.class);
            startActivity(intent);
            finish();
        }, SPLASH_SCREENS);
    }
}