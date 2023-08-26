package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Other extends Item {

    private String brand;

    public Other() {
        this.details.setCategory("Other");
    }

    @Override
    public List<String> getSpecifications() {
        List<String> valuesList = new ArrayList<>();
        valuesList.add(brand);

        return valuesList;
    }

    @Override
    public void setSpecifications(List<String> valuesList) {
        brand = valuesList.get(0);
    }

}
