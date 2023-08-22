package com.example.techswap.database;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;
import com.example.techswap.user.User;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DatabaseSetter {

    private final FirebaseFirestore database;

    public DatabaseSetter() {
        database = FirebaseFirestore.getInstance();
    }

    /**
     * Adds a new user / updates a user in the Firestore DB.
     */
    public void addUser(User user, Boolean isNew) {

        if (isNew) {
            addNewCart(user.getUsername());
        }

        Map<String, Object> data = new HashMap<>();
        data.put("password", user.getPassword());
        data.put("username", user.getUsername());

        database.collection("users").document(String.valueOf(user.getUsername())).set(data);

    }

    /**
     * Creates a new cart in the Firestore DB. Different from adding items to the cart.
     */
    public void addNewCart(String username) {

        Map<String, Object> data = new HashMap<>();

        data.put("username", username);
        database.collection("cart").document(String.valueOf(username)).set(data);

    }

    /**
     * Updates the cart in the Firestore DB, by adding or removing item from cart.
     * boolean true if add, false if remove.
     */
    public void addRemoveItemToCart(String username, String ItemDocName, boolean addOrRemove) {

        Map<String, Object> data = new HashMap<>();
        data.put("username", username);

        if (addOrRemove) {
            database.collection("cart").document(username).update("item_id", FieldValue.arrayUnion(ItemDocName));
        } else {
            database.collection("cart").document(username).update("item_id", FieldValue.arrayRemove(ItemDocName));
        }
    }

    /**
     * Clears the user's cart in the Firestore DB.
     */
    public void clearCart(String username) {
        Map<String, Object> data = new HashMap<>();
        data.put("username", username);

        database.collection("cart").document(username).update("item_id", FieldValue.delete());
    }

    /**
     * Creates an item instance in the Firestore DB
     */
    public void addItem(Item item) {
        // TODO: Image uploads
        Map<String, Object> data = new HashMap<>();
        Details details = item.getDetails();

        data.put("item_id", item.getId());
        data.put("category_id", details.getCategory());
        data.put("search_title", details.getSearchTitle());

        data.put("title", details.getTitle());
        data.put("subtitle", details.getSubtitle());
        data.put("search_title", details.getTitle());

        data.put("description", details.getDescription());
        data.put("price", details.getPrice());
        data.put("quantity", details.getQuantity());
        data.put("images", item.getImageUrls());

//        Map<String, String> specifications = item.getSpecifications();

        database.collection("items").document(String.valueOf(item.getId())).set(data);
//        database.collection("items").document(String.valueOf(item.getId())).collection("specifications").document("specifications").set(specifications);
    }

    /**
     * Removes a specified item instance in the Firestore DB
     */
    public void removeItem(int id) {
        // TODO: Delete item from all carts containing it
        database.collection("items").document(String.valueOf(id)).delete();
    }

}