package com.example.techswap.item;

import com.example.techswap.item.categories.*;

public class ItemFactory {

    public Item getItem(String itemType) {
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
