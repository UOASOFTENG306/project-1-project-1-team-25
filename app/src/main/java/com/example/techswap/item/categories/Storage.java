package com.example.techswap.item.categories;

import com.example.techswap.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Storage extends Item {

    private String brand;

    private String isSolidState;

    private String capacityGB;

    public Storage() {
        this.details.setCategory("Storage");
    }

    @Override
    public List<String> getSpecifications() {
        List<String> valuesList = new ArrayList<>();
        valuesList.add(brand);
        valuesList.add(isSolidState);
        valuesList.add(capacityGB);

        return valuesList;
    }

    @Override
    public void setSpecifications(List<String> valuesList) {
        brand = valuesList.get(0);
        isSolidState = valuesList.get(1);
        capacityGB = valuesList.get(2);
    }

}
