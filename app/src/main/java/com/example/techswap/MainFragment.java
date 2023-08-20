package com.example.techswap;

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

import com.example.techswap.adapters.CarouselAdapter;
import com.example.techswap.databinding.FragmentMainBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private final List<Map<String, Object>> itemList = new ArrayList<>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        fetchTopItems();

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

        CarouselAdapter categoryAdapter = new CarouselAdapter(categoryImageList, categoryCaptionList, null, null);
        categoryRecyclerView.setAdapter(categoryAdapter);

        // Second Carousel
        RecyclerView dealsRecyclerView = binding.dealsRecyclerView;
        LinearLayoutManager dealsLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        dealsRecyclerView.setLayoutManager(dealsLayoutManager);

        List<Integer> dealsImageList = Arrays.asList(
                R.drawable.tempimg,
                R.drawable.tempimg,
                R.drawable.tempimg,
                R.drawable.tempimg,
                R.drawable.tempimg,
                R.drawable.tempimg
        );

        List<String> dealsCaptionList = Arrays.asList(
                "Temp Item",
                "Temp Item",
                "Temp Item",
                "Temp Item",
                "Temp Item",
                "Temp Item"
        );

        List<Integer> dealsPriceList = Arrays.asList(
                11,
                11,
                11,
                11,
                11,
                11
        );

        CarouselAdapter dealsAdapter = new CarouselAdapter(dealsImageList, dealsCaptionList, dealsPriceList, null);
        dealsRecyclerView.setAdapter(dealsAdapter);

        // Vertical RecyclerView
        RecyclerView bestSellersRecyclerView = binding.bestSellersRecyclerView;
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

        binding.bestSellersHeader.setOnClickListener(view1 -> {
            DetailsFragment fragment = new DetailsFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.mainFragmentContainer, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }

    /**
     * Returns a map of the requested document data.
     * Returned object: List of Maps, each map representing one object.
     */
    public void fetchTopItems(){
        FirebaseFirestore.getInstance().collection("items")
                .orderBy("title").limit(6)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            itemList.add(document.getData());
                        }
                        System.out.println(itemList);
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
}
