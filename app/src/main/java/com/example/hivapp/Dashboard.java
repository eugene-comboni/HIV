package com.example.hivapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hivapp.Session.Prevalent;
import com.example.hivapp.ui.book.BookFragment;
import com.example.hivapp.ui.home.HomeFragment;
import com.example.hivapp.ui.store.StoreFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Dashboard extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        if (Prevalent.currentOnlineUser == null) {
            startActivity(new Intent(this, Login.class));
        }
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Meet new people", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                startActivity(new Intent(getApplicationContext(), mainActivity_chat.class));

            }
        });
        if (savedInstanceState== null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.nav_home);}

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,
                            new HomeFragment()).commit();
                    return true;
                case R.id.nav_book:
                    getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,
                            new BookFragment()).commit();
                    //MenuItem.se
                    return true;
                case R.id.nav_store:

                    getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,
                            new StoreFragment()).commit();
                    return true;

            }



            return false;
        }

    };
}
