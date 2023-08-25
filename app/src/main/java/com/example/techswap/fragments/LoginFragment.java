package com.example.techswap.fragments;

import static android.content.ContentValues.TAG;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.techswap.activities.MainActivity;
import com.example.techswap.R;
import com.example.techswap.database.DatabaseSetter;
import com.example.techswap.user.User;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;
import java.util.Objects;

public class LoginFragment extends Fragment {

    private EditText usernameInput;
    private EditText passwordInput;
    private TextView displayMessageTextView;
    private Button registerButton;
    private Button loginButton;
    private Button confirmButton;
    private final DatabaseSetter dbSetter = new DatabaseSetter();
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
        registerButton.setOnClickListener(v -> onViewRegister());

        loginButton.setOnClickListener(v -> onViewLogin());

        confirmButton.setOnClickListener(v -> onViewConfirm());

        return view;
    }

    // Handle switching between the register page and the login page
    private void onViewRegister() {
        registerButton.setBackgroundResource(R.drawable.active_button_style);
        loginButton.setBackgroundResource(R.drawable.inactive_button_style);
        registerButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
        loginButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray));
        displayMessageTextView.setVisibility(View.INVISIBLE);
        usernameInput.setText("");
        passwordInput.setText("");
        confirmButton.setText(R.string.create_account);
        isLogin = false;
    }

    private void onViewLogin() {
        registerButton.setBackgroundResource(R.drawable.inactive_button_style);
        loginButton.setBackgroundResource(R.drawable.active_button_style);
        registerButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray));
        loginButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
        displayMessageTextView.setVisibility(View.INVISIBLE);
        usernameInput.setText("");
        passwordInput.setText("");
        confirmButton.setText(R.string.sign_in);
        isLogin = true;
    }

    private void onViewConfirm() {
        if (usernameInput.getText().toString().equals("") || passwordInput.getText().toString().equals("")) {
            if (isLogin){ //login fail
                displayMessageTextView.setText("Invalid password or username,\n please try again.");
                displayMessageTextView.setVisibility(VISIBLE);
            } else { // register fail
                displayMessageTextView.setText("Username already in use,\n please try a different one.");
                displayMessageTextView.setVisibility(VISIBLE);
            }
        } else {
            User currentUser = new User(usernameInput.getText().toString(),passwordInput.getText().toString());
            fetchUser(currentUser, isLogin);
        }
    }

    private void fetchUser(User user, boolean isLoggingIn) {
        FirebaseFirestore.getInstance().collection("users")
                .document(user.getUsername()).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Map<String, Object> docData = task.getResult().getData();
                        Intent intent = new Intent(requireContext(), MainActivity.class);

                        // login success
                        if (isLoggingIn && task.getResult().exists() && Objects.requireNonNull(Objects.requireNonNull(docData).get("password")).toString().equals(user.getPassword())) {
                            User.setCurrentUser(user);
                            startActivity(intent);
                            // Inside your activity or fragment
                            Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_LONG).show();
                        } else if (!isLoggingIn && !task.getResult().exists())   { // register success
                            dbSetter.addUser(user, true);
                            User.setCurrentUser(user);
                            startActivity(intent);
                            Toast.makeText(requireContext(), "Registration Successful", Toast.LENGTH_LONG).show();
                        } else if (isLoggingIn){ //login fail
                            displayMessageTextView.setText("Invalid password or username,\n please try again.");
                            displayMessageTextView.setVisibility(VISIBLE);
                        } else { // register fail
                            displayMessageTextView.setText("Username already in use,\n please try a different one.");
                            displayMessageTextView.setVisibility(VISIBLE);
                        }

                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }
}