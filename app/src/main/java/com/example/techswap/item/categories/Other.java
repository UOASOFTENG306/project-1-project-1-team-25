package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.HashMap;
import java.util.List;

public class Other extends Item {

    private String brand;

    public Other() {
        Details details = new Details();
        details.setCategory("Other");
        this.setDetails(details);
    }

    private String getBrand() {
        return brand;
    }

    @Override
    public void setSpecifications(List<String> specificationsList) {
        brand = specificationsList.get(1);
    }

}
