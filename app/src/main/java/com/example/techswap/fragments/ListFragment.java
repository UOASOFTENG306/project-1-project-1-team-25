package com.example.techswap.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.techswap.adapters.CarouselAdapter;
import com.example.techswap.database.DatabaseUtils;
import com.example.techswap.databinding.FragmentListBinding;
import com.example.techswap.item.Item;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ListFragment extends Fragment {

    private final List<Item> itemList = new ArrayList<>();
    private final DatabaseUtils databaseUtils = new DatabaseUtils();
    CarouselAdapter carouselAdapter;
    private FragmentListBinding binding;

    public static ListFragment listCategory(String category) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putSerializable("category", category);
        fragment.setArguments(args);
        return fragment;
    }

    public static ListFragment listSearch(String searchTerm) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putSerializable("searchTerm", searchTerm);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentListBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        Bundle args = getArguments();
        if (args != null) {
            String category = (String) args.getSerializable("category");
            if (category != null) {
                fetchItems(category);
                setHeader(category);
                if (category.equals("GPU") || category.equals("Motherboard") || category.equals("Case")) {
                    carouselAdapter = new CarouselAdapter(CarouselAdapter.CarouselType.LARGE_LIST_ITEM);
                } else {
                    carouselAdapter = new CarouselAdapter(CarouselAdapter.CarouselType.LIST_ITEM);
                }
            } else {
                String searchTerm = (String) args.getSerializable("searchTerm");
                if (searchTerm != null) {
                    searchItems(searchTerm);
                    carouselAdapter = new CarouselAdapter(CarouselAdapter.CarouselType.LIST_ITEM);
                }
            }
        }

        // Vertical RecyclerView
        RecyclerView listRecyclerView = binding.listRecyclerView;
        LinearLayoutManager listLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        listRecyclerView.setLayoutManager(listLayoutManager);
        carouselAdapter.setContext(requireContext());
        listRecyclerView.setAdapter(carouselAdapter);

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

    public void fetchItems(String categoryName) {
        FirebaseFirestore.getInstance().collection("items")
                .whereEqualTo("category_id", categoryName).orderBy("title").limit(10)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        itemList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            itemList.add(databaseUtils.mapToItem(document.getData()));
                        }
                        setContent(itemList);
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }

    public void searchItems(String keyword) {
        FirebaseFirestore.getInstance().collection("items")
                .whereGreaterThanOrEqualTo("search_title", keyword)
                .whereLessThanOrEqualTo("search_title", keyword + "\uf8ff")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        itemList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            itemList.add(databaseUtils.mapToItem(document.getData()));
                        }
                        setContent(itemList);
                        switch (itemList.size()) {
                            case 0:
                                setHeader("No results found for \"" + keyword + "\". Try searching again!");
                                break;
                            case 1:
                                setHeader(itemList.size() + " result found for \"" + keyword + "\"");
                                break;
                            default:
                                setHeader(itemList.size() + " results found for \"" + keyword + "\"");
                        }

                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }

    public void setContent(List<Item> items) {
        carouselAdapter.updateData(items);
    }

    public void setHeader(String text) {
        binding.listTitle.setText(text);
    }
}




