package com.example.techswap.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.techswap.activities.MainActivity;
import com.example.techswap.activities.SellActivity;
import com.example.techswap.databinding.FragmentUserLoggedInBinding;
import com.example.techswap.user.User;

public class UserLoggedInFragment extends Fragment {

    private FragmentUserLoggedInBinding binding;

    /**
     * Inflates the layout for the login fragment's UI and initializes UI components and click listeners.
     *
     * @param inflater           The LayoutInflater used to inflate the layout.
     * @param container          The parent ViewGroup for the fragment UI.
     * @param savedInstanceState A Bundle containing saved state information.
     * @return The root View of the fragment's UI.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserLoggedInBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    /**
     * Called when the fragment's view has been created and is ready to be populated with UI elements.
     * Sets up click listeners for the "Sell Item" and "Log Out" buttons within the view.
     *
     * @param view               The root view of the fragment.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     *                           Any data that was previously saved in the state can be obtained from here.
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.sellItemButton.setOnClickListener(view1 -> {
            // Code to switch to another activity
            Intent intent = new Intent(requireActivity(), SellActivity.class);
            startActivity(intent);
        });

        binding.logOutButton.setOnClickListener(view2 -> {
            // Code to switch to another activity
            User.setCurrentUser(null);
            CartFragment.clearCart();
            Intent intent = new Intent(requireActivity(), MainActivity.class);
            startActivity(intent);
            Toast.makeText(requireContext(), "Logged Out", Toast.LENGTH_LONG).show();

        });
    }
}