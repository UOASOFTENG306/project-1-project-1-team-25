package com.example.techswap.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.example.techswap.R;
import com.example.techswap.interfaces.IDetailsImageAdapter;

import java.util.List;

public class DetailsImageAdapter extends ImageAdapter implements IDetailsImageAdapter {

    public DetailsImageAdapter(Context context, List<String> imageUrlList) {
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
                .inflate(R.layout.adapter_image, parent, false);
        return new ImageViewHolder(itemView);
    }

}