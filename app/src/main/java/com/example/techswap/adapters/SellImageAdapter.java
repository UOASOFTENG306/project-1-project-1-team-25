package com.example.techswap.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.example.techswap.R;
import com.example.techswap.interfaces.ISellImageAdapter;

import java.util.List;

public class SellImageAdapter extends ImageAdapter implements ISellImageAdapter {

    /**
     * Constructor for the SellImageAdapter class.
     * Initializes the adapter with the provided context and a list of image URLs.
     *
     * @param context The context associated with the adapter.
     * @param imageUrlList The list of image URLs to be displayed in the adapter.
     */
    public SellImageAdapter(Context context, List<String> imageUrlList) {
        super(context, imageUrlList);
    }

    /**
     * Called when RecyclerView needs a new {@link ImageViewHolder} of the given type to represent an item.
     * This method inflates the layout for the item view and creates a new {@link ImageViewHolder} instance
     * to hold the views within the item. It returns the created {@link ImageViewHolder} to be used for
     * displaying and managing the data of the item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new {@link ImageViewHolder} that holds an item view of the given view type.
     */
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_image_selling, parent, false);
        return new ImageViewHolder(itemView);
    }

    /**
     * Updates the list of image URLs and notifies the adapter of the data change.
     * This method sets the new list of image URLs for the adapter and calls {@link #notifyDataSetChanged()}
     * to inform the RecyclerView that the data has been updated and needs to be reloaded.
     *
     * @param imageUrlList The updated list of image URLs to be set for the adapter.
     */
    @SuppressLint("NotifyDataSetChanged")
    public void updateImages(List<String> imageUrlList) {
        super.setImageUrlList(imageUrlList);
        notifyDataSetChanged();
    }
}