package com.example.techswap.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techswap.R;
import com.example.techswap.adapters.CarouselAdapter;
import com.example.techswap.databinding.FragmentListBinding;
import com.example.techswap.item.Details;
import com.example.techswap.item.Item;
import com.example.techswap.item.categories.CPU;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListFragment extends Fragment {

    private FragmentListBinding binding;

    CarouselAdapter carouselAdapter = new CarouselAdapter(CarouselAdapter.CarouselType.LIST_ITEM);

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
        bestSellersRecyclerView.setAdapter(carouselAdapter);

        List<Item> items = new ArrayList<>();
        CPU cpu = new CPU();
        Details details = new Details();
        details.setTitle("Wow");
        details.setSubtitle("Amazing");
        details.setPrice(99.99);
        cpu.setDetails(details);
        for(int i = 0; i<7 ; i++) {
            items.add(cpu);
        }
        setContent(items);
        setHeader("test");

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

    public void setContent(List<Item> items) {
        carouselAdapter.updateData(items);
    }

    public void setHeader(String text) {
        binding.listTitle.setText(text);
    }
}
