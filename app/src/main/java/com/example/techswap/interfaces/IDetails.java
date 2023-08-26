package com.example.techswap.interfaces;

/**
 * The `IDetails` interface defines methods for accessing and modifying detailed information
 * associated with an item, such as its price, titles, descriptions, and category.
 * Implementations of this interface are intended to provide access to the details of an item.
 */
public interface IDetails {

    /**
     * Retrieves the price of the item.
     *
     * @return The price of the item.
     */
    double getPrice();

    /**
     * Sets the price of the item.
     *
     * @param price The price to set for the item.
     */
    void setPrice(double price);

    /**
     * Retrieves the title of the item.
     *
     * @return The title of the item.
     */
    String getTitle();

    /**
     * Sets the title of the item.
     *
     * @param title The title to set for the item.
     */
    void setTitle(String title);

    /**
     * Retrieves the subtitle of the item.
     *
     * @return The subtitle of the item.
     */
    String getSubtitle();

    /**
     * Sets the subtitle of the item.
     *
     * @param subtitle The subtitle to set for the item.
     */
    void setSubtitle(String subtitle);

    /**
     * Retrieves the description of the item.
     *
     * @return The description of the item.
     */
    String getDescription();

    /**
     * Sets the description of the item.
     *
     * @param description The description to set for the item.
     */
    void setDescription(String description);

    /**
     * Retrieves the category of the item.
     *
     * @return The category of the item.
     */
    String getCategory();

    /**
     * Sets the category of the item.
     *
     * @param category The category to set for the item.
     */
    void setCategory(String category);

    /**
     * Retrieves the search title of the item.
     *
     * @return The search title of the item.
     */
    String getSearchTitle();

    /**
     * Sets the search title of the item.
     *
     * @param searchTitle The search title to set for the item.
     */
    void setSearchTitle(String searchTitle);

}
