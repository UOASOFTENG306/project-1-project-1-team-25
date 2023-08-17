package com.example.techswap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class UserNotInActivity extends AppCompatActivity {

    private ImageView userInBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_in);

        userInBack = findViewById(R.id.userInBack);

        userInBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to switch to another activity
                Intent intent = new Intent(UserNotInActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
