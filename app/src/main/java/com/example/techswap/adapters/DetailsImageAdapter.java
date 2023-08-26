package com.example.techswap.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.techswap.R;
import com.example.techswap.interfaces.IDetailsImageAdapter;

import java.util.List;

public class DetailsImageAdapter extends ImageAdapter implements IDetailsImageAdapter {

    public DetailsImageAdapter(Context context, List<String> imageUrlList) {
        super(context, imageUrlList);
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_image, parent, false);
        return new ImageViewHolder(itemView);
    }

}