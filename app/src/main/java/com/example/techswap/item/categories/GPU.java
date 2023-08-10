package com.example.techswap.item.categories;

import com.example.techswap.item.Brand;
import com.example.techswap.item.GPUModel;
import com.example.techswap.item.Item;

public class GPU extends Item {

    private Brand brand;

    private GPUModel model;

    private int memorySizeGB;

    private int clockSpeedMHz;

    public GPU() {
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public GPUModel getModel() {
        return model;
    }

    public void setModel(GPUModel model) {
        this.model = model;
    }

    public int getMemorySizeGB() {
        return memorySizeGB;
    }

    public void setMemorySizeGB(int memorySizeGB) {
        this.memorySizeGB = memorySizeGB;
    }

    public int getClockSpeedMHz() {
        return clockSpeedMHz;
    }

    public void setClockSpeedMHz(int clockSpeedMHz) {
        this.clockSpeedMHz = clockSpeedMHz;
    }

}
