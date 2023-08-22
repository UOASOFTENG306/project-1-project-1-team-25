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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.techswap.R;
import com.example.techswap.adapters.ImageAdapter;
import com.example.techswap.adapters.SpecificationAdapter;
import com.example.techswap.databinding.FragmentDetailsBinding;
import com.example.techswap.item.Item;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;

    ViewPager2 viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;

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

        List<Integer> imageList = Arrays.asList(
                R.drawable.cpu,
                R.drawable.gpu,
                R.drawable.motherboard,
                R.drawable.harddisk,
                R.drawable.ram,
                R.drawable.powersupply,
                R.drawable.pccase
        );

        ImageAdapter adapterView = new ImageAdapter(imageList);
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

        return binding.getRoot();

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