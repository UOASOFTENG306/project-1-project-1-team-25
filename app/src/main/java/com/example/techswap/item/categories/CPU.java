package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.HashMap;
import java.util.List;

public class CPU extends Item {

    private String brand;

    private String socket;

    private String clockSpeedMHz;

    private String numCores;

    public CPU() {
        Details details = new Details();
        details.setCategory("CPU");
        this.setDetails(details);
    }

    private String getBrand() {
        return brand;
    }

    private String getSocket() {
        return socket;
    }

    private String getClockSpeedMHz() {
        return clockSpeedMHz;
    }

    private String getNumCores() {
        return numCores;
    }

    @Override
    public void setSpecifications(List<String> specificationsList) {
        brand = specificationsList.get(1);
        socket = specificationsList.get(3);
        clockSpeedMHz = specificationsList.get(5);
        numCores = specificationsList.get(7);
    }

}
