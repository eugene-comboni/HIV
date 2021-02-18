package com.example.hivapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button Login, Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login = findViewById(R.id.btnSignIn);
        Login.setOnClickListener(this);

        Register = findViewById(R.id.btnSignUp);
        Register.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnSignIn:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.btnSignUp:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }
}