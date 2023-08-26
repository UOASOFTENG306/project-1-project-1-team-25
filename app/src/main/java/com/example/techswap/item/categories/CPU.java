package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.ArrayList;
import java.util.List;

public class CPU extends Item {

    private String brand;

    private String socket;

    private String clockSpeedMHz;

    private String numCores;

    public CPU() {
        this.details.setCategory("CPU");
    }

    @Override
    public List<String> getSpecifications() {
        List<String> valuesList = new ArrayList<>();
        valuesList.add(brand);
        valuesList.add(socket);
        valuesList.add(clockSpeedMHz);
        valuesList.add(numCores);

        return valuesList;
    }

    @Override
    public void setSpecifications(List<String> valuesList) {
        if (valuesList.size() == 4) {
            brand = valuesList.get(0);
            socket = valuesList.get(1);
            clockSpeedMHz = valuesList.get(2);
            numCores = valuesList.get(3);
        }
    }

}
