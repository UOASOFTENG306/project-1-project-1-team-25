package com.example.techswap.item.categories;

import com.example.techswap.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * The `Storage` class represents a storage device, which is a subclass of the `Item` class.
 * It encapsulates information about the brand, type (solid state or not), and capacity of the storage.
 */
public class Storage extends Item {

    private String brand;

    private String isSolidState;

    private String capacityGB;

    /**
     * Constructs a new `Storage` object with default values and sets the category in the details.
     */
    public Storage() {
        this.details.setCategory("Storage");
    }

    /**
     * Retrieves the specifications of the storage device.
     *
     * @return A list containing the brand, type (solid state or not), and capacity of the storage.
     */
    @Override
    public List<String> getSpecifications() {
        List<String> valuesList = new ArrayList<>();
        valuesList.add(brand);
        valuesList.add(isSolidState);
        valuesList.add(capacityGB);

        return valuesList;
    }

    /**
     * Sets the specifications of the storage device using the provided values.
     *
     * @param valuesList A list containing the brand, type (solid state or not), and capacity of the storage.
     */
    @Override
    public void setSpecifications(List<String> valuesList) {
        brand = valuesList.get(0);
        isSolidState = valuesList.get(1);
        capacityGB = valuesList.get(2);
    }

}
