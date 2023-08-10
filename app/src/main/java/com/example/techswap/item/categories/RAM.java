package com.example.techswap.item.categories;

import com.example.techswap.item.Brand;
import com.example.techswap.item.Item;

public class RAM extends Item {

    private Brand brand;

    private int numSticks;

    private int singleCapacityGB;

    private int speedMHz;

    private int latencyCL;

    public RAM() {
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getNumSticks() {
        return numSticks;
    }

    public void setNumSticks(int numSticks) {
        this.numSticks = numSticks;
    }

    public int getSingleCapacityGB() {
        return singleCapacityGB;
    }

    public void setSingleCapacityGB(int singleCapacityGB) {
        this.singleCapacityGB = singleCapacityGB;
    }

    public int getSpeedMHz() {
        return speedMHz;
    }

    public void setSpeedMHz(int speedMHz) {
        this.speedMHz = speedMHz;
    }

    public int getLatencyCL() {
        return latencyCL;
    }

    public void setLatencyCL(int latencyCL) {
        this.latencyCL = latencyCL;
    }

}
