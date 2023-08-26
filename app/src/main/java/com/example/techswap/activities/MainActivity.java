package com.example.techswap.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.techswap.R;
import com.example.techswap.fragments.CartFragment;
import com.example.techswap.fragments.ListFragment;
import com.example.techswap.fragments.MainFragment;
import com.example.techswap.user.User;

public class MainActivity extends AppCompatActivity {

    private TextView logoText;
    private EditText searchBar;
    private Animation fadeInAnimation;
    private Animation fadeOutAnimation;
    private boolean isSearchBarVisible = false;

    /**
     * Called when the activity is first created. This method initializes the activity's UI components,
     * sets up listeners, and loads the initial fragment.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     *                           this Bundle contains the data it most recently supplied in {@link #onSaveInstanceState}.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoText = findViewById(R.id.logoText);
        searchBar = findViewById(R.id.searchBar);

        // Conditional visibility of cart icon depending on user
        if (User.getCurrentUser() == null) {
            findViewById(R.id.cartIcon).setVisibility(View.GONE);
        }

        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        findViewById(R.id.userIcon).setOnClickListener(v -> {
            // Load the animation from the XML resource
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_press);
            // Start the animation on the ImageView
            v.startAnimation(animation);

            // Switch to user activity
            Intent intent = new Intent(MainActivity.this, UserActivity.class);
            startActivity(intent);
        });

        // initialize MainFragment
        MainFragment fragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFragmentContainer, fragment);

        // Clear the entire back stack
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        transaction.commit();

        // listener for search action
        searchBar.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (event == null || event.getAction() == KeyEvent.ACTION_DOWN) {
                    performSearch(searchBar.getText().toString());
                    return true;
                }
            }
            return false;
        });
    }

    /**
     * Overrides the touch event dispatcher to manage interactions with the current Activity's views,
     * particularly focusing on EditText elements and handling touch events that occur outside them.
     * This method is responsible for dismissing the keyboard or hiding specific UI components,
     * ensuring a user-friendly experience when interacting with input fields and related UI elements.
     *
     * @param event The MotionEvent representing the touch event being dispatched.
     * @return True if the event was processed and consumed, false otherwise.
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        // Action down motion actions
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    hideSearchBar();
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    /**
     * Hides the on-screen keyboard (soft input) associated with the provided view.
     * This method utilizes the InputMethodManager to programmatically hide the keyboard.
     * It should be called when you want to dismiss the keyboard after a user interaction
     * with an input field to provide a smoother user experience.
     *
     * @param view The view currently in focus that triggered the keyboard to appear.
     */
    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Displays the on-screen keyboard (soft input) for the provided view.
     * This method utilizes the InputMethodManager to programmatically show the keyboard.
     * It should be called when you want to display the keyboard automatically in response
     * to a user interaction with an input field, ensuring a smooth user experience.
     *
     * @param view The view that requires keyboard input focus and triggers its appearance.
     */
    private void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    /**
     * Initiates a search operation with the provided search query and updates the UI accordingly.
     * This method is responsible for conducting a search using the given query and updating the
     * displayed content based on the search results. It involves hiding the search bar, creating
     * a new list fragment populated with the search results, and managing the fragment transaction.
     *
     * @param searchQuery The search query entered by the user.
     */
    private void performSearch(String searchQuery) {
        ListFragment fragment = ListFragment.listSearch(searchQuery);
        hideSearchBar();

        // Start a fragment transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(R.id.mainFragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * Callback method triggered when the search icon is clicked to initiate a search.
     * This method handles the user interaction when the search icon is clicked. It checks
     * whether the search bar is currently visible, and if not, it triggers the display of
     * the search bar to initiate a search operation.
     *
     * @param view The view representing the search icon that was clicked.
     */
    public void onSearchIconClick(View view) {
        if (!isSearchBarVisible) {
            showSearchBar();
        }
    }

    /**
     * Displays the search bar and transitions UI elements accordingly.
     * This method is responsible for showing the search bar while managing UI transitions.
     * It makes the search bar visible, fades it in with an animation, hides the logo text,
     * and requests focus on the search bar for user interaction. Additionally, it ensures
     * that the keyboard is displayed to allow seamless search input.
     */
    private void showSearchBar() {
        searchBar.setVisibility(View.VISIBLE);
        searchBar.requestFocus();
        searchBar.startAnimation(fadeInAnimation);
        logoText.setVisibility(View.GONE);
        logoText.startAnimation(fadeOutAnimation);
        isSearchBarVisible = true;

        showKeyboard(searchBar);
    }

    /**
     * Hides the search bar and manages associated UI elements.
     * This method is responsible for hiding the search bar while handling UI transitions.
     * It starts a fade-out animation for the search bar, hides it from view, clears focus,
     * shows the logo text, and ensures that the keyboard is dismissed if it was visible.
     */
    private void hideSearchBar() {
        searchBar.startAnimation(fadeOutAnimation);
        searchBar.setVisibility(View.GONE);
        searchBar.clearFocus();
        logoText.setVisibility(View.VISIBLE);
        isSearchBarVisible = false;

        hideKeyboard(searchBar);
    }

    /**
     * Callback method triggered when the cart icon is clicked to manage cart-related actions.
     * This method handles the user interaction when the cart icon is clicked. It checks the current
     * fragment displayed and transitions to the CartFragment if the current fragment is not the cart
     * fragment. It also applies a button press animation to provide visual feedback.
     *
     * @param view The view representing the cart icon that was clicked.
     */
    public void onCartClick(View view) {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainFragmentContainer);

        // Load the animation from the XML resource
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_press);
        // Start the animation on the ImageView
        view.startAnimation(animation);

        if (!(currentFragment instanceof CartFragment)) {
            CartFragment fragment = new CartFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

            transaction.replace(R.id.mainFragmentContainer, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    /**
     * Callback method triggered when the logo is clicked to manage main screen navigation.
     * This method handles the user interaction when the logo is clicked. It checks the current
     * fragment displayed and transitions to the MainFragment if the current fragment is not the
     * main fragment. It also applies a button press animation to provide visual feedback.
     *
     * @param view The view representing the logo that was clicked.
     */
    public void onLogoClick(View view) {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainFragmentContainer);

        // Load the animation from the XML resource
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_press);
        // Start the animation on the ImageView
        view.startAnimation(animation);

        if (!(currentFragment instanceof MainFragment)) {
            MainFragment fragment = new MainFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);

            transaction.replace(R.id.mainFragmentContainer, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}