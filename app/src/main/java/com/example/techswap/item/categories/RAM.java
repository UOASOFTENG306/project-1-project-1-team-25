package com.example.techswap.item.categories;

import com.example.techswap.item.Item;

import java.util.ArrayList;
import java.util.List;

public class RAM extends Item {

    private String brand;

    private String numSticks;

    private String singleCapacityGB;

    private String speedMHz;

    private String latencyCL;

    public RAM() {
        this.details.setCategory("Memory");
    }

    @Override
    public List<String> getSpecifications() {
        List<String> valuesList = new ArrayList<>();
        valuesList.add(brand);
        valuesList.add(numSticks);
        valuesList.add(singleCapacityGB);
        valuesList.add(speedMHz);
        valuesList.add(latencyCL);

        return valuesList;
    }

    @Override
    public void setSpecifications(List<String> valuesList) {
        brand = valuesList.get(0);
        numSticks = valuesList.get(1);
        singleCapacityGB = valuesList.get(2);
        speedMHz = valuesList.get(3);
        latencyCL = valuesList.get(4);
    }

}
