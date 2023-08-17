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

    private static final int TYPE_WITHOUT_PRICE = 0;
    private static final int TYPE_WITH_PRICE = 1;

    public CarouselAdapter(List<Integer> imageList, List<String> captionList, List<Integer> priceList) {
        this.imageList = imageList;
        this.captionList = captionList;
        this.priceList = priceList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_WITHOUT_PRICE) {
            View itemView = inflater.inflate(R.layout.carousel_item_category, parent, false);
            return new CarouselViewHolder(itemView);
        } else {
            View itemView = inflater.inflate(R.layout.carousel_item_deal, parent, false);
            return new CarouselViewHolderWithPrice(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        int imageResId = imageList.get(position);

        if (viewType == TYPE_WITHOUT_PRICE) {
            CarouselViewHolder carouselViewHolder = (CarouselViewHolder) holder;

            carouselViewHolder.carouselImage.setImageResource(imageResId);
            carouselViewHolder.captionText.setText(captionList.get(position));
        } else {
            CarouselViewHolderWithPrice carouselViewHolder = (CarouselViewHolderWithPrice) holder;

            carouselViewHolder.carouselImage.setImageResource(imageResId);
            carouselViewHolder.captionText.setText(captionList.get(position));
            carouselViewHolder.priceText.setText("$" + priceList.get(position).toString());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return priceList != null ? TYPE_WITH_PRICE : TYPE_WITHOUT_PRICE;
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class CarouselViewHolder extends RecyclerView.ViewHolder {
        ImageView carouselImage;
        TextView captionText;

        public CarouselViewHolder(@NonNull View itemView) {
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
            priceText = itemView.findViewById((R.id.priceText));
        }
    }
}
