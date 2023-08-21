package com.example.techswap.fragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
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
import com.example.techswap.databinding.FragmentMainBinding;
import com.example.techswap.database.DatabaseUtils;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.example.techswap.item.Details;
import com.example.techswap.item.Item;
import com.example.techswap.item.categories.CPU;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private final List<Item> bestSellersList = new ArrayList<>();
    private final List<Item> dealsList = new ArrayList<>();
    private final DatabaseUtils databaseUtils = new DatabaseUtils();
    CarouselAdapter dealsAdapter = new CarouselAdapter(CarouselAdapter.CarouselType.HORIZONTAL_ITEM);
    CarouselAdapter bestSellersAdapter = new CarouselAdapter(CarouselAdapter.CarouselType.LIST_ITEM);

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        fetchBestSellers();
        fetchDeals();

        binding = FragmentMainBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        // First Carousel
        RecyclerView categoryRecyclerView = binding.categoryRecyclerView;
        LinearLayoutManager categoryLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(categoryLayoutManager);

        List<Integer> categoryImageList = Arrays.asList(
                R.drawable.cpu,
                R.drawable.gpu,
                R.drawable.motherboard,
                R.drawable.harddisk,
                R.drawable.ram,
                R.drawable.powersupply,
                R.drawable.pccase
        );

        List<String> categoryCaptionList = Arrays.asList(
                "CPU",
                "Graphics Card",
                "Motherboard",
                "Storage",
                "Memory",
                "Power",
                "Case"
        );

        CarouselAdapter categoryAdapter = new CarouselAdapter(categoryImageList, categoryCaptionList, null, null, CarouselAdapter.CarouselType.CATEGORY);
        categoryRecyclerView.setAdapter(categoryAdapter);


        // Second Carousel
        RecyclerView dealsRecyclerView = binding.dealsRecyclerView;
        LinearLayoutManager dealsLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        dealsRecyclerView.setLayoutManager(dealsLayoutManager);
        dealsRecyclerView.setAdapter(dealsAdapter);

        List<Item> items = new ArrayList<>();
        CPU cpu = new CPU();
        Details details = new Details();
        details.setTitle("Wow");
        details.setPrice(99.99);
        cpu.setDetails(details);
        for(int i = 0; i<7 ; i++) {
            items.add(cpu);
        }

        setDeals(items);


        // Vertical RecyclerView
        RecyclerView bestSellersRecyclerView = binding.bestSellersRecyclerView;
        LinearLayoutManager bestSellersLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        bestSellersRecyclerView.setLayoutManager(bestSellersLayoutManager);
        bestSellersRecyclerView.setAdapter(bestSellersAdapter);

        items = new ArrayList<>();
        cpu = new CPU();
        details = new Details();
        details.setTitle("Wow");
        details.setSubtitle("Amazing");
        details.setPrice(99.99);
        cpu.setDetails(details);
        for(int i = 0; i<7 ; i++) {
            items.add(cpu);
        }

        setBestSellers(items);

        return rootView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.bestSellersHeader.setOnClickListener(view1 -> {
            DetailsFragment fragment = new DetailsFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.mainFragmentContainer, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }

    private void fetchBestSellers(){
        // TODO: Find metric for being "best seller"
        FirebaseFirestore.getInstance().collection("items")
                .orderBy("title").limit(6)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            bestSellersList.add(databaseUtils.mapToItem(document.getData()));
                        }
                        setBestSellers(bestSellersList);
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }

    private void fetchDeals() {
        // TODO: Add metric for being "deals"
        FirebaseFirestore.getInstance().collection("items")
                .orderBy("title").limit(6)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            dealsList.add(databaseUtils.mapToItem(document.getData()));
                        }
                        setDeals(dealsList);
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setDeals(List<Item> items) {
        dealsAdapter.updateData(items);
    }

    public void setBestSellers(List<Item> items) {
        bestSellersAdapter.updateData(items);
    }
}
