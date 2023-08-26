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

import java.util.List;

public abstract class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private List<String> imageUrlList;
    private Context context;

    /**
     * Constructor for the ImageAdapter class.
     * Initializes the adapter with the provided context and a list of image URLs.
     *
     * @param context The context associated with the adapter.
     * @param imageUrlList The list of image URLs to be displayed in the adapter.
     */
    public ImageAdapter(Context context, List<String> imageUrlList) {
        this.context = context;
        this.imageUrlList = imageUrlList;
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
     * Called by RecyclerView to display the data at the specified position.
     * Binds the image URL at the given position to the provided {@link ImageViewHolder},
     * using the Glide library to load and display the image.
     *
     * @param holder The {@link ImageViewHolder} to bind the data to.
     * @param position The position of the item within the data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        String url = imageUrlList.get(position);

        Glide.with(context).load(url).into(holder.image);
    }

    /**
     * Returns the total number of image URLs in the adapter's data set.
     *
     * @return The total number of image URLs in the data set, or 0 if the list is null.
     */
    @Override
    public int getItemCount() {
        if (imageUrlList == null) {
            return 0;
        }
        return imageUrlList.size();
    }

    /**
     * Sets the context for the adapter.
     *
     * @param context The context to be set for the adapter.
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Sets the list of image URLs for the adapter.
     *
     * @param imageUrlList The list of image URLs to be set for the adapter.
     */
    public void setImageUrlList(List<String> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        final ImageView image;

        /**
         * Constructor for the ViewHolder used to hold image views.
         * Initializes the ViewHolder with the provided item view and associates the image view
         * with the corresponding layout view to be used for displaying images.
         *
         * @param itemView The view representing an individual item in the RecyclerView.
         */
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }
    }
}