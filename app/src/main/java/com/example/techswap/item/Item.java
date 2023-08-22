package com.example.techswap.item;

import android.media.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class Item implements Serializable {

    private Long id;

    private Details details;

    private List<String> imageUrls = new ArrayList<>();

    protected Item() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getImageUrls() {
        return this.imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public abstract HashMap<String,String> getSpecifications();

    public abstract void setSpecifications(HashMap<String,String> specifications);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, details.getTitle());
    }

    @Override
    public String toString() {
        return "Item " + id + ": " + details.getTitle() + " hash: " + hashCode();
    }

}
