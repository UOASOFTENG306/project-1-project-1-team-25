package com.example.techswap.item.categories;

import com.example.techswap.item.Brand;
import com.example.techswap.item.Item;

import java.util.HashMap;

public class Other extends Item {

    private Brand brand;

    public Other() {
    }

    private Brand getBrand() {
        return brand;
    }

    private void setBrand(Brand brand) {
        this.brand = brand;
    }

    public HashMap<String,String> getSpecifications() {
        return null;
    }

    public void setSpecifications(HashMap<String,String> specifications) {

    }
}
