package com.example.techswap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class UserInActivity extends AppCompatActivity {

    private ImageView userInBack;
    private Button userSellItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_in);

        userInBack = findViewById(R.id.userInBack);
        userSellItem = findViewById(R.id.userSellItemButton);

        userInBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to switch to another activity
                Intent intent = new Intent(UserInActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        userSellItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to switch to another activity
                Intent intent = new Intent(UserInActivity.this, SellActivity.class);
                startActivity(intent);
            }
        });
    }
}
