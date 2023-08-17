package com.example.techswap;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class UserInActivity extends AppCompatActivity {

    private ImageView userInBack;
    private Button userViewCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_in);

        userInBack = findViewById(R.id.userInBack);

        userInBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to switch to another activity
                Intent intent = new Intent(UserInActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
