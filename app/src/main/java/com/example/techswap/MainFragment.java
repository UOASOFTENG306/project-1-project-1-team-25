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

        // initialise recycler view
        RecyclerView recyclerView = binding.categoryRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
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

        List<String> captionList = Arrays.asList(
                "CPU",
                "Graphics Card",
                "Motherboard",
                "Storage",
                "Memory",
                "Power",
                "Case"
        );

        CarouselAdapter adapter = new CarouselAdapter(imageList, captionList);
        recyclerView.setAdapter(adapter);

        return binding.getRoot();

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