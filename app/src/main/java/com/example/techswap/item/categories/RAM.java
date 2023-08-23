package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.HashMap;
import java.util.List;

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

    private String getNumSticks() {
        return numSticks;
    }

    private String getSingleCapacityGB() {
        return singleCapacityGB;
    }

    private String getSpeedMHz() {
        return speedMHz;
    }

    private String getLatencyCL() {
        return latencyCL;
    }

    @Override
    public void setSpecifications(List<String> specificationsList) {
        brand = specificationsList.get(1);
        numSticks = specificationsList.get(3);
        singleCapacityGB = specificationsList.get(5);
        speedMHz = specificationsList.get(7);
        latencyCL = specificationsList.get(9);
    }

}
