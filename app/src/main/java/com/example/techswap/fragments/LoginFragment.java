package com.example.techswap.fragments;

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
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.techswap.R;
import com.example.techswap.activities.MainActivity;
import com.example.techswap.database.Database;
import com.example.techswap.databinding.FragmentLoginBinding;
import com.example.techswap.user.User;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;
import java.util.Objects;

import static android.content.ContentValues.TAG;
import static android.view.View.VISIBLE;

public class LoginFragment extends Fragment {

    private EditText usernameInput;
    private EditText passwordInput;
    private TextView displayMessageTextView;
    private Button registerButton;
    private Button loginButton;
    private Button confirmButton;
    private boolean isLogin = true;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentLoginBinding binding = FragmentLoginBinding.inflate(inflater, container, false);
        View viewRoot = binding.getRoot();

        usernameInput = binding.usernameInputView;
        passwordInput = binding.passwordInputView;
        displayMessageTextView = binding.successMessage;
        loginButton = binding.loginViewButton;
        registerButton = binding.registerViewButton;
        confirmButton = binding.confirmButton;

        // Set click listeners for buttons
        registerButton.setOnClickListener(v -> onViewRegister());

        loginButton.setOnClickListener(v -> onViewLogin());

        confirmButton.setOnClickListener(v -> onViewConfirm());

        return viewRoot;
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
            if (isLogin) { //login fail
                displayMessageTextView.setText("Invalid password or username,\n please try again.");
                displayMessageTextView.setVisibility(VISIBLE);
            } else { // register fail
                displayMessageTextView.setText("Username already in use,\n please try a different one.");
                displayMessageTextView.setVisibility(VISIBLE);
            }
        } else {
            fetchUser(usernameInput.getText().toString(), passwordInput.getText().toString(), isLogin);
        }
    }

    private void fetchUser(String username, String password, boolean isLoggingIn) {
        FirebaseFirestore.getInstance().collection("users")
                .document(username).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Map<String, Object> docData = task.getResult().getData();
                        Intent intent = new Intent(requireContext(), MainActivity.class);

                        // login success
                        if (isLoggingIn && task.getResult().exists() && Objects.requireNonNull(Objects.requireNonNull(docData).get("password")).toString().equals(password)) {
                            User.userLogin(username, password);
                            startActivity(intent);
                            Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_LONG).show();
                        } else if (!isLoggingIn && !task.getResult().exists()) { // register success
                            Database.addUser(username, password);
                            startActivity(intent);
                            Toast.makeText(requireContext(), "Registration Successful", Toast.LENGTH_LONG).show();
                        } else if (isLoggingIn) { //login fail
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