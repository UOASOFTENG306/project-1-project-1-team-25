package com.example.techswap.item;

import java.util.HashSet;

public class Cart {

    private HashSet<Item> contents;

    public HashSet<Item> getContents() {
        return contents;
    }

    public void setContents(HashSet<Item> contents) {
        this.contents = contents;
    }

    public void addItemToCart(Item item) {
        contents.add(item);
    }

    public void removeFromCart(Item item) {
        contents.remove(item);
    }

    public void clearCart() {
        contents.clear();
    }
}
