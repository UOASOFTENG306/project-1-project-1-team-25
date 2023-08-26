package com.example.techswap.item;

import androidx.annotation.NonNull;
import com.example.techswap.interfaces.IDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The abstract `Item` class represents a generic item in a catalog.
 * Subclasses are expected to provide specific implementations for setting and getting specifications.
 */
public abstract class Item implements Serializable {

    protected IDetails details = new Details();
    private String id;
    private List<String> imageUrls = new ArrayList<>();

    private List<String> specificationsTitleList = new ArrayList<>();

    /**
     * Default constructor for creating an `Item` instance.
     */
    protected Item() {
    }

    /**
     * Retrieves the unique identifier of the item.
     *
     * @return The ID of the item.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the item.
     *
     * @param id The ID to set for the item.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retrieves a list of URLs for images associated with the item.
     *
     * @return A list of image URLs.
     */
    public List<String> getImageUrls() {
        if (this.imageUrls == null || this.imageUrls.size() == 0) {
            List<String> urls = new ArrayList<>();
            urls.add("https://upload.wikimedia.org/wikipedia/commons/8/89/HD_transparent_picture.png");
            return urls;
        }
        return this.imageUrls;
    }

    /**
     * Retrieves the URL of the first image associated with the item.
     *
     * @return The URL of the first image.
     */
    public String getFirstImageUrl() {
        if (this.imageUrls == null || this.imageUrls.size() == 0) {
            return "https://upload.wikimedia.org/wikipedia/commons/8/89/HD_transparent_picture.png"; // TODO: add resource for URL
        }
        return this.imageUrls.get(0);
    }

    /**
     * Sets the list of image URLs associated with the item.
     *
     * @param imageUrls The list of image URLs to set.
     */
    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    /**
     * Retrieves the details of the item.
     *
     * @return The details of the item.
     */
    public Details getDetails() {
        return (Details) details;
    }

    /**
     * Sets the details of the item.
     *
     * @param details The details to set for the item.
     */
    public void setDetails(Details details) {
        this.details = details;
    }

    /**
     * Retrieves the specifications of the item.
     * Subclasses are expected to implement this method.
     *
     * @return A list containing the specifications values.
     */
    public abstract List<String> getSpecifications();

    /**
     * Sets the specifications of the item using the provided values.
     * Subclasses are expected to implement this method.
     *
     * @param valuesList A list containing the specifications values.
     */
    public abstract void setSpecifications(List<String> valuesList);

    /**
     * Retrieves the list of titles for the specifications.
     *
     * @return The list of specification titles.
     */
    public List<String> getSpecificationsTitleList() {
        return specificationsTitleList;
    }

    /**
     * Sets the list of titles for the specifications.
     *
     * @param specificationsTitleList The list of specification titles to set.
     */
    public void setSpecificationsTitleList(List<String> specificationsTitleList) {
        this.specificationsTitleList = specificationsTitleList;
    }

    /**
     * Compares the current `Item` instance to another object for equality.
     *
     * @param o The object to compare against.
     * @return `true` if the objects are equal, otherwise `false`.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    /**
     * Computes a hash code value for the `Item` instance.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, details.getTitle());
    }

    /**
     * Generates a string representation of the `Item` instance.
     *
     * @return The string representation of the `Item`.
     */
    @NonNull
    @Override
    public String toString() {
        return "Item " + id + ": " + details.getTitle() + " hash: " + hashCode();
    }

}
