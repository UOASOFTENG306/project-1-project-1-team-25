package com.example.techswap.fragments;

import static android.content.ContentValues.TAG;

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
import com.example.techswap.database.Database;
import com.example.techswap.databinding.FragmentListBinding;
import com.example.techswap.interfaces.ICarouselAdapter;
import com.example.techswap.item.Item;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private final List<Item> itemList = new ArrayList<>();
    ICarouselAdapter carouselAdapter;
    private FragmentListBinding binding;

    /**
     * Creates a new instance of ListFragment configured with a specific category.
     *
     * @param category The category to be associated with the ListFragment.
     * @return A new instance of ListFragment with the specified category.
     */
    public static ListFragment listCategory(String category) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putSerializable("category", category);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Creates a new instance of ListFragment configured for displaying search results based on a search term.
     *
     * @param searchTerm The search term to be used for fetching and displaying search results.
     * @return A new instance of ListFragment configured for displaying search results.
     */
    public static ListFragment listSearch(String searchTerm) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putSerializable("searchTerm", searchTerm);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Inflates the layout for the fragment's UI and initializes the UI components.
     *
     * @param inflater           The LayoutInflater used to inflate the layout.
     * @param container          The parent ViewGroup for the fragment UI.
     * @param savedInstanceState A Bundle containing saved state information.
     * @return The root View of the fragment's UI.
     */
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
                    searchItems(searchTerm.toLowerCase());
                    carouselAdapter = new CarouselAdapter(CarouselAdapter.CarouselType.LIST_ITEM);
                }
            }
        }

        // Vertical RecyclerView
        RecyclerView listRecyclerView = binding.listRecyclerView;
        LinearLayoutManager listLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        listRecyclerView.setLayoutManager(listLayoutManager);
        carouselAdapter.setContext(requireContext());
        listRecyclerView.setAdapter((RecyclerView.Adapter<?>) carouselAdapter);

        return rootView;
    }

    /**
     * Called when the fragment's view has been created and is ready to be populated with UI elements.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Called when the view previously created by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has been detached from the fragment.
     * This method releases references to the ViewBinding and performs necessary cleanup.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Fetches items from a database collection based on the specified category name and updates the UI accordingly.
     *
     * @param categoryName The category name to filter items by.
     */
    public void fetchItems(String categoryName) {
        FirebaseFirestore.getInstance().collection("items")
                .whereEqualTo("category_id", categoryName).orderBy("title").limit(10)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        itemList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            itemList.add(Database.mapToItem(document.getData()));
                        }
                        setContent(itemList);
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }

    /**
     * Searches for items in a database collection based on a keyword and updates the UI accordingly.
     *
     * @param keyword The keyword used for searching items.
     */
    public void searchItems(String keyword) {
        FirebaseFirestore.getInstance().collection("items")
                .whereGreaterThanOrEqualTo("search_title", keyword)
                .whereLessThanOrEqualTo("search_title", keyword + "\uf8ff")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        itemList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            itemList.add(Database.mapToItem(document.getData()));
                        }
                        setContent(itemList);
                        // Message varies on search result size
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

    /**
     * Updates the content of the UI with the provided list of items.
     *
     * @param items The list of items to be displayed in the UI.
     */
    public void setContent(List<Item> items) {
        carouselAdapter.updateData(items);
    }

    /**
     * Sets the header text of the UI with the provided text.
     *
     * @param text The text to be displayed as the header.
     */
    public void setHeader(String text) {
        binding.listTitle.setText(text);
    }
}




