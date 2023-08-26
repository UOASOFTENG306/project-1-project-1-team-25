package com.example.techswap.item;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Item implements Serializable {

    private String id;

    protected Details details = new Details();

    private List<String> imageUrls = new ArrayList<>();

    private List<String> specificationsTitleList = new ArrayList<>();

    protected Item() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getImageUrls() {
        if (this.imageUrls == null || this.imageUrls.size() == 0) {
            List<String> urls = new ArrayList<>();
            urls.add("https://upload.wikimedia.org/wikipedia/commons/8/89/HD_transparent_picture.png");
            return urls;
        }
        return this.imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getFirstImageUrl() {
        if (this.imageUrls == null || this.imageUrls.size() == 0) {
            return "https://upload.wikimedia.org/wikipedia/commons/8/89/HD_transparent_picture.png"; // TODO: add resource for URL
        }
        return this.imageUrls.get(0);
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public abstract List<String> getSpecifications();

    public abstract void setSpecifications(List<String> valuesList);

    public List<String> getSpecificationsTitleList() {
        return specificationsTitleList;
    }

    public void setSpecificationsTitleList(List<String> specificationsTitleList) {
        this.specificationsTitleList = specificationsTitleList;
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

    @NonNull
    @Override
    public String toString() {
        return "Item " + id + ": " + details.getTitle() + " hash: " + hashCode();
    }

}
