package com.example.techswap.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.techswap.R;
import com.example.techswap.adapters.ImageAdapter;
import com.example.techswap.adapters.SpecificationAdapter;
import com.example.techswap.databinding.FragmentDetailsBinding;

import java.time.LocalDateTime;
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
        // binding.detailsDescription.setText(LocalDateTime.now().toString());

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

        ViewPager2 mViewPager = binding.detailsPager;

        List<Integer> imageList = Arrays.asList(
                R.drawable.cpu,
                R.drawable.gpu,
                R.drawable.motherboard,
                R.drawable.harddisk,
                R.drawable.ram,
                R.drawable.powersupply,
                R.drawable.pccase
        );

        ImageAdapter adapterView = new ImageAdapter(imageList);
        mViewPager.setAdapter(adapterView);

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