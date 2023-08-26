package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * The `CPU` class represents a Central Processing Unit (CPU), which is a subclass of the `Item` class.
 * It encapsulates information about the brand, socket type, clock speed, and number of cores of the CPU.
 */
public class CPU extends Item {

    private String brand;

    private String socket;

    private String clockSpeedMHz;

    private String numCores;

    /**
     * Constructs a new `CPU` object with default values and sets the category in the details.
     */
    public CPU() {
        Details details = new Details();
        details.setCategory("CPU");
        this.setDetails(details);
    }

    /**
     * Retrieves the specifications of the CPU.
     *
     * @return A list containing the brand, socket type, clock speed, and number of cores of the CPU.
     */
    @Override
    public List<String> getSpecifications() {
        List<String> valuesList = new ArrayList<>();
        valuesList.add(brand);
        valuesList.add(socket);
        valuesList.add(clockSpeedMHz);
        valuesList.add(numCores);

        return valuesList;
    }

    /**
     * Sets the specifications of the CPU using the provided values.
     *
     * @param valuesList A list containing the brand, socket type, clock speed, and number of cores of the CPU.
     */
    @Override
    public void setSpecifications(List<String> valuesList) {
        if (valuesList.size() == 4) {
            brand = valuesList.get(0);
            socket = valuesList.get(1);
            clockSpeedMHz = valuesList.get(2);
            numCores = valuesList.get(3);
        }
    }

}
