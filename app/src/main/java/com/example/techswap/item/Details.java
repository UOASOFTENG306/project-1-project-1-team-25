package com.example.techswap.item;

import java.io.Serializable;

/**
 * The `Details` class represents additional information about an item, which includes its price, title, subtitle,
 * description, category, and search title. This information provides context and metadata for the item.
 */
public class Details implements Serializable {

    private double price;

    private String title;

    private String subtitle;

    private String description;

    private String category;

    private String searchTitle;

    /**
     * Default constructor for creating a `Details` instance.
     */
    public Details() {
    }

    /**
     * Retrieves the price of the item.
     *
     * @return The price of the item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the item.
     *
     * @param price The price to set for the item.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retrieves the title of the item.
     *
     * @return The title of the item.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the item.
     *
     * @param title The title to set for the item.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the subtitle of the item.
     *
     * @return The subtitle of the item.
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * Sets the subtitle of the item.
     *
     * @param subtitle The subtitle to set for the item.
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * Retrieves the description of the item.
     *
     * @return The description of the item.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the item.
     *
     * @param description The description to set for the item.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the category of the item.
     *
     * @return The category of the item.
     */
    public String getCategory() { return category; }

    /**
     * Sets the category of the item.
     *
     * @param category The category to set for the item.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Retrieves the search title of the item.
     *
     * @return The search title of the item.
     */
    public String getSearchTitle() {
        return searchTitle;
    }

    /**
     * Sets the search title of the item.
     *
     * @param searchTitle The search title to set for the item.
     */
    public void setSearchTitle(String searchTitle) { this.searchTitle = searchTitle; }

}
