package com.example.techswap.database;

import com.example.techswap.user.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreHelper {

    private final FirebaseFirestore database;

    public FirestoreHelper() {
        database = FirebaseFirestore.getInstance();
    }

    public Task<Void> addUser(String userId, User user) {
        DocumentReference docRef = database.collection("users").document(userId);
        return docRef.set(user);
    }
}