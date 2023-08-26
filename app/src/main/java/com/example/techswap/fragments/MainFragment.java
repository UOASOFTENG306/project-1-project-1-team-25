package com.example.techswap.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.techswap.adapters.CarouselAdapter;
import com.example.techswap.database.Database;
import com.example.techswap.databinding.FragmentMainBinding;
import com.example.techswap.item.Item;
import com.example.techswap.item.ItemFactory;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MainFragment extends Fragment {

    final CarouselAdapter dealsAdapter = new CarouselAdapter(CarouselAdapter.CarouselType.HORIZONTAL_ITEM);
    final CarouselAdapter bestSellersAdapter = new CarouselAdapter(CarouselAdapter.CarouselType.LIST_ITEM);
    private final List<Item> bestSellersList = new ArrayList<>();
    private final List<Item> dealsList = new ArrayList<>();
    private FragmentMainBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        fetchBestSellers();
        fetchDeals();

        // First Carousel
        RecyclerView categoryRecyclerView = binding.categoryRecyclerView;
        LinearLayoutManager categoryLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(categoryLayoutManager);

        List<String> categoryImageList = Arrays.asList(
                "https://firebasestorage.googleapis.com/v0/b/techswap-e2b95.appspot.com/o/res%2Fcpu.png?alt=media&token=50d5ec1f-35fb-411f-85ab-e8e6d90a4654",
                "https://firebasestorage.googleapis.com/v0/b/techswap-e2b95.appspot.com/o/res%2Fgpu.png?alt=media&token=9faff4a5-f2fc-4473-81bc-d2beb2e6f087",
                "https://firebasestorage.googleapis.com/v0/b/techswap-e2b95.appspot.com/o/res%2Fmotherboard.png?alt=media&token=36205014-d4db-456d-a6f6-f21a5f4873b4",
                "https://firebasestorage.googleapis.com/v0/b/techswap-e2b95.appspot.com/o/res%2Fstorage.png?alt=media&token=d3b2f629-e198-4975-8fae-0c8853ce7acc",
                "https://firebasestorage.googleapis.com/v0/b/techswap-e2b95.appspot.com/o/res%2Fmemory.png?alt=media&token=dc9d2068-3013-4840-8b25-70fe656e314e",
                "https://firebasestorage.googleapis.com/v0/b/techswap-e2b95.appspot.com/o/res%2Fpowersupply.png?alt=media&token=16ea5f20-96d9-4660-8e82-992bccaf4aa1",
                "https://firebasestorage.googleapis.com/v0/b/techswap-e2b95.appspot.com/o/res%2Fpccase.png?alt=media&token=2ba0a331-a666-439e-8682-a6951c063461",
                "https://firebasestorage.googleapis.com/v0/b/techswap-e2b95.appspot.com/o/res%2Fother.png?alt=media&token=6cde2eee-4aaa-46f2-9ff2-1ae10db775b9"
        );

        List<String> categoryCaptionList = Arrays.asList(
                "CPUs",
                "GPUs",
                "MOBOs",
                "Storage",
                "Memory",
                "Power",
                "Case",
                "Other"
        );

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);

        CarouselAdapter categoryAdapter = new CarouselAdapter(categoryImageList, categoryCaptionList, null, null, null, CarouselAdapter.CarouselType.CATEGORY);
        categoryAdapter.setContext(requireContext());
        categoryRecyclerView.setAdapter(categoryAdapter);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(categoryRecyclerView);

        // Second Carousel
        RecyclerView dealsRecyclerView = binding.dealsRecyclerView;
        LinearLayoutManager dealsLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        dealsRecyclerView.setLayoutManager(dealsLayoutManager);
        dealsAdapter.setContext(requireContext());
        dealsRecyclerView.setAdapter(dealsAdapter);

        // Vertical RecyclerView
        RecyclerView bestSellersRecyclerView = binding.bestSellersRecyclerView;
        LinearLayoutManager bestSellersLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        bestSellersRecyclerView.setLayoutManager(bestSellersLayoutManager);
        bestSellersAdapter.setContext(requireContext());
        bestSellersRecyclerView.setAdapter(bestSellersAdapter);

        // Populate recyclerview with placeholder items
        List<Item> items = new ArrayList<>();
        Item placeholderItem = ItemFactory.getItem("Other");
        for (int i = 0; i < 7; i++) {
            items.add(placeholderItem);
        }

        setDeals(items);
        setBestSellers(items);

        return rootView;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void fetchBestSellers() {
        // TODO: Add metric for being "best sellers"
        FirebaseFirestore.getInstance().collection("items")
                .orderBy("title").limit(6)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            bestSellersList.add(Database.mapToItem(document.getData()));
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
                .orderBy("title", Query.Direction.DESCENDING).limit(6)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            dealsList.add(Database.mapToItem(document.getData()));
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
