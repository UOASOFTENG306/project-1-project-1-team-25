package com.example.techswap.interfaces;

import com.example.techswap.item.Item;

/**
 * The `IDatabase` interface defines methods for interacting with a database, specifically tailored for a Firestore database.
 * Implementations of this interface provide functionality for managing users, carts, items, and their interactions in the database.
 */
public interface IDatabase {

    /**
     * Adds a new user to the database with the provided username and password.
     *
     * @param username The username of the user to be added.
     * @param password The password associated with the user's account.
     */
    public void addUser(String username, String password);

    /**
     * Creates a new cart for a user in the Firestore database.
     * Note: This action is distinct from adding items to the cart.
     *
     * @param username The username of the user for whom to create a new cart.
     */
    public void addNewCart(String username);

    /**
     * Updates the user's cart in the Firestore database by adding or removing an item from the cart.
     *
     * @param ItemDocName The document name of the item in the Firestore database.
     * @param addOrRemove A boolean indicating whether to add (true) or remove (false) the item from the cart.
     */
    public void addRemoveItemToCart(String ItemDocName, boolean addOrRemove);

    /**
     * Clears the user's cart in the Firestore database.
     *
     * @param username The username of the user whose cart is to be cleared.
     */
    public void clearCart(String username);

    /**
     * Creates an instance of an item in the Firestore database.
     *
     * @param item The item instance to be added to the database.
     */
    public void addItem(Item item);
}
