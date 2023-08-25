package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.ArrayList;
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

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getMemorySizeGB() {
        return memorySizeGB;
    }

    public String getClockSpeedMHz() {
        return clockSpeedMHz;
    }

    @Override
    public List<String> getSpecifications() {
        List<String> valuesList = new ArrayList<>();
        valuesList.add(brand);
        valuesList.add(model);
        valuesList.add(memorySizeGB);
        valuesList.add(clockSpeedMHz);

        return valuesList;
    }

    @Override
    public void setSpecifications(List<String> valuesList) {
        brand = valuesList.get(0);
        model = valuesList.get(1);
        memorySizeGB = valuesList.get(2);
        clockSpeedMHz = valuesList.get(3);
    }

}
