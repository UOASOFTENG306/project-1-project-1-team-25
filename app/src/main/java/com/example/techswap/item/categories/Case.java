package com.example.techswap.item.categories;

import com.example.techswap.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Case extends Item {

    private String brand;

    private String formFactor;

    public Case() {
        this.details.setCategory("Case");
    }

    @Override
    public List<String> getSpecifications() {
        List<String> valuesList = new ArrayList<>();
        valuesList.add(brand);
        valuesList.add(formFactor);

        return valuesList;
    }

    @Override
    public void setSpecifications(List<String> valuesList) {
        brand = valuesList.get(0);
        formFactor = valuesList.get(1);
    }

}
