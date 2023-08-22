package com.example.techswap.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.techswap.R;

import java.util.List;

public class SellImageAdapter extends RecyclerView.Adapter<SellImageAdapter.ImageViewHolder> {
    private List<String> imageUrlList;

    private Context context;

    public SellImageAdapter(Context context, List<String> imageUrlList) {
        this.context = context;
        this.imageUrlList = imageUrlList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sell_image, parent, false);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        String url = imageUrlList.get(position);

        Glide.with(context).load(url).into(holder.image);
    }


    @Override
    public int getItemCount() {
        if (imageUrlList == null) {
            return 0;
        }
        return imageUrlList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void updateImages(List<String> imageUrlList) {
        this.imageUrlList = imageUrlList;
        Log.println(Log.INFO,"tag",imageUrlList.toString());
        notifyDataSetChanged();
    }
}