package com.example.techswap;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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




//package com.example.techswap;
//
//import androidx.appcompat.app.AppCompatActivity;
////import com.google.firebase.FirebaseApp;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//public class LoginActivity extends AppCompatActivity {
//
//    private EditText usernameInput;
//    private EditText passwordInput;
//    private Button registerButton;
//    private Button loginButton;
//    private Button confirmButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        usernameInput = findViewById(R.id.username_input_view);
//        passwordInput = findViewById(R.id.password_input_view);
//        loginButton = findViewById(R.id.login_view_button);
//        registerButton = findViewById(R.id.register_view_button);
//        confirmButton = findViewById(R.id.confirm_button);
//    }
//
////    public void loginUser(View view) {
////        EditText usernameInput = (EditText) findViewById(R.id.username_input_view);
////        String username = String.valueOf(usernameInput.getText());
////        EditText passwordInput = (EditText) findViewById(R.id.password_input_view);
////        String password = String.valueOf(passwordInput.getText());
////
////        // for when the database is created and can be accessed
////        User user = dbHandler.getUserByUsername(username);
////        boolean loggedIn = user.login(password);
////    }
//
////    switching between the register page and the login page
//    public void onViewRegister(View view) {
//        registerButton.setBackgroundResource(R.drawable.active_button_style);
//        loginButton.setBackgroundResource(R.drawable.inactive_button_style);
//        confirmButton.setText("Create Account");
//    }
//
//    public void onViewLogin(View view) {
//        registerButton.setBackgroundResource(R.drawable.inactive_button_style);
//        loginButton.setBackgroundResource(R.drawable.active_button_style);
//        confirmButton.setText(R.string.login);
//    }
//
//}