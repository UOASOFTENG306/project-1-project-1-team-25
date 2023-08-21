package com.example.techswap.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techswap.adapters.CarouselAdapter;
import com.example.techswap.adapters.SpecificationAdapter;
import com.example.techswap.databinding.FragmentDetailsBinding;

import java.util.Arrays;
import java.util.List;

public class DetailsFragment extends Fragment {

private FragmentDetailsBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentDetailsBinding.inflate(inflater, container, false);

        // set field text
        binding.detailsTitle.setText("Title");
        binding.detailsSubtitle.setText("Subtitle");
        binding.detailsPrice.setText("$299.99");
        binding.detailsDescription.setText(java.time.LocalDateTime.now().toString());

        // specifications recycler view
        RecyclerView recyclerView = binding.specificationRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        List<String> specificationList = Arrays.asList(
                "Brand",
                "Clock speed"
        );

        List<String> valueList = Arrays.asList(
                "Intel",
                "Like, a bajillion"
        );

        SpecificationAdapter adapter = new SpecificationAdapter(specificationList, valueList);
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