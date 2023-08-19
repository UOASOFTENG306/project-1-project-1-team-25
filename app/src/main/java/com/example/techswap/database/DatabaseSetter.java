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

    public void addUser(String id, User user) {
        Map<String, Object> data = new HashMap<>();
        data.put("user_id", user.getId());
        data.put("username", user.getUsername());
        data.put("password", user.getPassword());
        database.collection("users").document(id).set(data);
    }
}