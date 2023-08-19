package com.example.techswap.database;

import com.example.techswap.user.User;
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
    public void addUser(String docName, User user, Boolean isNew) {

        // TODO: Check for duplicate username in database
//        if (isNew) {
//            addNewCart(docName, user.getId());
//        }

        Map<String, Object> data = new HashMap<>();
        data.put("password", user.getPassword());
        data.put("user_id", user.getId());
        data.put("username", user.getUsername());

        database.collection("users").document(docName).set(data);

    }

}