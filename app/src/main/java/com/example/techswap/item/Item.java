package com.example.techswap.item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public abstract class Item implements Serializable {

    private String id;

    private Details details;

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
            List<String> urls = new ArrayList<String>();
            urls.add("https://static.thenounproject.com/png/1269202-200.png");
            return urls;
        }
        return this.imageUrls;
    }
    public String getFirstImageUrl() {
        if (this.imageUrls == null || this.imageUrls.size() == 0) {
            return "https://static.thenounproject.com/png/1269202-200.png";
        }
        return this.imageUrls.get(0);
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

    public abstract void setSpecifications(List<String> valuesList);

    public abstract List<String> getSpecifications();

    public void setSpecificationsTitleList(List<String> specificationsTitleList) {
        this.specificationsTitleList = specificationsTitleList;
    }

    public List<String> getSpecificationsTitleList() {
        return specificationsTitleList;
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
