package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.HashMap;
import java.util.List;

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

    private String getWattage() {
        return wattage;
    }

    @Override
    public void setSpecifications(List<String> specificationsList) {
        brand = specificationsList.get(1);
        wattage = specificationsList.get(3);
    }

}
