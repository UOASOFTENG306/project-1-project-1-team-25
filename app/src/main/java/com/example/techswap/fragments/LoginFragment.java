package com.example.techswap.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.techswap.database.DatabaseSetter;
import com.example.techswap.user.User;

import com.example.techswap.MainActivity;
import com.example.techswap.R;

public class LoginFragment extends Fragment {

    private EditText usernameInput;
    private EditText passwordInput;
    private TextView successMessageTextView;
    private Button registerButton;
    private Button loginButton;
    private Button confirmButton;
    private DatabaseSetter dbSetter = new DatabaseSetter();
    private boolean isLogin = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        usernameInput = view.findViewById(R.id.username_input_view);
        passwordInput = view.findViewById(R.id.password_input_view);
        successMessageTextView = view.findViewById(R.id.successMessage);
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

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewConfirm();
            }
        });

        return view;
    }

    // Handle switching between the register page and the login page
    private void onViewRegister() {
        registerButton.setBackgroundResource(R.drawable.active_button_style);
        loginButton.setBackgroundResource(R.drawable.inactive_button_style);
        confirmButton.setText("Create Account");
        isLogin = false;
    }

    private void onViewLogin() {
        registerButton.setBackgroundResource(R.drawable.inactive_button_style);
        loginButton.setBackgroundResource(R.drawable.active_button_style);
        confirmButton.setText("Sign In");
        isLogin = true;
    }

    private void onViewConfirm() {
        User currentUser = new User(usernameInput.getText().toString(),passwordInput.getText().toString());

        if (isLogin) {
            // TODO: check if login info is correct
            User.setCurrentUser(currentUser);
        } else {
            dbSetter.addUser(currentUser, true);
            User.setCurrentUser(currentUser);
        }

        Intent intent = new Intent(requireContext(), MainActivity.class);
        startActivity(intent);
    }
}