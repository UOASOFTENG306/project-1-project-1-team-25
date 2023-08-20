package com.example.techswap.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techswap.fragments.DetailsFragment;
import com.example.techswap.fragments.ListFragment;
import com.example.techswap.R;

import java.util.List;

public class CarouselAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Integer> imageList;
    private List<String> titleList;
    private List<Integer> priceList;
    private List<String> subtitleList;

    private static final int CATEGORY = 0;
    private static final int HORIZONTAL_ITEM = 1;
    private static final int LIST_ITEM = 2;

    public CarouselAdapter(List<Integer> imageList, List<String> titleList, List<Integer> priceList, List<String> subtitleList) {
        this.imageList = imageList;
        this.titleList = titleList;
        this.subtitleList = subtitleList;
        this.priceList = priceList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case CATEGORY:
                View itemViewWithoutPriceOrDesc = inflater.inflate(R.layout.carousel_item_category, parent, false);
                return new CarouselViewHolderCategory(itemViewWithoutPriceOrDesc);
            case HORIZONTAL_ITEM:
                View itemViewWithPrice = inflater.inflate(R.layout.carousel_item_deal, parent, false);
                return new CarouselViewHolderHorizontalItem(itemViewWithPrice);
            case LIST_ITEM:
                View itemViewWithDescAndPrice = inflater.inflate(R.layout.carousel_item_best_seller, parent, false);
                return new CarouselViewHolderListItem(itemViewWithDescAndPrice);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        int imageResId = imageList.get(position);

        if (viewType == CATEGORY) {
            CarouselViewHolderCategory viewHolder = (CarouselViewHolderCategory) holder;
            viewHolder.carouselImage.setImageResource(imageResId);
            viewHolder.titleText.setText(titleList.get(position));
        } else if (viewType == HORIZONTAL_ITEM) {
            CarouselViewHolderHorizontalItem viewHolder = (CarouselViewHolderHorizontalItem) holder;
            viewHolder.carouselImage.setImageResource(imageResId);
            viewHolder.titleText.setText(titleList.get(position));
            viewHolder.priceText.setText("$" + priceList.get(position).toString());
        } else if (viewType == LIST_ITEM) {
            CarouselViewHolderListItem viewHolder = (CarouselViewHolderListItem) holder;
            viewHolder.carouselImage.setImageResource(imageResId);
            viewHolder.titleText.setText(titleList.get(position));
            viewHolder.subtitleText.setText(subtitleList.get(position));
            viewHolder.priceText.setText("$" + priceList.get(position).toString());
        }

        // Set OnClickListener for the item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click event
                int viewType = getItemViewType(position);
                if (viewType == CATEGORY) {
                    ListFragment fragment = new ListFragment();
                    FragmentTransaction transaction = ((AppCompatActivity) v.getContext()).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.mainFragmentContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else if (viewType == HORIZONTAL_ITEM || viewType == LIST_ITEM) {
                    DetailsFragment fragment = new DetailsFragment();
                    FragmentTransaction transaction = ((AppCompatActivity) v.getContext()).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.mainFragmentContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        boolean hasPrice = priceList != null && priceList.size() > position;
        boolean hasDesc = subtitleList != null && subtitleList.size() > position;

        if (!hasPrice && !hasDesc) {
            return CATEGORY;
        } else if (hasPrice && !hasDesc) {
            return HORIZONTAL_ITEM;
        } else if (hasPrice && hasDesc) {
            return LIST_ITEM;
        } else {
            throw new IllegalArgumentException("Invalid item type");
        }
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class CarouselViewHolderCategory extends RecyclerView.ViewHolder {
        ImageView carouselImage;
        TextView titleText;

        public CarouselViewHolderCategory(@NonNull View itemView) {
            super(itemView);
            carouselImage = itemView.findViewById(R.id.itemImage);
            titleText = itemView.findViewById(R.id.itemTitle);
        }
    }

    public class CarouselViewHolderHorizontalItem extends RecyclerView.ViewHolder {
        ImageView carouselImage;
        TextView titleText;
        TextView priceText;

        public CarouselViewHolderHorizontalItem(@NonNull View itemView) {
            super(itemView);
            carouselImage = itemView.findViewById(R.id.carouselImage);
            titleText = itemView.findViewById(R.id.captionText);
            priceText = itemView.findViewById(R.id.priceText);
        }
    }

    public class CarouselViewHolderListItem extends RecyclerView.ViewHolder {
        ImageView carouselImage;
        TextView titleText;
        TextView subtitleText;
        TextView priceText;

        public CarouselViewHolderListItem(@NonNull View itemView) {
            super(itemView);
            carouselImage = itemView.findViewById(R.id.carouselImage);
            titleText = itemView.findViewById(R.id.captionText);
            priceText = itemView.findViewById(R.id.priceText);
            subtitleText = itemView.findViewById(R.id.descriptionText);
        }
    }
}
