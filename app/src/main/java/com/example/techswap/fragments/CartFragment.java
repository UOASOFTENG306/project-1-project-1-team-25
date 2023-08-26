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

    /**
     * Clears the items in the cart.
     * Removes all items from the list representing the cart contents.
     */
    public static void clearCart() {
        itemList.clear();
    }
    private TextView emptyCartMessage;

    /**
     * Called to create the view hierarchy associated with the fragment.
     * This method inflates the layout for the fragment using the provided inflater and container.
     * It then initializes and configures the UI components and adapter for displaying the cart items.
     * If a user is logged in, it fetches the cart data and sets it to the adapter.
     * Additionally, it sets up the OnClickListener for the checkout button.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container The parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return The root view of the fragment's layout.
     */
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

    /**
     * Handles the checkout process.
     * Clears the cart for the current user in the database, clears the local cart itemList,
     * updates the adapter with the cleared items, and displays a checkout completion toast.
     */
    private void onCheckout() {
        db.clearCart(User.getCurrentUser().getUsername());
        itemList.clear();
        setItems(itemList);
        Toast.makeText(requireContext(), "Checkout complete", Toast.LENGTH_LONG).show();
    }

    /**
     * Called when the view hierarchy associated with the fragment is created.
     * This method is called after the fragment's view has been inflated and added to the view hierarchy.
     * It provides the inflated view as a parameter and allows you to perform additional setup and
     * initialization of UI components or data.
     *
     * @param view The view hierarchy associated with the fragment.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Called when the view hierarchy associated with the fragment is being removed.
     * This method is called when the fragment's view is being detached and will no longer be used.
     * It's a good place to perform cleanup operations related to UI components and references.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Fetches the cart data for the current user from the Firestore database.
     * Retrieves the cart data stored under the user's username and triggers the retrieval
     * of detailed cart items using the fetched item IDs.
     */
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

    /**
     * Fetches detailed cart items from the Firestore database using the provided cart data.
     * Retrieves the items corresponding to the item IDs in the cartData list from the "items" collection.
     * Clears the existing itemList and populates it with the fetched cart items.
     *
     * @param cartData The list of item IDs representing the user's cart contents.
     */
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

    /**
     * Sets the cart items and updates the UI with the item information and total costs.
     * Updates the adapter with the provided list of items and calculates the total, subtotal, and GST.
     * Updates the UI with the calculated values and toggles the visibility of cart total information based on cart contents.
     *
     * @param items The list of Item objects representing the user's cart contents.
     */
    public void setItems(List<Item> items) {
        adapter.updateData(items);

        // Default to $0.00 for empty cart
        if (items.isEmpty()) {
            binding.cartTotal.setText(R.string.price_placeholder);
            binding.subtotalPriceText.setText(R.string.price_placeholder);
            binding.feesPriceText.setText(R.string.price_placeholder);
                // Hide the cart total information if the cart is empty
            emptyCartMessage.setVisibility(View.VISIBLE);
        } else {
            // Factor in gst
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

    /**
     * Callback method triggered when an item in the adapter is clicked.
     * Updates the UI with the new list of items, triggering a recalculation of total costs and UI updates.
     *
     * @param items The updated list of Item objects representing the user's cart contents.
     */
    @Override
    public void onAdapterItemClick(List<Item> items) {
        setItems(items);
    }
}