package com.example.techswap.item.categories;

import com.example.techswap.item.Brand;
import com.example.techswap.item.Item;
import com.example.techswap.item.Socket;

import java.util.HashMap;

public class RAM extends Item {

    private Brand brand;

    private int numSticks;

    private int singleCapacityGB;

    private int speedMHz;

    private int latencyCL;

    public RAM() {
    }

    private Brand getBrand() {
        return brand;
    }

    private void setBrand(Brand brand) {
        this.brand = brand;
    }

    private int getNumSticks() {
        return numSticks;
    }

    private void setNumSticks(int numSticks) {
        this.numSticks = numSticks;
    }

    private int getSingleCapacityGB() {
        return singleCapacityGB;
    }

    private void setSingleCapacityGB(int singleCapacityGB) {
        this.singleCapacityGB = singleCapacityGB;
    }

    private int getSpeedMHz() {
        return speedMHz;
    }

    private void setSpeedMHz(int speedMHz) {
        this.speedMHz = speedMHz;
    }

    private int getLatencyCL() {
        return latencyCL;
    }

    private void setLatencyCL(int latencyCL) {
        this.latencyCL = latencyCL;
    }

    public HashMap<String,String> getSpecifications() {
        HashMap<String,String> specifications = new HashMap<String,String>();
        specifications.put("Brand", brand.toString());
        specifications.put("No. of sticks", String.valueOf(numSticks));
        specifications.put("Single stick capacity", String.valueOf(singleCapacityGB));
        specifications.put("Clock speed", String.valueOf(speedMHz));
        specifications.put("Latency", String.valueOf(latencyCL));

        return specifications;
    }

    public void setSpecifications(HashMap<String,String> specifications) {
        brand = Brand.valueOf(specifications.get("Brand"));
        numSticks = Integer.parseInt(specifications.get("No. of sticks"));
        singleCapacityGB = Integer.parseInt(specifications.get("Single stick capacity"));
        speedMHz = Integer.parseInt(specifications.get("Clock speed"));
        latencyCL = Integer.parseInt(specifications.get("Latency"));
    }

}
