package com.example.techswap.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.techswap.R;
import com.example.techswap.interfaces.ISellImageAdapter;

import java.util.List;

public class SellImageAdapter extends ImageAdapter implements ISellImageAdapter {

    public SellImageAdapter(Context context, List<String> imageUrlList) {
        super(context, imageUrlList);
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_image_selling, parent, false);
        return new ImageViewHolder(itemView);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateImages(List<String> imageUrlList) {
        super.setImageUrlList(imageUrlList);
        notifyDataSetChanged();
    }
}