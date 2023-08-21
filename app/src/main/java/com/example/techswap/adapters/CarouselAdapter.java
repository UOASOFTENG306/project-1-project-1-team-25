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
import com.example.techswap.item.Item;

import java.util.ArrayList;
import java.util.List;

public class CarouselAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Integer> imageList;
    private List<String> titleList;
    private List<Double> priceList;
    private List<String> subtitleList;

    private CarouselType carouselType;

    public enum CarouselType {
        CATEGORY, HORIZONTAL_ITEM, LIST_ITEM
    }

    public CarouselAdapter(CarouselType carouselType) {
        this.imageList = new ArrayList<>();
        this.titleList = new ArrayList<>();
        this.subtitleList = new ArrayList<>();
        this.priceList = new ArrayList<>();
        this.carouselType = carouselType;
    }

    public CarouselAdapter(List<Integer> imageList, List<String> titleList, List<Double> priceList, List<String> subtitleList, CarouselType carouselType) {
        this.imageList = imageList;
        this.titleList = titleList;
        this.subtitleList = subtitleList;
        this.priceList = priceList;
        this.carouselType = carouselType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (carouselType) {
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
        int imageResId = imageList.get(position);

        if (carouselType == CarouselType.CATEGORY) {
            CarouselViewHolderCategory viewHolder = (CarouselViewHolderCategory) holder;
            viewHolder.carouselImage.setImageResource(imageResId);
            viewHolder.titleText.setText(titleList.get(position));
        } else if (carouselType == CarouselType.HORIZONTAL_ITEM) {
            CarouselViewHolderHorizontalItem viewHolder = (CarouselViewHolderHorizontalItem) holder;
            viewHolder.carouselImage.setImageResource(imageResId);
            viewHolder.titleText.setText(titleList.get(position));
            viewHolder.priceText.setText("$" + priceList.get(position).toString());
        } else if (carouselType == CarouselType.LIST_ITEM) {
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
                if (carouselType == CarouselType.CATEGORY) {
                    ListFragment fragment = new ListFragment();
                    FragmentTransaction transaction = ((AppCompatActivity) v.getContext()).getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.mainFragmentContainer, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else if (carouselType == CarouselType.HORIZONTAL_ITEM || carouselType == CarouselType.LIST_ITEM) {
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

    public void updateData(List<Item> items) {
        List<String> titleList = new ArrayList<String>();
        List<String> subtitleList = new ArrayList<String>();
        List<Double> priceList = new ArrayList<Double>();
        List<Integer> imageList = new ArrayList<Integer>();

        for (Item item : items) {
            titleList.add(item.getDetails().getTitle());
            subtitleList.add(item.getDetails().getSubtitle());
            priceList.add(item.getDetails().getPrice());
            imageList.add(R.drawable.tempimg);
        }

        this.titleList = titleList;
        this.priceList = priceList;
        this.subtitleList = subtitleList;
        this.imageList = imageList;

        notifyDataSetChanged();
    }
}
