package com.example.hivapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    private EditText EditTextFullName, EditTextEmail, EditTextPhone, EditTextPassword, EditTextConfirmPassword;
    private ProgressBar progressBar;
    private Button BtnRegisterUser;
    private TextView txtBackToLogin;

    private FirebaseAuth mAuth;
    DatabaseReference reference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        BtnRegisterUser = findViewById(R.id.btnRegister);

        txtBackToLogin = findViewById(R.id.txtBack_to_Login);

        EditTextFullName = findViewById(R.id.edtName);
        EditTextEmail = findViewById(R.id.edtEmail);
        EditTextPhone = findViewById(R.id.edtPhoneRegister);
        EditTextPassword = findViewById(R.id.edtPasswordReg);
        EditTextConfirmPassword = findViewById(R.id.edtConfirmPassword);

        progressBar= findViewById(R.id.progressBar);

        txtBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        BtnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_username = EditTextFullName.getText().toString();
                String txt_email = EditTextEmail.getText().toString();
                String phone = EditTextPhone.getText().toString();
                String txt_password = EditTextPassword.getText().toString();
                String txt_confirm_password = EditTextConfirmPassword.getText().toString();

                if (TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_LONG).show();
                }else if (txt_password.length() < 6){
                    Toast.makeText(getApplicationContext(),"password must be atleast 6 characters", Toast.LENGTH_LONG).show();
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                    register(txt_username, txt_email, phone,txt_password, txt_confirm_password);
                }
            }
        });

    }

    private void register(String EditTextFullName, String EditTextEmail, String EditTextPhone, String EditTextPassword, String EditTextConfirmPassword){



        mAuth.createUserWithEmailAndPassword(EditTextEmail, EditTextPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            progressBar.getProgress();
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("username", EditTextFullName);
                      /*      hashMap.put("phone", EditTextPhone);
                            hashMap.put("confirm", EditTextConfirmPassword);*/
                            hashMap.put("imageURL", "default");

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(getApplicationContext(), "You cant register with this email and password", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}



/*
package com.example.hivapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private EditText EditTextFullName, EditTextEmail, EditTextPhone, EditTextPassword, EditTextConfirmPassword;
    private ProgressBar progressBar;
    private Button BtnRegisterUser;
    private TextView txtBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        BtnRegisterUser = findViewById(R.id.btnRegister);
        BtnRegisterUser.setOnClickListener(this);

        txtBackToLogin = findViewById(R.id.txtBack_to_Login);
        txtBackToLogin.setOnClickListener(this);

        EditTextFullName = findViewById(R.id.edtName);
        EditTextEmail = findViewById(R.id.edtEmail);
        EditTextPhone = findViewById(R.id.edtPhoneRegister);
        EditTextPassword = findViewById(R.id.edtPasswordReg);
        EditTextConfirmPassword = findViewById(R.id.edtConfirmPassword);

        progressBar= findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                registerUser();
                break;
            case R.id.txtBack_to_Login:
                startActivity(new Intent(this, Login.class));
                break;
        }
    }

    private void registerUser() {
        String email = EditTextEmail.getText().toString().trim();
        String fullName = EditTextFullName.getText().toString().trim();
        String Phone = EditTextPhone.getText().toString().trim();
        String Password = EditTextPassword.getText().toString().trim();
        String ConfirmPassword = EditTextConfirmPassword.getText().toString().trim();

        if (fullName.isEmpty()){
            EditTextFullName.setError("Full Name is Required");
            EditTextFullName.requestFocus();
            return;
        }

        if (email.isEmpty()){
            EditTextEmail.setError("Email Address is required");
            EditTextEmail.requestFocus();
            return;
        }

        if (Phone.isEmpty()){
            EditTextPhone.setError("Phone Number is Required");
            EditTextPhone.requestFocus();
            return;
        }

        if (Password.isEmpty()){
            EditTextPassword.setError("Password is Required");
            EditTextPassword.requestFocus();
            return;
        }

        if (ConfirmPassword.isEmpty()){
            EditTextConfirmPassword.setError("Confirm Password");
            EditTextConfirmPassword.requestFocus();
            return;
        }

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            EditTextEmail.setError("Provide Valid Email");
            EditTextEmail.requestFocus();
            return;
        }

        if (Password.length() < 6){
            EditTextPassword.setError("Min Password length should be 6 characters");
            EditTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            User user = new User(email,fullName,Phone);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(Register.this, "User has been Registered Successfully", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                    else{
                                        Toast.makeText(Register.this, "Failed to Register! Try Again!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                        }else {
                            Toast.makeText(Register.this, "Failed to Register the User!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}
*/
