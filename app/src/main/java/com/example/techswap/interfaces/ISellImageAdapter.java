package com.example.techswap.interfaces;

import android.content.Context;

import java.util.List;

/**
 * The `ISellImageAdapter` interface defines methods for managing image data and context in an image adapter
 * specifically designed for displaying images during the item selling process.
 */
public interface ISellImageAdapter {

    /**
     * Sets the context for the image adapter.
     *
     * @param context The context to be set for the image adapter.
     */
    void setContext(Context context);

    /**
     * Updates the list of image URLs in the adapter.
     *
     * @param imageUrlList The list of image URLs to update in the image adapter.
     */
    void updateImages(List<String> imageUrlList);

}
