package com.example.techswap.database;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;
import com.example.techswap.user.User;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
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
    public void addUser(String docName, User user, Boolean isNew) {

        // TODO: Check for duplicate username in database
        if (isNew) {
            addNewCart(docName, user.getId());
        }

        Map<String, Object> data = new HashMap<>();
        data.put("password", user.getPassword());
        data.put("user_id", user.getId());
        data.put("username", user.getUsername());

        database.collection("users").document(docName).set(data);

    }

    /**
     * Creates a new cart in the Firestore DB, upon new user creation. Different from adding items to the cart.
     */
    public void addNewCart(String docName, int id) {

        Map<String, Object> data = new HashMap<>();

        data.put("item_id", new ArrayList<Integer>());
        data.put("user_id", id);

        database.collection("cart").document(docName).set(data);

    }

    /**
     * Creates an item instance in the Firestore DB
     */
    public void addItem(String docName, Item item) {
        // TODO: Image upload
        Map<String, Object> data = new HashMap<>();
        Details details = item.getDetails();

        data.put("item_id", item.getId());
        data.put("category_id", details.getCategory());

        data.put("title", details.getTitle());
        data.put("subtitle", details.getSubtitle());

        data.put("description", details.getDescription());
        data.put("price", details.getPrice());
        data.put("quantity", details.getQuantity());

        Map<String, String> specifications = item.getSpecifications();

        database.collection("items").document(docName).set(data);
        database.collection("items").document(docName).collection("specifications").document("specifications").set(specifications);
    }

}