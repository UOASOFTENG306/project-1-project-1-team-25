package com.example.techswap.item.categories;

import com.example.techswap.item.Item;

public class ItemFactory {

    public Item getItem(String itemType) {
        switch (itemType) {
            case "case":
                return new Case();
            case "cpu":
                return new CPU();
            case "gpu":
                return new GPU();
            case "motherboard":
                return new Motherboard();
            case "other":
                return new Other();
            case "psu":
                return new PSU();
            case "ram":
                return new RAM();
            case "storage":
                return new Storage();
        }
        return new Other();
    }
}
