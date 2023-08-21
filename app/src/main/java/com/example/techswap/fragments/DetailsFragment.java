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
import com.example.techswap.item.Item;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class DetailsFragment extends Fragment {

private FragmentDetailsBinding binding;

    public static DetailsFragment newInstance(Item item) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("item", item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentDetailsBinding.inflate(inflater, container, false);

        Bundle args = getArguments();
        if (args != null) {
            Item item = (Item) args.getSerializable("item");
            // set field text
            binding.detailsTitle.setText(item.getDetails().getTitle());
            binding.detailsSubtitle.setText(item.getDetails().getSubtitle());
            binding.detailsPrice.setText("$" + item.getDetails().getPrice());
            binding.detailsDescription.setText(item.getDetails().getDescription());

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
        } else {

        }

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