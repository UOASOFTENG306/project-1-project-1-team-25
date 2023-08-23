package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.HashMap;

public class Other extends Item {

    private String brand;

    public Other() {
        Details details = new Details();
        details.setCategory("Other");
        this.setDetails(details);
    }

    private String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        this.brand = brand;
    }

}
