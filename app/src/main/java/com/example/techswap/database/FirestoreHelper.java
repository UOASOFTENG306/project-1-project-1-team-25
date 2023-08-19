package com.example.techswap.database;

import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreHelper {

    private final FirebaseFirestore database;

    public FirestoreHelper() {
        database = FirebaseFirestore.getInstance();
    }
}