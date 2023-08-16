package com.example.techswap.item.categories;

import com.example.techswap.item.Brand;
import com.example.techswap.item.GPUModel;
import com.example.techswap.item.Item;
import com.example.techswap.item.Socket;

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
        HashMap<String,String> specifications = new HashMap<String,String>();
        specifications.put("Brand", brand.toString());
        specifications.put("Model", model.toString());
        specifications.put("Memory size", String.valueOf(memorySizeGB));
        specifications.put("Clock speed", String.valueOf(clockSpeedMHz));

        return specifications;
    }

    public void setSpecifications(HashMap<String,String> specifications) {
        brand = Brand.valueOf(specifications.get("Brand"));
        model = GPUModel.valueOf(specifications.get("Model"));
        memorySizeGB = Integer.parseInt(specifications.get("Memory size"));
        clockSpeedMHz = Integer.parseInt(specifications.get("Clock speed"));
    }

}
