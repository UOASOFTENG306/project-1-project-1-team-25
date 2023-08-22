package com.example.techswap.item;

import java.util.ArrayList;

public class Cart {

    private ArrayList<String> items;

    public Cart() {}

    public ArrayList<String> getItems() {
        return items;
    }

    public void setItems(ArrayList<String> contents) {
        this.items = items;
    }

    public void addItemToCart(Item item) {
        items.add(item.getId().toString());
    }

    public void removeFromCart(Item item) {
        items.remove(item.getId().toString());
    }

    public void clearCart() {
        items.clear();
    }
}
