package com.example.techswap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.techswap.fragments.LoginFragment;
import com.example.techswap.fragments.UserLoggedInFragment;
import com.example.techswap.user.CurrentUser;

public class UserActivity extends AppCompatActivity {

    private ImageView userInBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userInBack = findViewById(R.id.userInBack);

        userInBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to switch to another activity
                Intent intent = new Intent(UserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment;
        if (CurrentUser.getCurrentUser() == null) {
            fragment = new LoginFragment();
        } else {
            fragment = new UserLoggedInFragment();
        }

        fragmentTransaction.replace(R.id.mainFragmentContainer, fragment);
        fragmentTransaction.commit();
    }
}
