package com.example.techswap.interfaces;

import android.content.Context;
import com.example.techswap.item.Item;

import java.util.List;

/**
 * The `ICarouselAdapter` interface defines methods for updating data and setting context in a carousel adapter.
 * Implementations of this interface are intended to provide functionality for managing data in a carousel display.
 */
public interface ICarouselAdapter {

    /**
     * Updates the data in the carousel adapter with a new list of items.
     *
     * @param items The list of items to update the carousel adapter with.
     */
    void updateData(List<Item> items);

    /**
     * Sets the context for the carousel adapter.
     *
     * @param context The context to be set for the carousel adapter.
     */
    void setContext(Context context);
}
