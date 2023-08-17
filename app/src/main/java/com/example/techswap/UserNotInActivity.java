package com.example.techswap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class UserNotInActivity extends AppCompatActivity {

    private ImageView userInBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_not_in);

        userInBack = findViewById(R.id.userInBack);

        userInBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to switch to another activity
                Intent intent = new Intent(UserNotInActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Load the LoginFragment
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            LoginFragment loginFragment = new LoginFragment();
            fragmentTransaction.replace(R.id.mainFragmentContainer, loginFragment); // Replace the container with the fragment
            fragmentTransaction.commit();
        }
    }
}
