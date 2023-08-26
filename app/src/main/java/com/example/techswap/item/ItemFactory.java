package com.example.techswap.item;

import com.example.techswap.item.categories.*;

/**
 * The `ItemFactory` class is responsible for creating instances of various item types based on the given item type string.
 * It provides a method to retrieve an instance of a specific item type using a factory pattern.
 */
public class ItemFactory {

    /**
     * Creates and returns an instance of the specified item type.
     *
     * @param itemType The type of item to create (e.g., "CPU", "GPU", "Motherboard", etc.).
     * @return An instance of the specified item type, or an instance of the "Other" item if the type is unknown.
     */
    public static Item getItem(String itemType) {
        switch (itemType) {
            case "CPU":
                return new CPU();
            case "GPU":
                return new GPU();
            case "Motherboard":
                return new Motherboard();
            case "Storage":
                return new Storage();
            case "Memory":
                return new RAM();
            case "Power":
                return new PSU();
            case "Case":
                return new Case();
            case "Other":
                return new Other();
        }
        return new Other();
    }
}
