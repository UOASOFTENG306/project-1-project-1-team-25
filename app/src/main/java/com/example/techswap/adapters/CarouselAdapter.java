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

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder> {
    private List<Integer> imageList;
    private List<String> captionList;

    public CarouselAdapter(List<Integer> imageList, List<String> captionList) {
        this.imageList = imageList;
        this.captionList = captionList;
    }

    @NonNull
    @Override
    public CarouselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carousel_item_category, parent, false);
        return new CarouselViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CarouselViewHolder holder, int position) {
        int imageResId = imageList.get(position);
        holder.carouselImage.setImageResource(imageResId);

        String caption = captionList.get(position); // Replace with your data source
        holder.captionText.setText(caption);
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
}