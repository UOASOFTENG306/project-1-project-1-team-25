package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.HashMap;

public class RAM extends Item {

    private String brand;

    private String numSticks;

    private String singleCapacityGB;

    private String speedMHz;

    private String latencyCL;

    public RAM() {
        Details details = new Details();
        details.setCategory("Memory");
        this.setDetails(details);
    }

    private String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        this.brand = brand;
    }

    private String getNumSticks() {
        return numSticks;
    }

    private void setNumSticks(String numSticks) {
        this.numSticks = numSticks;
    }

    private String getSingleCapacityGB() {
        return singleCapacityGB;
    }

    private void setSingleCapacityGB(String singleCapacityGB) {
        this.singleCapacityGB = singleCapacityGB;
    }

    private String getSpeedMHz() {
        return speedMHz;
    }

    private void setSpeedMHz(String speedMHz) {
        this.speedMHz = speedMHz;
    }

    private String getLatencyCL() {
        return latencyCL;
    }

    private void setLatencyCL(String latencyCL) {
        this.latencyCL = latencyCL;
    }

}
