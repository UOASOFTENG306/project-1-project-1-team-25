package com.example.techswap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techswap.adapters.CarouselAdapter;
import com.example.techswap.databinding.FragmentMainBinding;

import java.util.Arrays;
import java.util.List;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMainBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        // First Carousel
        RecyclerView recyclerView1 = binding.categoryRecyclerView;
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(layoutManager1);

        List<Integer> imageList1 = Arrays.asList(
                R.drawable.cpu,
                R.drawable.gpu,
                R.drawable.motherboard,
                R.drawable.harddisk,
                R.drawable.ram,
                R.drawable.powersupply,
                R.drawable.pccase
        );

        List<String> captionList1 = Arrays.asList(
                "CPU",
                "Graphics Card",
                "Motherboard",
                "Storage",
                "Memory",
                "Power",
                "Case"
        );

        CarouselAdapter adapter1 = new CarouselAdapter(imageList1, captionList1, null, null);
        recyclerView1.setAdapter(adapter1);

        // Second Carousel
        RecyclerView recyclerView2 = binding.dealsRecyclerView;
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager2);

        List<Integer> imageList2 = Arrays.asList(
                R.drawable.tempimg,
                R.drawable.tempimg,
                R.drawable.tempimg,
                R.drawable.tempimg,
                R.drawable.tempimg,
                R.drawable.tempimg
                // Add more images here
        );

        List<String> captionList2 = Arrays.asList(
                "Temp Item",
                "Temp Item",
                "Temp Item",
                "Temp Item",
                "Temp Item",
                "Temp Item"
                // Add more captions here
        );

        List<Integer> priceList = Arrays.asList(
                11,
                11,
                11,
                11,
                11,
                11
                // Add more captions here
        );

        CarouselAdapter adapter2 = new CarouselAdapter(imageList2, captionList2, priceList, null);
        recyclerView2.setAdapter(adapter2);

        // Vertical RecyclerView
        RecyclerView verticalRecyclerView = binding.bestSellersRecyclerView;
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        verticalRecyclerView.setLayoutManager(verticalLayoutManager);

        List<Integer> verticalImageList = Arrays.asList(
                R.drawable.tempimg,
                R.drawable.tempimg,
                R.drawable.tempimg,
                R.drawable.tempimg,
                R.drawable.tempimg,
                R.drawable.tempimg
                // Add more images here
        );

        List<String> verticalCaptionList = Arrays.asList(
                "Temp Item",
                "Temp Item",
                "Temp Item",
                "Temp Item",
                "Temp Item",
                "Temp Item"
                // Add more captions here
        );

        List<Integer> priceList2 = Arrays.asList(
                11,
                11,
                11,
                11,
                11,
                11
                // Add more captions here
        );

        List<String> descriptionList = Arrays.asList(
                "Lorem ipsum dolor sit amet",
                "Lorem ipsum dolor sit amet",
                "Lorem ipsum dolor sit amet",
                "Lorem ipsum dolor sit amet",
                "Lorem ipsum dolor sit amet",
                "Lorem ipsum dolor sit amet"
                // Add more captions here
        );

        CarouselAdapter verticalAdapter = new CarouselAdapter(verticalImageList, verticalCaptionList, priceList2, descriptionList);
        verticalRecyclerView.setAdapter(verticalAdapter);

        return rootView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.categoryRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
