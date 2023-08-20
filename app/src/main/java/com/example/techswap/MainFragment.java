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
import com.example.techswap.user.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private final CollectionReference itemCollection = FirebaseFirestore.getInstance().collection("items");
    private final CollectionReference userCollection = FirebaseFirestore.getInstance().collection("users");
    private final CollectionReference cartCollection = FirebaseFirestore.getInstance().collection("cart");
    private List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
    private Map<String, Object> userData = new HashMap<String, Object>();
    private Map<String, Object> cartData = new HashMap<String, Object>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

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

        binding.bestSellersHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailsFragment fragment = new DetailsFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.mainFragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Returns a map of the requested document data.
     * Returned object: List of Maps, each map representing one object.
     */
    private void fetchItemsByCategory(String category, int limit){
        itemCollection
                .whereEqualTo("category_id", category).orderBy("title").limit(limit)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                itemList.add(document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    /**
     * Returns the requested user data
     * Returned object: Map, representing user.
     */
    private void fetchUserData(User user){
        userCollection.document(String.valueOf(user.getId())).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        userData = document.getData();
                    } else {
                        Log.d(TAG, "User not found");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    private void fetchCartData(String id){
        userCollection.document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        cartData = document.getData();
                    } else {
                        Log.d(TAG, "Cart not found");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
}
