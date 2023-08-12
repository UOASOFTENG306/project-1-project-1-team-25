package com.example.techswap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText usernameInput = (EditText) findViewById(R.id.username_input_view);
        EditText passwordInput = (EditText) findViewById(R.id.password_input_view);
        Button loginButton = (Button) findViewById(R.id.login_button);
    }

    public void loginUser(View view) {
        EditText usernameInput = (EditText) findViewById(R.id.username_input_view);
        String username = String.valueOf(usernameInput.getText());
        EditText passwordInput = (EditText) findViewById(R.id.password_input_view);
        String password = String.valueOf(passwordInput.getText());

        // for when the database is created and can be accessed
//        User user = dbHandler.getUserByUsername(username);
//        boolean loggedIn = user.login(password);

    }
}