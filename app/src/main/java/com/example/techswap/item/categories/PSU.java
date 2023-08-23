package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.HashMap;

public class PSU extends Item {

    private String brand;

    private String wattage;

    public PSU() {
        Details details = new Details();
        details.setCategory("Power");
        this.setDetails(details);
    }

    private String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        this.brand = brand;
    }

    private String getWattage() {
        return wattage;
    }

    private void setWattage(String wattage) {
        this.wattage = wattage;
    }

}
