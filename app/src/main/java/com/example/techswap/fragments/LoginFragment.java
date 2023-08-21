package com.example.techswap.fragments;

import static android.content.ContentValues.TAG;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class LoginFragment extends Fragment {

    private EditText usernameInput;
    private EditText passwordInput;
    private TextView displayMessageTextView;
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
        displayMessageTextView = view.findViewById(R.id.successMessage);
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
        registerButton.setTextColor(getResources().getColor(R.color.white));
        loginButton.setTextColor(getResources().getColor(R.color.gray));
        displayMessageTextView.setVisibility(View.INVISIBLE);
        usernameInput.setText("");
        passwordInput.setText("");
        confirmButton.setText("Create Account");
        isLogin = false;
    }

    private void onViewLogin() {
        registerButton.setBackgroundResource(R.drawable.inactive_button_style);
        loginButton.setBackgroundResource(R.drawable.active_button_style);
        registerButton.setTextColor(getResources().getColor(R.color.gray));
        loginButton.setTextColor(getResources().getColor(R.color.white));
        displayMessageTextView.setVisibility(View.INVISIBLE);
        usernameInput.setText("");
        passwordInput.setText("");
        confirmButton.setText("Sign In");
        isLogin = true;
    }

    private void onViewConfirm() {
        User currentUser = new User(usernameInput.getText().toString(),passwordInput.getText().toString());
        fetchUser(currentUser, isLogin);
    }

    private void fetchUser(User user, boolean isLoggingIn) {
        FirebaseFirestore.getInstance().collection("users")
                .document(user.getUsername()).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Map<String, Object> docData = task.getResult().getData();
                        Intent intent = new Intent(requireContext(), MainActivity.class);
//
                        if (isLoggingIn && task.getResult().exists() && docData.get("password").toString().equals(user.getPassword())) {
                            User.setCurrentUser(user);
                            startActivity(intent);
                        } else if (!isLoggingIn && !task.getResult().exists())   {
                            dbSetter.addUser(user, true);
                            User.setCurrentUser(user);
                            startActivity(intent);
                        } else if (isLoggingIn){
                            displayMessageTextView.setText("Invalid password or username,\n please try again.");
                            displayMessageTextView.setVisibility(VISIBLE);
                        } else {
                            displayMessageTextView.setText("Username already in use,\n please try a different one.");
                            displayMessageTextView.setVisibility(VISIBLE);
                        }
//                        Intent intent = new Intent(requireContext(), MainActivity.class);
//                        startActivity(intent);

                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }
}