package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.HashMap;

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

    private void setBrand(String brand) {
        this.brand = brand;
    }

    private String getModel() {
        return model;
    }

    private void setModel(String model) {
        this.model = model;
    }

    private String getMemorySizeGB() {
        return memorySizeGB;
    }

    private void setMemorySizeGB(String memorySizeGB) {
        this.memorySizeGB = memorySizeGB;
    }

    private String getClockSpeedMHz() {
        return clockSpeedMHz;
    }

    private void setClockSpeedMHz(String clockSpeedMHz) {
        this.clockSpeedMHz = clockSpeedMHz;
    }

}
