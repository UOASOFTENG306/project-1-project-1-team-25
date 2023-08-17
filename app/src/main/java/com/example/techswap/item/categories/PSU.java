package com.example.techswap.item.categories;

import com.example.techswap.item.Brand;
import com.example.techswap.item.Item;
import com.example.techswap.item.Socket;

import java.util.HashMap;

public class PSU extends Item {

    private Brand brand;

    private int wattage;

    public PSU() {
    }

    private Brand getBrand() {
        return brand;
    }

    private void setBrand(Brand brand) {
        this.brand = brand;
    }

    private int getWattage() {
        return wattage;
    }

    private void setWattage(int wattage) {
        this.wattage = wattage;
    }

    public HashMap<String,String> getSpecifications() {
        HashMap<String,String> specifications = new HashMap<String,String>();
        specifications.put("Brand", brand.toString());
        specifications.put("Rated capacity", String.valueOf(wattage));

        return specifications;
    }

    public void setSpecifications(HashMap<String,String> specifications) {
        brand = Brand.valueOf(specifications.get("Brand"));
        wattage = Integer.parseInt(specifications.get("Rated capacity"));
    }
}
