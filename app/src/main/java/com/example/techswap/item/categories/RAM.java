package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * The `RAM` class represents Random Access Memory (RAM), which is a subclass of the `Item` class.
 * It encapsulates information about the brand, number of sticks, capacity per stick, speed, and latency of the RAM.
 */
public class RAM extends Item {

    private String brand;

    private String numSticks;

    private String singleCapacityGB;

    private String speedMHz;

    private String latencyCL;

    /**
     * Constructs a new `RAM` object with default values and sets the category in the details.
     */
    public RAM() {
        Details details = new Details();
        details.setCategory("Memory");
        this.setDetails(details);
    }

    /**
     * Retrieves the specifications of the RAM.
     *
     * @return A list containing the brand, number of sticks, capacity per stick, speed, and latency of the RAM.
     */
    @Override
    public List<String> getSpecifications() {
        List<String> valuesList = new ArrayList<>();
        valuesList.add(brand);
        valuesList.add(numSticks);
        valuesList.add(singleCapacityGB);
        valuesList.add(speedMHz);
        valuesList.add(latencyCL);

        return valuesList;
    }

    /**
     * Sets the specifications of the RAM using the provided values.
     *
     * @param valuesList A list containing the brand, number of sticks, capacity per stick, speed, and latency of the RAM.
     */
    @Override
    public void setSpecifications(List<String> valuesList) {
        brand = valuesList.get(0);
        numSticks = valuesList.get(1);
        singleCapacityGB = valuesList.get(2);
        speedMHz = valuesList.get(3);
        latencyCL = valuesList.get(4);
    }

}
