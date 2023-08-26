package com.example.techswap.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.techswap.R;
import com.example.techswap.fragments.LoginFragment;
import com.example.techswap.fragments.UserLoggedInFragment;
import com.example.techswap.user.User;

public class UserActivity extends AppCompatActivity {

    /**
     * Called when the activity is first created. This method initializes the UI components, sets up
     * listeners for user interactions such as navigation, and manages the display of appropriate
     * fragments based on the user's authentication status.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied in {@link #onSaveInstanceState}.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        findViewById(R.id.userInBack).setOnClickListener(v -> {
            // switch to main
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment;
        TextView usernameView = findViewById(R.id.username);
        // no user then sign in
        if (User.getCurrentUser() == null) {
            usernameView.setText(R.string.sign_in);
            fragment = new LoginFragment();
        } else {
            usernameView.setText(User.getCurrentUser().getUsername());
            fragment = new UserLoggedInFragment();
        }

        fragmentTransaction.replace(R.id.mainFragmentContainer, fragment);
        fragmentTransaction.commit();
    }
}
