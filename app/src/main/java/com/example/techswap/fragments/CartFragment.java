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
import com.example.techswap.database.DatabaseSetter;
import com.example.techswap.database.DatabaseUtils;
import com.example.techswap.databinding.FragmentCartBinding;
import com.example.techswap.item.Details;
import com.example.techswap.item.Item;
import com.example.techswap.item.categories.CPU;
import com.example.techswap.user.User;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements CarouselAdapter.AdapterCallback {

    private FragmentCartBinding binding;
    private CarouselAdapter adapter = new CarouselAdapter(CarouselAdapter.CarouselType.CART_ITEM, this);
    private final List<Item> itemList = new ArrayList<>();
    private final DatabaseUtils databaseUtils = new DatabaseUtils();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        View view = binding.getRoot();  // Use the inflated view from the binding

        // specifications recycler view
        RecyclerView recyclerView = binding.cartRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setContext(requireContext());
        recyclerView.setAdapter(adapter);

        if (User.getCurrentUser() != null){
            fetchCart();
            setItems(itemList);
        }

        binding.checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckout();
            }
        });

        return view;  // Return the inflated view
    }


    private void onCheckout() {
        DatabaseSetter db = new DatabaseSetter();
        db.clearCart(User.getCurrentUser().getUsername());
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void fetchCart() {
        FirebaseFirestore.getInstance().collection("cart").document(User.getCurrentUser().getUsername())
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            ArrayList<String> cartData = (ArrayList<String>) document.getData().get("item_id");
                            fetchCartItems(cartData);
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                });
    }

    private void fetchCartItems(ArrayList<String> cartData) {
        if (cartData != null && cartData.size() > 0) {
            FirebaseFirestore.getInstance().collection("items")
                    .whereIn("item_id", cartData)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                itemList.add(databaseUtils.mapToItem(document.getData()));
                            }
                            setItems(itemList);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    });
        }
    }

    public void setItems(List<Item> items) {
        adapter.updateData(items);
        Double total = new Double(0), gst, subTotal;

        for (Item item : items){
            total += item.getDetails().getPrice();
        }

        subTotal = total * 0.85;
        gst = total - subTotal;

        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);

        String totalString = "$" + df.format(total);
        String subtotalString = "$" + df.format(subTotal);
        String gstString = "$" + df.format(gst);

        binding.cartTotal.setText(totalString);
        binding.subtotalPriceText.setText(subtotalString);
        binding.feesPriceText.setText(gstString);
    }

    @Override
    public void onAdapterItemClick(List<Item> items) {
        setItems(items);
    }
}