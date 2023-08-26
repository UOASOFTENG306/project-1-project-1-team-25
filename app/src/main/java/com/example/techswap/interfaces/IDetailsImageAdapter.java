package com.example.techswap.interfaces;

import android.content.Context;

import java.util.List;

/**
 * The `IDetailsImageAdapter` interface defines methods for managing image data and context in an image adapter
 * specifically tailored for displaying details of an item.
 */
public interface IDetailsImageAdapter {

    /**
     * Returns the number of images available in the adapter.
     *
     * @return The number of images.
     */
    int getItemCount();

    /**
     * Sets the context for the image adapter.
     *
     * @param context The context to be set for the image adapter.
     */
    void setContext(Context context);

}
