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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUserLoggedInBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        return rootView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.sellItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code to switch to another activity
                Intent intent = new Intent(requireActivity(), SellActivity.class);
                startActivity(intent);
            }
        });

        binding.logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code to switch to another activity
                User.setCurrentUser(null);
                Intent intent = new Intent(requireActivity(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(requireContext(), "Logged Out", Toast.LENGTH_LONG).show();

            }
        });
    }
}