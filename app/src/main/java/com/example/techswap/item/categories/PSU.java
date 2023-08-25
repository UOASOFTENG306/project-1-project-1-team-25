package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.ArrayList;
import java.util.List;

public class PSU extends Item {

    private String brand;

    private String wattage;

    public PSU() {
        Details details = new Details();
        details.setCategory("Power");
        this.setDetails(details);
    }

    public String getBrand() {
        return brand;
    }

    public String getWattage() {
        return wattage;
    }

    @Override
    public List<String> getSpecifications() {
        List<String> valuesList = new ArrayList<>();
        valuesList.add(brand);
        valuesList.add(wattage);

        return valuesList;
    }

    @Override
    public void setSpecifications(List<String> valuesList) {
        brand = valuesList.get(0);
        wattage = valuesList.get(1);
    }

}
