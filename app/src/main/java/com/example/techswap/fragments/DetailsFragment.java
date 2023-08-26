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
import com.example.techswap.interfaces.ISpecificationAdapter;
import com.example.techswap.item.Item;
import com.example.techswap.user.User;

import java.text.DecimalFormat;
import java.util.List;

public class DetailsFragment extends Fragment {

    IDatabase db = new Database();
    IDetailsImageAdapter imageAdapter;
    ISpecificationAdapter specificationAdapter;
    ViewPager2 viewPager;
    LinearLayout sliderDotsPanel;
    private FragmentDetailsBinding binding;
    private int dotsCount;
    private ImageView[] dots;
    private Item item;

    public static DetailsFragment newInstance(Item item) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("item", item);
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

            specificationAdapter = new SpecificationAdapter(specificationList, valueList);
            recyclerView.setAdapter((RecyclerView.Adapter<?>) specificationAdapter);

            if (specificationAdapter.getItemCount() == 0) {
                binding.detailsSpecificationsHeader.setVisibility(View.GONE);
            } else {
                binding.detailsSpecificationsHeader.setVisibility(View.VISIBLE);
            }

        }

        // view pager
        viewPager = binding.detailsPager;
        imageAdapter = new DetailsImageAdapter(requireContext(), item.getImageUrls());
        viewPager.setAdapter((RecyclerView.Adapter<?>) imageAdapter);

        // view pager dots
        sliderDotsPanel = binding.sliderDotsPanel;
        dotsCount = imageAdapter.getItemCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {

            dots[i] = new ImageView(requireContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.inactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotsPanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.active_dot));

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotsCount; i++) {
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

    /**
     * Adds the current item to the user's cart and navigates to the CartFragment.
     * This method updates the cart database, replaces the current fragment with the CartFragment,
     * and adds the transaction to the fragment back stack.
     */
    private void onAddToCart() {
        db.addRemoveItemToCart(item.getId(), true);

        CartFragment fragment = new CartFragment();
        FragmentTransaction transaction = (getParentFragmentManager().beginTransaction());

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        transaction.replace(R.id.mainFragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * Called when the fragment's view has been created and is ready to be populated with UI elements.
     *
     * @param view           The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
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

}