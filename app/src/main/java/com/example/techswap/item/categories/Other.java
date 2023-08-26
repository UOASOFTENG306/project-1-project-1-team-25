package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * The `Other` class represents a miscellaneous item, which is a subclass of the `Item` class.
 * It encapsulates information about the brand of the item.
 */
public class Other extends Item {

    private String brand;

    /**
     * Constructs a new `Other` object with default values and sets the category in the details.
     */
    public Other() {
        Details details = new Details();
        details.setCategory("Other");
        this.setDetails(details);
    }

    /**
     * Retrieves the specifications of the miscellaneous item.
     *
     * @return A list containing the brand of the item.
     */
    @Override
    public List<String> getSpecifications() {
        List<String> valuesList = new ArrayList<>();
        valuesList.add(brand);

        return valuesList;
    }

    /**
     * Sets the specifications of the miscellaneous item using the provided values.
     *
     * @param valuesList A list containing the brand of the item.
     */
    @Override
    public void setSpecifications(List<String> valuesList) {
        brand = valuesList.get(0);
    }

}
