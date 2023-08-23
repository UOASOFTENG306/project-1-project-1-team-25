package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.HashMap;
import java.util.List;

public class Storage extends Item {

    private String brand;

    private String isSolidState;

    private String capacityGB;

    public Storage() {
        Details details = new Details();
        details.setCategory("Storage");
        this.setDetails(details);
    }

    private String getBrand() {
        return brand;
    }

    private String isSolidState() {
        return isSolidState;
    }

    private String getCapacityGB() {
        return capacityGB;
    }

    @Override
    public void setSpecifications(List<String> specificationsList) {
        brand = specificationsList.get(1);
        isSolidState = specificationsList.get(3);
        capacityGB = specificationsList.get(5);
    }

}
