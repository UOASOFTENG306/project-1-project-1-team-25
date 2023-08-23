package com.example.techswap.item.categories;

import com.example.techswap.item.Details;
import com.example.techswap.item.Item;

import java.util.HashMap;

public class Case extends Item {

    private String brand;

    private String formFactor;

    public Case() {
        Details details = new Details();
        details.setCategory("Case");
        this.setDetails(details);
    }

    private String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        this.brand = brand;
    }

    private String getFormFactor() {
        return formFactor;
    }

    private void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

}
