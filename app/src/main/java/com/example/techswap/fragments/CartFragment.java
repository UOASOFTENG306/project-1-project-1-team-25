package com.example.techswap.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techswap.R;
import com.example.techswap.adapters.CarouselAdapter;
import com.example.techswap.database.Database;
import com.example.techswap.databinding.FragmentCartBinding;
import com.example.techswap.interfaces.ICarouselAdapter;
import com.example.techswap.interfaces.IDatabase;
import com.example.techswap.item.Item;
import com.example.techswap.user.User;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class CartFragment extends Fragment implements CarouselAdapter.AdapterCallback {


    IDatabase db = new Database();
    private static final List<Item> itemList = new ArrayList<>();
    ICarouselAdapter adapter = new CarouselAdapter(CarouselAdapter.CarouselType.CART_ITEM, this);
    private FragmentCartBinding binding;

    public static void clearCart() {
        itemList.clear();
    }
    private TextView emptyCartMessage;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        View view = binding.getRoot();  // Use the inflated view from the binding

        emptyCartMessage = binding.emptyCartTextView;

        // specifications recycler view
        RecyclerView recyclerView = binding.cartRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setContext(requireContext());
        recyclerView.setAdapter((RecyclerView.Adapter<?>) adapter);

        if (User.getCurrentUser() != null) {
            fetchCart();
            setItems(itemList);
        }

        binding.checkoutButton.setOnClickListener(v -> onCheckout());

        return view;  // Return the inflated view
    }

    private void onCheckout() {
        db.clearCart(User.getCurrentUser().getUsername());
        itemList.clear();
        setItems(itemList);
        Toast.makeText(requireContext(), "Checkout complete", Toast.LENGTH_LONG).show();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressWarnings("unchecked")
    public void fetchCart() {
        FirebaseFirestore.getInstance().collection("cart").document(User.getCurrentUser().getUsername())
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            ArrayList<String> cartData = (ArrayList<String>) Objects.requireNonNull(document.getData()).get("item_id");
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
                            itemList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                itemList.add(Database.mapToItem(document.getData()));
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

        if (items.isEmpty()) {
            binding.cartTotal.setText(R.string.price_placeholder);
            binding.subtotalPriceText.setText(R.string.price_placeholder);
            binding.feesPriceText.setText(R.string.price_placeholder);
                // Hide the cart total information if the cart is empty
            emptyCartMessage.setVisibility(View.VISIBLE);
        } else {
            double total = 0, gst, subTotal;

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

            emptyCartMessage.setVisibility(View.GONE);
        }


    }

    @Override
    public void onAdapterItemClick(List<Item> items) {
        setItems(items);
    }
}