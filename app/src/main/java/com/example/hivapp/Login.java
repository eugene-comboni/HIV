package com.example.hivapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hivapp.Model.User;
import com.example.hivapp.Session.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Login";
    private EditText editTextEmail, editTextPassword;
    private ProgressBar progressBar;
    private Button btnLogin;
    private TextView txtBackToRegister;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        txtBackToRegister = findViewById(R.id.txt_back_to_Signup);
        txtBackToRegister.setOnClickListener(this);

        editTextEmail = findViewById(R.id.edtEmailAddress);
        editTextPassword = findViewById(R.id.edtPassword);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                LoginUser();
                break;
            case R.id.txt_back_to_Signup:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }

    private void LoginUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextPassword.setError("Passsword is required");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() <6){
            editTextPassword.setError("MinPassword required is 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    /*startActivity(new Intent(Login.this, Dashboard.class));*/
                    progressBar.getProgress();
                    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                    String uid= currentFirebaseUser.getUid();
                    final DatabaseReference RootRef;
                    RootRef = FirebaseDatabase.getInstance().getReference();
                    RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            User usersData = snapshot.child("Users").child(uid).getValue(User.class);
                            Prevalent.currentOnlineUser=usersData;
                            startActivity(new Intent(getApplicationContext(),Dashboard.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.d(TAG, "onCancelled: "
                            +error.getMessage().toString());
                        }
                    });



                }else {
                    Toast.makeText(Login.this, "Failed to Login! Please check your credentials", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}