package com.example.techswap.item.categories;

import com.example.techswap.item.Brand;
import com.example.techswap.item.FormFactor;
import com.example.techswap.item.Item;

import java.util.HashMap;

public class Case extends Item {

    private Brand brand;

    private FormFactor formFactor;

    public Case() {
    }

    private Brand getBrand() {
        return brand;
    }

    private void setBrand(Brand brand) {
        this.brand = brand;
    }

    private FormFactor getFormFactor() {
        return formFactor;
    }

    private void setFormFactor(FormFactor formFactor) {
        this.formFactor = formFactor;
    }

    public HashMap<String,String> getSpecifications() {
        return null;
    }

    public void setSpecifications(HashMap<String,String> specifications) {

    }
}
