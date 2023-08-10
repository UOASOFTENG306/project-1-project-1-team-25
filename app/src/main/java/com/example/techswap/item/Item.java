package com.example.techswap.item;

import android.media.Image;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Item {

    private Long id;

    private Details details;

    private Set<Image> images = new HashSet<>();

    protected Item() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Image> getImages() {
        // Wrap the Set of Image objects with a wrapper that provides read-only
        // access. Clients thus can't change the state of the returned Set.
        return Collections.unmodifiableSet(images);
    }

    public void addImage(Image image) {
        images.add(image);
    }

    public void removeImage(Image image) {
        images.remove(image);
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

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
