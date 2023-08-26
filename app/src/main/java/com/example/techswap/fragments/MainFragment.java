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
import com.example.techswap.interfaces.ICarouselAdapter;
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

    ICarouselAdapter dealsAdapter = new CarouselAdapter(CarouselAdapter.CarouselType.HORIZONTAL_ITEM);
    ICarouselAdapter bestSellersAdapter = new CarouselAdapter(CarouselAdapter.CarouselType.LIST_ITEM);
    private final List<Item> bestSellersList = new ArrayList<>();
    private final List<Item> dealsList = new ArrayList<>();
    private FragmentMainBinding binding;

    /**
     * Inflates the layout for the login fragment's UI and initializes UI components and click listeners.
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
        dealsRecyclerView.setAdapter((RecyclerView.Adapter<?>) dealsAdapter);

        // Vertical RecyclerView
        RecyclerView bestSellersRecyclerView = binding.bestSellersRecyclerView;
        LinearLayoutManager bestSellersLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        bestSellersRecyclerView.setLayoutManager(bestSellersLayoutManager);
        bestSellersAdapter.setContext(requireContext());
        bestSellersRecyclerView.setAdapter((RecyclerView.Adapter<?>) bestSellersAdapter);

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

    /**
     * Called when the fragment's view has been created and is ready to be populated with UI elements.
     *
     * @param view The root view of the fragment.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     *                           Any data that was previously saved in the state can be obtained from here.
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Fetches a list of best-selling items from the Firestore database.
     * The fetched items are ordered by title and limited to a maximum of 6.
     * The retrieved items are added to the bestSellersList and then set as the best sellers.
     */
    private void fetchBestSellers() {
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

    /**
     * Fetches a list of deals from the Firestore database.
     * The fetched deals are ordered by title in descending order and limited to a maximum of 6.
     * The retrieved deals are added to the dealsList and then set as the deals.
     */
    private void fetchDeals() {
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

    /**
     * Called when the fragment's view is about to be destroyed.
     * This is an appropriate place to release resources associated with the view.
     * The binding instance is set to null to prevent memory leaks.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Sets the list of items as the deals for display.
     *
     * @param items The list of Item objects representing the deals to be displayed.
     */
    public void setDeals(List<Item> items) {
        dealsAdapter.updateData(items);
    }

    /**
     * Sets the list of items as the best sellers for display.
     *
     * @param items The list of Item objects representing the best sellers to be displayed.
     */
    public void setBestSellers(List<Item> items) {
        bestSellersAdapter.updateData(items);
    }
}
