package com.example.techswap.item;

import java.util.ArrayList;

public class Cart {

    private ArrayList<String> contents;

    public Cart() {}

    public ArrayList<String> getContents() {
        return contents;
    }

    public void setContents(ArrayList<String> contents) {
        this.contents = contents;
    }

    public void addItemToCart(Item item) {
        contents.add(item.getId().toString());
    }

    public void removeFromCart(Item item) {
        contents.remove(item.getId().toString());
    }

    public void clearCart() {
        contents.clear();
    }
}
