package com.example.techswap.interfaces;

import com.example.techswap.item.Item;

public interface IDatabase {

    public void addUser(String username, String password);

    /**
     * Creates a new cart in the Firestore DB. Different from adding items to the cart.
     */
    public void addNewCart(String username);

    /**
     * Updates the cart in the Firestore DB, by adding or removing item from cart.
     * boolean true if add, false if remove.
     */
    public void addRemoveItemToCart(String ItemDocName, boolean addOrRemove);

    /**
     * Clears the user's cart in the Firestore DB.
     */
    public void clearCart(String username);

    /**
     * Creates an item instance in the Firestore DB
     */
    public void addItem(Item item);
}
