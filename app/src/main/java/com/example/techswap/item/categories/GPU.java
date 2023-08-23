package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.HashMap;
import java.util.List;

public class GPU extends Item {

    private String brand;

    private String model;

    private String memorySizeGB;

    private String clockSpeedMHz;

    public GPU() {
        Details details = new Details();
        details.setCategory("GPU");
        this.setDetails(details);
    }

    private String getBrand() {
        return brand;
    }

    private String getModel() {
        return model;
    }

    private String getMemorySizeGB() {
        return memorySizeGB;
    }

    private String getClockSpeedMHz() {
        return clockSpeedMHz;
    }

    @Override
    public void setSpecifications(List<String> specificationsList) {
        brand = specificationsList.get(1);
        model = specificationsList.get(3);
        memorySizeGB = specificationsList.get(5);
        clockSpeedMHz = specificationsList.get(7);
    }

}
