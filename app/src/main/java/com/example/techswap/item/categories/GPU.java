package com.example.techswap.item.categories;

import com.example.techswap.item.Brand;
import com.example.techswap.item.GPUModel;
import com.example.techswap.item.Item;

import java.util.HashMap;

public class GPU extends Item {

    private Brand brand;

    private GPUModel model;

    private int memorySizeGB;

    private int clockSpeedMHz;

    public GPU() {
    }

    private Brand getBrand() {
        return brand;
    }

    private void setBrand(Brand brand) {
        this.brand = brand;
    }

    private GPUModel getModel() {
        return model;
    }

    private void setModel(GPUModel model) {
        this.model = model;
    }

    private int getMemorySizeGB() {
        return memorySizeGB;
    }

    private void setMemorySizeGB(int memorySizeGB) {
        this.memorySizeGB = memorySizeGB;
    }

    private int getClockSpeedMHz() {
        return clockSpeedMHz;
    }

    private void setClockSpeedMHz(int clockSpeedMHz) {
        this.clockSpeedMHz = clockSpeedMHz;
    }

    public HashMap<String,String> getSpecifications() {
        return null;
    }

    public void setSpecifications(HashMap<String,String> specifications) {

    }

}
