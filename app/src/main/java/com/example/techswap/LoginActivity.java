package com.example.techswap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText usernameInput = (EditText) findViewById(R.id.username_input_view);
        EditText passwordInput = (EditText) findViewById(R.id.password_input_view);
        Button loginButton = (Button) findViewById(R.id.login_button);
    }
}