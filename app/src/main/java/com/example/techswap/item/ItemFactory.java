package com.example.techswap.item;

import com.example.techswap.fragments.ListFragment;
import com.example.techswap.item.Item;
import com.example.techswap.item.categories.CPU;
import com.example.techswap.item.categories.Case;
import com.example.techswap.item.categories.GPU;
import com.example.techswap.item.categories.Motherboard;
import com.example.techswap.item.categories.Other;
import com.example.techswap.item.categories.PSU;
import com.example.techswap.item.categories.RAM;
import com.example.techswap.item.categories.Storage;

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
