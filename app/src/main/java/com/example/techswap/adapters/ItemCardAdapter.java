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

public class ItemCardAdapter extends RecyclerView.Adapter<ItemCardAdapter.ItemCardViewHolder> {
    private List<Integer> imageList;
    private List<String> titleList;
    private List<String> subtitleList;
    private List<String> priceList;

    public ItemCardAdapter(List<Integer> imageList, List<String> titleList, List<String> subtitleList, List<String> priceList) {
        this.imageList = imageList;
        this.titleList = titleList;
        this.subtitleList = subtitleList;
        this.priceList = priceList;
    }

    @NonNull
    @Override
    public ItemCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new ItemCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCardViewHolder holder, int position) {
        int imageResId = imageList.get(position);
        holder.itemImage.setImageResource(imageResId);

        String title = titleList.get(position);
        holder.titleText.setText(title);

        String subtitle = titleList.get(position);
        holder.subtitleText.setText(subtitle);

        String price = titleList.get(position);
        holder.priceText.setText(price);
    }


    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ItemCardViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView titleText;
        TextView subtitleText;
        TextView priceText;

        public ItemCardViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            titleText = itemView.findViewById(R.id.itemTitle);
            subtitleText = itemView.findViewById(R.id.itemSubtitle);
            priceText = itemView.findViewById(R.id.itemPrice);
        }
    }
}