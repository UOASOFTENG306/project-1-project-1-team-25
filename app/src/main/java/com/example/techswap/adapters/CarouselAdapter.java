package com.example.techswap.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techswap.R;

import java.util.List;

public class CarouselAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Integer> imageList;
    private List<String> captionList;
    private List<Integer> priceList;
    private List<String> descriptionList;

    private static final int TYPE_WITHOUT_PRICE_OR_DESC = 0;
    private static final int TYPE_WITH_PRICE = 1;
    private static final int TYPE_WITH_DESC_AND_PRICE = 2;

    public CarouselAdapter(List<Integer> imageList, List<String> captionList, List<Integer> priceList, List<String> descriptionList) {
        this.imageList = imageList;
        this.captionList = captionList;
        this.descriptionList = descriptionList;
        this.priceList = priceList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_WITHOUT_PRICE_OR_DESC:
                View itemViewWithoutPriceOrDesc = inflater.inflate(R.layout.carousel_item_category, parent, false);
                return new CarouselViewHolderWithoutPriceOrDesc(itemViewWithoutPriceOrDesc);
            case TYPE_WITH_PRICE:
                View itemViewWithPrice = inflater.inflate(R.layout.carousel_item_deal, parent, false);
                return new CarouselViewHolderWithPrice(itemViewWithPrice);
            case TYPE_WITH_DESC_AND_PRICE:
                View itemViewWithDescAndPrice = inflater.inflate(R.layout.carousel_item_best_seller, parent, false);
                return new CarouselViewHolderWithDescAndPrice(itemViewWithDescAndPrice);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        int imageResId = imageList.get(position);

        if (viewType == TYPE_WITHOUT_PRICE_OR_DESC) {
            CarouselViewHolderWithoutPriceOrDesc viewHolder = (CarouselViewHolderWithoutPriceOrDesc) holder;
            viewHolder.carouselImage.setImageResource(imageResId);
            viewHolder.captionText.setText(captionList.get(position));
        } else if (viewType == TYPE_WITH_PRICE) {
            CarouselViewHolderWithPrice viewHolder = (CarouselViewHolderWithPrice) holder;
            viewHolder.carouselImage.setImageResource(imageResId);
            viewHolder.captionText.setText(captionList.get(position));
            viewHolder.priceText.setText("$" + priceList.get(position).toString());
        } else if (viewType == TYPE_WITH_DESC_AND_PRICE) {
            CarouselViewHolderWithDescAndPrice viewHolder = (CarouselViewHolderWithDescAndPrice) holder;
            viewHolder.carouselImage.setImageResource(imageResId);
            viewHolder.captionText.setText(captionList.get(position));
            viewHolder.additionalDataText.setText(descriptionList.get(position));
            viewHolder.priceText.setText("$" + priceList.get(position).toString());
        }
    }

    @Override
    public int getItemViewType(int position) {
        boolean hasPrice = priceList != null && priceList.size() > position;
        boolean hasDesc = descriptionList != null && descriptionList.size() > position;

        if (!hasPrice && !hasDesc) {
            return TYPE_WITHOUT_PRICE_OR_DESC;
        } else if (hasPrice && !hasDesc) {
            return TYPE_WITH_PRICE;
        } else if (hasPrice && hasDesc) {
            return TYPE_WITH_DESC_AND_PRICE;
        } else {
            throw new IllegalArgumentException("Invalid item type");
        }
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class CarouselViewHolderWithoutPriceOrDesc extends RecyclerView.ViewHolder {
        ImageView carouselImage;
        TextView captionText;

        public CarouselViewHolderWithoutPriceOrDesc(@NonNull View itemView) {
            super(itemView);
            carouselImage = itemView.findViewById(R.id.carouselImage);
            captionText = itemView.findViewById(R.id.captionText);
        }
    }

    public class CarouselViewHolderWithPrice extends RecyclerView.ViewHolder {
        ImageView carouselImage;
        TextView captionText;
        TextView priceText;

        public CarouselViewHolderWithPrice(@NonNull View itemView) {
            super(itemView);
            carouselImage = itemView.findViewById(R.id.carouselImage);
            captionText = itemView.findViewById(R.id.captionText);
            priceText = itemView.findViewById(R.id.priceText);
        }
    }

    public class CarouselViewHolderWithDescAndPrice extends RecyclerView.ViewHolder {
        ImageView carouselImage;
        TextView captionText;
        TextView additionalDataText;
        TextView priceText;

        public CarouselViewHolderWithDescAndPrice(@NonNull View itemView) {
            super(itemView);
            carouselImage = itemView.findViewById(R.id.carouselImage);
            captionText = itemView.findViewById(R.id.captionText);
            priceText = itemView.findViewById(R.id.priceText);
            additionalDataText = itemView.findViewById(R.id.descriptionText);
        }
    }
}
