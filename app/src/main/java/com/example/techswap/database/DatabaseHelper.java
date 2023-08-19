package com.example.techswap.database;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.techswap.user.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper {

    private final FirebaseFirestore database;

    public DatabaseHelper() {
        database = FirebaseFirestore.getInstance();
    }

    public void addUser(String id, User user) {
        Map<String, Object> data = new HashMap<>();
        data.put("user_id", user.getId());
        data.put("username", user.getUsername());
        data.put("password", user.getPassword());
        database.collection("users").document(id).set(data);
    }

    public void getUser(String userId) {
        DocumentReference docRef = database.collection("users").document(userId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
}