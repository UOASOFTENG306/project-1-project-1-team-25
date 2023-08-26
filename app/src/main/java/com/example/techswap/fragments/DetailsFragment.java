package com.example.techswap.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.example.techswap.R;
import com.example.techswap.adapters.DetailsImageAdapter;
import com.example.techswap.adapters.SpecificationAdapter;
import com.example.techswap.database.Database;
import com.example.techswap.databinding.FragmentDetailsBinding;
import com.example.techswap.interfaces.IDatabase;
import com.example.techswap.interfaces.IDetailsImageAdapter;
import com.example.techswap.item.Item;
import com.example.techswap.user.User;

import java.text.DecimalFormat;
import java.util.List;

public class DetailsFragment extends Fragment {

    IDatabase db = new Database();
    IDetailsImageAdapter adapter;
    ViewPager2 viewPager;
    LinearLayout sliderDotspanel;
    private FragmentDetailsBinding binding;
    private int dotscount;
    private ImageView[] dots;
    private Item item;

    public static DetailsFragment newInstance(Item item) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("item", item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentDetailsBinding.inflate(inflater, container, false);

        Bundle args = getArguments();
        if (args != null) {
            Item item = (Item) args.getSerializable("item");
            this.item = item;

            // set field text
            binding.detailsTitle.setText(item.getDetails().getTitle());
            binding.detailsSubtitle.setText(item.getDetails().getSubtitle());

            DecimalFormat df = new DecimalFormat("0.00");
            df.setMaximumFractionDigits(2);
            String price = "$" + df.format(item.getDetails().getPrice());

            binding.detailsPrice.setText(price);
            binding.detailsDescription.setText(item.getDetails().getDescription());

            // specifications recycler view
            RecyclerView recyclerView = binding.specificationRecyclerView;
            LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);

            List<String> specificationList = item.getSpecificationsTitleList();
            List<String> valueList = item.getSpecifications();

            SpecificationAdapter adapter = new SpecificationAdapter(specificationList, valueList);
            recyclerView.setAdapter(adapter);

            if (adapter.getItemCount() == 0) {
                binding.detailsSpecificationsHeader.setVisibility(View.GONE);
            } else {
                binding.detailsSpecificationsHeader.setVisibility(View.VISIBLE);
            }

        }

        // view pager
        viewPager = binding.detailsPager;
        adapter = new DetailsImageAdapter(requireContext(), item.getImageUrls());
        viewPager.setAdapter((RecyclerView.Adapter<?>) adapter);

        // view pager dots
        sliderDotspanel = binding.sliderDotsPanel;
        dotscount = adapter.getItemCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(requireContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.inactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.active_dot));

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.inactive_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.active_dot));
            }

        });

        if (User.getCurrentUser() != null) {
            binding.addToCartButton.setEnabled(true);
            binding.addToCartButton.setBackgroundResource(R.drawable.rounded_button);
            binding.addToCartButton.setText(R.string.add_to_cart);
            binding.addToCartButton.setTextSize(16);
        } else {
            binding.addToCartButton.setEnabled(false);
            binding.addToCartButton.setBackgroundResource(R.drawable.rounded_button_grey);
            binding.addToCartButton.setText(R.string.sign_in_to_purchase);
            binding.addToCartButton.setTextSize(14);
        }

        binding.addToCartButton.setOnClickListener(v -> onAddToCart());

        return binding.getRoot();

    }

    private void onAddToCart() {
        db.addRemoveItemToCart(item.getId(), true);

        CartFragment fragment = new CartFragment();
        FragmentTransaction transaction = (getParentFragmentManager().beginTransaction());

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        transaction.replace(R.id.mainFragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}