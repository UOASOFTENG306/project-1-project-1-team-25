package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.HashMap;
import java.util.List;

public class Case extends Item {

    private String brand;

    private String formFactor;

    public Case() {
        Details details = new Details();
        details.setCategory("Case");
        this.setDetails(details);
    }

    @Override
    public void setSpecifications(List<String> specificationsList) {
        brand = specificationsList.get(1);
        formFactor = specificationsList.get(3);
    }

    private String getBrand() {
        return brand;
    }

    private String getFormFactor() {
        return formFactor;
    }


}
