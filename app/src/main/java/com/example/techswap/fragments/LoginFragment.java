package com.example.techswap.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.techswap.R;

public class LoginFragment extends Fragment {

    private EditText usernameInput;
    private EditText passwordInput;
    private Button registerButton;
    private Button loginButton;
    private Button confirmButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        usernameInput = view.findViewById(R.id.username_input_view);
        passwordInput = view.findViewById(R.id.password_input_view);
        loginButton = view.findViewById(R.id.login_view_button);
        registerButton = view.findViewById(R.id.register_view_button);
        confirmButton = view.findViewById(R.id.confirm_button);

        // Set click listeners for buttons
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewRegister();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewLogin();
            }
        });

        return view;
    }

    // Handle switching between the register page and the login page
    private void onViewRegister() {
        registerButton.setBackgroundResource(R.drawable.active_button_style);
        loginButton.setBackgroundResource(R.drawable.inactive_button_style);
        confirmButton.setText("Create Account");
    }

    private void onViewLogin() {
        registerButton.setBackgroundResource(R.drawable.inactive_button_style);
        loginButton.setBackgroundResource(R.drawable.active_button_style);
        confirmButton.setText("Sign In");
    }
}