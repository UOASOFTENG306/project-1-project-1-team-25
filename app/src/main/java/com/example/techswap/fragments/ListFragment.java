package com.example.techswap.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techswap.R;
import com.example.techswap.adapters.CarouselAdapter;
import com.example.techswap.databinding.FragmentListBinding;
import com.example.techswap.databinding.FragmentMainBinding;

import java.util.Arrays;
import java.util.List;

public class ListFragment extends Fragment {

    private FragmentListBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentListBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        // Vertical RecyclerView
        RecyclerView bestSellersRecyclerView = binding.listRecyclerView;
        LinearLayoutManager bestSellersLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        bestSellersRecyclerView.setLayoutManager(bestSellersLayoutManager);

        List<Integer> bestSellersImageList = Arrays.asList(
                R.drawable.tempimg,
                R.drawable.tempimg,
                R.drawable.tempimg,
                R.drawable.tempimg,
                R.drawable.tempimg,
                R.drawable.tempimg
        );

        List<String> bestSellersCaptionList = Arrays.asList(
                "Temp Item",
                "Temp Item",
                "Temp Item",
                "Temp Item",
                "Temp Item",
                "Temp Item"
        );

        List<Integer> bestSellersPriceList = Arrays.asList(
                11,
                11,
                11,
                11,
                11,
                11
        );

        List<String> bestSellersDescriptionList = Arrays.asList(
                "Lorem ipsum dolor sit amet",
                "Lorem ipsum dolor sit amet",
                "Lorem ipsum dolor sit amet",
                "Lorem ipsum dolor sit amet",
                "Lorem ipsum dolor sit amet",
                "Lorem ipsum dolor sit amet"
        );

        CarouselAdapter bestSellersAdapter = new CarouselAdapter(bestSellersImageList, bestSellersCaptionList, bestSellersPriceList, bestSellersDescriptionList);
        bestSellersRecyclerView.setAdapter(bestSellersAdapter);

        return rootView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
