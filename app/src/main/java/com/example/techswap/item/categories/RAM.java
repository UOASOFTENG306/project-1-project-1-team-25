package com.example.techswap.item.categories;

import com.example.techswap.item.Brand;
import com.example.techswap.item.Item;

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
        return null;
    }

    public void setSpecifications(HashMap<String,String> specifications) {

    }

}
