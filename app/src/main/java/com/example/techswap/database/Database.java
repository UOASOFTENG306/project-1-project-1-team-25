package com.example.techswap.database;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;
import com.example.techswap.item.ItemFactory;
import com.example.techswap.user.User;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.*;

public class Database {

    private static final FirebaseFirestore database = FirebaseFirestore.getInstance();

    /**
     * Adds a new user / updates a user in the Firestore DB.
     */
    public static void addUser(User user, Boolean isNew) {

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
    public static void addNewCart(String username) {

        Map<String, Object> data = new HashMap<>();

        data.put("item_id", new ArrayList<String>());
        database.collection("cart").document(String.valueOf(username)).set(data);

    }

    /**
     * Updates the cart in the Firestore DB, by adding or removing item from cart.
     * boolean true if add, false if remove.
     */
    public static void addRemoveItemToCart(String username, String ItemDocName, boolean addOrRemove) {

        if (addOrRemove) {
            database.collection("cart").document(username).update("item_id", FieldValue.arrayUnion(ItemDocName));
        } else {
            database.collection("cart").document(username).update("item_id", FieldValue.arrayRemove(ItemDocName));
        }
    }

    /**
     * Clears the user's cart in the Firestore DB.
     */
    public static void clearCart(String username) {
        database.collection("cart").document(username).update("item_id", FieldValue.delete());
    }

    /**
     * Creates an item instance in the Firestore DB
     */
    public static void addItem(Item item) {
        Map<String, Object> data = new HashMap<>();
        Details details = item.getDetails();

        data.put("item_id", item.getId());
        data.put("category_id", details.getCategory());
        data.put("search_title", details.getTitle().toLowerCase());

        data.put("title", details.getTitle());
        data.put("subtitle", details.getSubtitle());

        data.put("description", details.getDescription());
        data.put("price", details.getPrice());
        data.put("images", item.getImageUrls());

        String[] stringArray = new String[]{"", "", "", "", ""};

        data.put("specifications", Arrays.asList(stringArray));
        data.put("specifications_id", Arrays.asList(stringArray));

        database.collection("items").document(String.valueOf(item.getId())).set(data);
    }

    @SuppressWarnings("unchecked")
    public static Item mapToItem(Map<String, Object> data) {
        ItemFactory itemFactory = new ItemFactory();
        Details details = new Details();

        String category, description, title, subtitle, searchTitle, id;
        double price;

        category = Objects.requireNonNull(data.get("category_id")).toString();
        price = Double.parseDouble(Objects.requireNonNull(data.get("price")).toString());
        description = Objects.requireNonNull(data.get("description")).toString();
        title = Objects.requireNonNull(data.get("title")).toString();
        subtitle = Objects.requireNonNull(data.get("subtitle")).toString();
        searchTitle = Objects.requireNonNull(data.get("search_title")).toString();
        id = Objects.requireNonNull(data.get("item_id")).toString();

        details.setCategory(category);
        details.setPrice(price);
        details.setDescription(description);
        details.setTitle(title);
        details.setSubtitle(subtitle);
        details.setSearchTitle(searchTitle);

        Item item = itemFactory.getItem(category);
        item.setDetails(details);
        item.setId(id);
        item.setImageUrls((List<String>) data.get("images"));
        item.setSpecifications((List<String>) data.get("specifications"));
        item.setSpecificationsTitleList((List<String>) data.get("specifications_id"));

        return item;
    }
}
