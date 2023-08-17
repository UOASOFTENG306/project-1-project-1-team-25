package com.example.techswap;

import androidx.appcompat.app.AppCompatActivity;
//import com.google.firebase.FirebaseApp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private Button registerButton;
    private Button loginButton;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameInput = findViewById(R.id.username_input_view);
        passwordInput = findViewById(R.id.password_input_view);
        loginButton = findViewById(R.id.login_view_button);
        registerButton = findViewById(R.id.register_view_button);
        confirmButton = findViewById(R.id.confirm_button);
    }

//    public void loginUser(View view) {
//        EditText usernameInput = (EditText) findViewById(R.id.username_input_view);
//        String username = String.valueOf(usernameInput.getText());
//        EditText passwordInput = (EditText) findViewById(R.id.password_input_view);
//        String password = String.valueOf(passwordInput.getText());
//
//        // for when the database is created and can be accessed
//        User user = dbHandler.getUserByUsername(username);
//        boolean loggedIn = user.login(password);
//    }

//    switching between the register page and the login page
    public void onViewRegister(View view) {
        registerButton.setBackgroundResource(R.drawable.active_button_style);
        loginButton.setBackgroundResource(R.drawable.inactive_button_style);
        confirmButton.setText("Create Account");
    }

    public void onViewLogin(View view) {
        registerButton.setBackgroundResource(R.drawable.inactive_button_style);
        loginButton.setBackgroundResource(R.drawable.active_button_style);
        confirmButton.setText(R.string.login);
    }

}