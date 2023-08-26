package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * The `GPU` class represents a Graphics Processing Unit (GPU), which is a subclass of the `Item` class.
 * It encapsulates information about the brand, model, memory size, and clock speed of the GPU.
 */
public class GPU extends Item {

    private String brand;

    private String model;

    private String memorySizeGB;

    private String clockSpeedMHz;

    /**
     * Constructs a new `GPU` object with default values and sets the category in the details.
     */
    public GPU() {
        Details details = new Details();
        details.setCategory("GPU");
        this.setDetails(details);
    }

    /**
     * Retrieves the specifications of the GPU.
     *
     * @return A list containing the brand, model, memory size, and clock speed of the GPU.
     */
    @Override
    public List<String> getSpecifications() {
        List<String> valuesList = new ArrayList<>();
        valuesList.add(brand);
        valuesList.add(model);
        valuesList.add(memorySizeGB);
        valuesList.add(clockSpeedMHz);

        return valuesList;
    }

    /**
     * Sets the specifications of the GPU using the provided values.
     *
     * @param valuesList A list containing the brand, model, memory size, and clock speed of the GPU.
     */
    @Override
    public void setSpecifications(List<String> valuesList) {
        brand = valuesList.get(0);
        model = valuesList.get(1);
        memorySizeGB = valuesList.get(2);
        clockSpeedMHz = valuesList.get(3);
    }

}
