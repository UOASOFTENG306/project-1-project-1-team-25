package com.example.techswap.item.categories;

import com.example.techswap.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * The `Case` class represents a computer case, which is a subclass of the `Item` class.
 * It encapsulates information about the brand and form factor of the case.
 */
public class Case extends Item {

    private String brand;

    private String formFactor;

    /**
     * Constructs a new `Case` object with default values and sets the category in the details.
     */
    public Case() {
        this.details.setCategory("Case");
    }

    /**
     * Retrieves the specifications of the case.
     *
     * @return A list containing the brand and form factor of the case.
     */
    @Override
    public List<String> getSpecifications() {
        List<String> valuesList = new ArrayList<>();
        valuesList.add(brand);
        valuesList.add(formFactor);

        return valuesList;
    }

    /**
     * Sets the specifications of the case using the provided values.
     *
     * @param valuesList A list containing the brand and form factor of the case.
     */
    @Override
    public void setSpecifications(List<String> valuesList) {
        brand = valuesList.get(0);
        formFactor = valuesList.get(1);
    }

}
