package com.example.techswap;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.MotionEvent;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    private AppBarConfiguration appBarConfiguration;
//    private ActivityMainBinding binding;

    private TextView logoText;
    private ImageView userIcon;
    private ImageView searchIcon;
    private EditText searchBar;
    private Animation fadeInAnimation;
    private Animation fadeOutAnimation;
    private boolean isSearchBarVisible = false;
    private FragmentContainerView fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoText = findViewById(R.id.logoText);
        userIcon = findViewById(R.id.userIcon);
        searchIcon = findViewById(R.id.searchIcon);
        searchBar = findViewById(R.id.searchBar);
        fragmentContainer = findViewById(R.id.mainFragmentContainer);

        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in2);
        fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);

//        searchBar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    hideSearchBar();
////                    closeKeyboard();
//                }
//            }
//        });

        // Set a touch listener to detect clicks outside the search bar
        findViewById(android.R.id.content).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (isSearchBarVisible && searchBar.getVisibility() == View.VISIBLE) {
                        int[] searchBarLocation = new int[2];
                        searchBar.getLocationOnScreen(searchBarLocation);

                        int touchX = (int) event.getRawX();
                        int touchY = (int) event.getRawY();

                        if (touchX < searchBarLocation[0] || touchX > searchBarLocation[0] + searchBar.getWidth() ||
                                touchY < searchBarLocation[1] || touchY > searchBarLocation[1] + searchBar.getHeight()) {
                            hideSearchBar();
//                            closeKeyboard();
                        }
                    }
                }
                return false;
            }
        });

        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to switch to another activity
                Intent intent = new Intent(MainActivity.this, UserInActivity.class);
                startActivity(intent);
            }
        });

        // initialize MainFragment
        MainFragment fragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFragmentContainer, fragment);

        // Clear the entire back stack
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        transaction.commit();
    }

    public void onSearchIconClick(View view) {
        if (!isSearchBarVisible) {
            showSearchBar();
        }
    }

    private void showSearchBar() {
        searchBar.setVisibility(View.VISIBLE);
        searchBar.requestFocus();
        searchBar.startAnimation(fadeInAnimation);
        logoText.setVisibility(View.GONE);
        logoText.startAnimation(fadeOutAnimation);
        isSearchBarVisible = true;
    }

    private void hideSearchBar() {
        searchBar.startAnimation(fadeOutAnimation);
        searchBar.setVisibility(View.GONE);
        searchBar.clearFocus();
        logoText.setVisibility(View.VISIBLE);
        logoText.requestFocus();
        isSearchBarVisible = false;
    }

    public void onCartClick(View view) {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainFragmentContainer);
        if (!(currentFragment instanceof CartFragment)) {
            CartFragment fragment = new CartFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.mainFragmentContainer, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public void onLogoClick(View view) {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.mainFragmentContainer);
        if (!(currentFragment instanceof MainFragment)) {
            MainFragment fragment = new MainFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.mainFragmentContainer, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

//    private void closeKeyboard() {
//        View view = this.getCurrentFocus();
//        if (view != null) {
//            InputMethodManager imm = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//    }
}

//@Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_cart) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
