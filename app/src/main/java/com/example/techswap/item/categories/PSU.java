package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * The `PSU` class represents a Power Supply Unit (PSU), which is a subclass of the `Item` class.
 * It encapsulates information about the brand and wattage of the PSU.
 */
public class PSU extends Item {

    private String brand;

    private String wattage;

    /**
     * Constructs a new `PSU` object with default values and sets the category in the details.
     */
    public PSU() {
        Details details = new Details();
        details.setCategory("Power");
        this.setDetails(details);
    }

    /**
     * Retrieves the specifications of the PSU.
     *
     * @return A list containing the brand and wattage of the PSU.
     */
    @Override
    public List<String> getSpecifications() {
        List<String> valuesList = new ArrayList<>();
        valuesList.add(brand);
        valuesList.add(wattage);

        return valuesList;
    }

    /**
     * Sets the specifications of the PSU using the provided values.
     *
     * @param valuesList A list containing the brand and wattage of the PSU.
     */
    @Override
    public void setSpecifications(List<String> valuesList) {
        brand = valuesList.get(0);
        wattage = valuesList.get(1);
    }

}
