package com.example.techswap.fragments;

import static android.content.ContentValues.TAG;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.techswap.MainActivity;
import com.example.techswap.R;
import com.example.techswap.adapters.ImageAdapter;
import com.example.techswap.adapters.SpecificationAdapter;
import com.example.techswap.database.DatabaseSetter;
import com.example.techswap.database.DatabaseUtils;
import com.example.techswap.databinding.FragmentDetailsBinding;
import com.example.techswap.item.Item;
import com.example.techswap.user.User;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;

    ViewPager2 viewPager;
    LinearLayout sliderDotspanel;
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
            LayoutInflater inflater, ViewGroup container,
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
            binding.detailsPrice.setText("$" + item.getDetails().getPrice());
            binding.detailsDescription.setText(item.getDetails().getDescription());

            // specifications recycler view
            RecyclerView recyclerView = binding.specificationRecyclerView;
            LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);

            List<String> specificationList = Arrays.asList(
                    "Brand",
                    "Clock speed"
            );

            List<String> valueList = Arrays.asList(
                    "Intel",
                    "Like, a bajillion"
            );

            SpecificationAdapter adapter = new SpecificationAdapter(specificationList, valueList);
            recyclerView.setAdapter(adapter);
        }

        // view pager
        viewPager = binding.detailsPager;

        List<String> imageList = Arrays.asList(
                "https://firebasestorage.googleapis.com/v0/b/techswap-e2b95.appspot.com/o/images%2Fc184b8c3-3361-4272-8f68-d56f8c72c4a1?alt=media&token=7a716214-fb89-4e21-879d-90c2a4f37d90",
                "https://firebasestorage.googleapis.com/v0/b/techswap-e2b95.appspot.com/o/images%2Fc184b8c3-3361-4272-8f68-d56f8c72c4a1?alt=media&token=7a716214-fb89-4e21-879d-90c2a4f37d90",
                "https://firebasestorage.googleapis.com/v0/b/techswap-e2b95.appspot.com/o/images%2Fc184b8c3-3361-4272-8f68-d56f8c72c4a1?alt=media&token=7a716214-fb89-4e21-879d-90c2a4f37d90",
                "https://firebasestorage.googleapis.com/v0/b/techswap-e2b95.appspot.com/o/images%2Fc184b8c3-3361-4272-8f68-d56f8c72c4a1?alt=media&token=7a716214-fb89-4e21-879d-90c2a4f37d90",
                "https://firebasestorage.googleapis.com/v0/b/techswap-e2b95.appspot.com/o/images%2Fc184b8c3-3361-4272-8f68-d56f8c72c4a1?alt=media&token=7a716214-fb89-4e21-879d-90c2a4f37d90",
                "https://firebasestorage.googleapis.com/v0/b/techswap-e2b95.appspot.com/o/images%2Fc184b8c3-3361-4272-8f68-d56f8c72c4a1?alt=media&token=7a716214-fb89-4e21-879d-90c2a4f37d90",
                "https://firebasestorage.googleapis.com/v0/b/techswap-e2b95.appspot.com/o/images%2Fc184b8c3-3361-4272-8f68-d56f8c72c4a1?alt=media&token=7a716214-fb89-4e21-879d-90c2a4f37d90"
        );

        ImageAdapter adapterView = new ImageAdapter(requireContext(), imageList);
        viewPager.setAdapter(adapterView);

        // view pager dots
        sliderDotspanel = binding.sliderDotsPanel;
        dotscount = adapterView.getItemCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(requireContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.inactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.active_dot));

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.inactive_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        if (User.getCurrentUser() != null) {
            binding.addToCartButton.setVisibility(VISIBLE);
        }

        binding.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddToCart();
            }
        });

        return binding.getRoot();

    }

    private void onAddToCart() {
        DatabaseSetter db = new DatabaseSetter();
        db.addRemoveItemToCart(User.getCurrentUser().getUsername(), item.getId().toString(), true);

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