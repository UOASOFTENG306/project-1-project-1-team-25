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
import com.example.techswap.adapters.ItemCardAdapter;
import com.example.techswap.adapters.SpecificationAdapter;
import com.example.techswap.databinding.FragmentCartBinding;
import com.example.techswap.databinding.FragmentDetailsBinding;

import java.util.Arrays;
import java.util.List;

public class CartFragment extends Fragment {

private FragmentCartBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentCartBinding.inflate(inflater, container, false);

        // specifications recycler view
        RecyclerView recyclerView = binding.cartRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        List<Integer> imageList = Arrays.asList(
                R.drawable.cpu,
                R.drawable.gpu,
                R.drawable.motherboard,
                R.drawable.harddisk,
                R.drawable.ram,
                R.drawable.powersupply,
                R.drawable.pccase
        );

        List<String> titleList = Arrays.asList(
                "CPU",
                "Graphics Card",
                "Motherboard",
                "Storage",
                "Memory",
                "Power",
                "Case"
        );

        List<String> subtitleList = Arrays.asList(
                "CPU",
                "Graphics Card",
                "Motherboard",
                "Storage",
                "Memory",
                "Power",
                "Case"
        );

        List<String> priceList = Arrays.asList(
                "CPU",
                "Graphics Card",
                "Motherboard",
                "Storage",
                "Memory",
                "Power",
                "Case"
        );

        ItemCardAdapter adapter = new ItemCardAdapter(imageList, titleList, subtitleList, priceList);
        recyclerView.setAdapter(adapter);

        return binding.getRoot();

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