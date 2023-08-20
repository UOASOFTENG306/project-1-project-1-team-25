package com.example.techswap.database;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.techswap.user.User;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseFetcher {

    private final CollectionReference itemCollection = FirebaseFirestore.getInstance().collection("items");
    private final CollectionReference userCollection = FirebaseFirestore.getInstance().collection("users");
    private final CollectionReference cartCollection = FirebaseFirestore.getInstance().collection("cart");
    private List<Map<String, Object>> itemList = new ArrayList<>();
    private Map<String, Object> userData = new HashMap<>();
    private Map<String, Object> cartData = new HashMap<>();

    /**
     * Returns a map of the requested document data.
     * Returned object: List of Maps, each map representing one object.
     */
    public void fetchItemsByCategory(String category, int limit){
        itemCollection
                .whereEqualTo("category_id", category).orderBy("title").limit(limit)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            itemList.add(document.getData());
                        }
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }

    /**
     * Returns the requested user data
     * Returned object: Map, representing user.
     */
    public void fetchUserData(User user){
        userCollection.document(String.valueOf(user.getId())).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    userData = document.getData();
                } else {
                    Log.d(TAG, "User not found");
                }
            } else {
                Log.d(TAG, "get failed with ", task.getException());
            }
        });
    }

    /**
     * Returns the requested user data
     * Returned object: Map, representing cart
     */
    public void fetchCartData(String id){
        cartCollection.document(id).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    cartData = document.getData();
                } else {
                    Log.d(TAG, "Cart not found");
                }
            } else {
                Log.d(TAG, "get failed with ", task.getException());
            }
        });
    }

}
