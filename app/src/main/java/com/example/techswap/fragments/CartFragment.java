package com.example.techswap.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techswap.adapters.CarouselAdapter;
import com.example.techswap.database.DatabaseSetter;
import com.example.techswap.databinding.FragmentCartBinding;
import com.example.techswap.item.Details;
import com.example.techswap.item.Item;
import com.example.techswap.item.categories.CPU;
import com.example.techswap.user.User;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private CarouselAdapter adapter = new CarouselAdapter(CarouselAdapter.CarouselType.CART_ITEM);

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentCartBinding.inflate(inflater, container, false);

        // specifications recycler view
        RecyclerView recyclerView = binding.cartRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setContext(requireContext());
        recyclerView.setAdapter(adapter);

        List<Item> items = new ArrayList<>();
        CPU cpu = new CPU();
        Details details = new Details();
        details.setTitle("Wow");
        details.setPrice(99.99);
        cpu.setDetails(details);
        for(int i = 0; i<7 ; i++) {
            items.add(cpu);
        }

        setItems(items);

        binding.checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckout();
            }
        });

        return binding.getRoot();

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

    public void setItems(List<Item> items) {
        adapter.updateData(items);
    }

}