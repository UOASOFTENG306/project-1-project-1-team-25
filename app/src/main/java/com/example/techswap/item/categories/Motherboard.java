package com.example.techswap.item.categories;

import com.example.techswap.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * The `Motherboard` class represents a computer motherboard, which is a subclass of the `Item` class.
 * It encapsulates information about the brand, socket type, RAM slots, and form factor of the motherboard.
 */
public class Motherboard extends Item {

    private String brand;

    private String socket;

    private String ramSlots;

    private String formFactor;

    /**
     * Constructs a new `Motherboard` object with default values and sets the category in the details.
     */
    public Motherboard() {
        this.details.setCategory("Motherboard");
    }

    /**
     * Retrieves the specifications of the motherboard.
     *
     * @return A list containing the brand, socket type, RAM slots, and form factor of the motherboard.
     */
    @Override
    public List<String> getSpecifications() {
        List<String> valuesList = new ArrayList<>();
        valuesList.add(brand);
        valuesList.add(socket);
        valuesList.add(ramSlots);
        valuesList.add(formFactor);

        return valuesList;
    }

    /**
     * Sets the specifications of the motherboard using the provided values.
     *
     * @param valuesList A list containing the brand, socket type, RAM slots, and form factor of the motherboard.
     */
    @Override
    public void setSpecifications(List<String> valuesList) {
        brand = valuesList.get(0);
        socket = valuesList.get(1);
        ramSlots = valuesList.get(2);
        formFactor = valuesList.get(3);
    }

}
